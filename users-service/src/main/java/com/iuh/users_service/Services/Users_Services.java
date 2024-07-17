package com.iuh.users_service.Services;

import com.iuh.users_service.Clients.UsersHealthClients;
import com.iuh.users_service.Dtos.Reponse.Authenticated;
import com.iuh.users_service.Dtos.Reponse.ProfileUsers;
import com.iuh.users_service.Dtos.Reponse.Token;
import com.iuh.users_service.Dtos.Request.*;
import com.iuh.users_service.Dtos.UserDto;
import com.iuh.users_service.IServices.IUsers_Services;
import com.iuh.users_service.Mapper.Users_Mapper;
import com.iuh.users_service.Models.Users_Models;
import com.iuh.users_service.Repositories.UsersRepositories;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class Users_Services implements IUsers_Services {
    @Autowired
    private UsersRepositories usersRepositories;
    @Autowired
    private Users_Mapper usersMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTServices jwtServices;

    @Autowired
    private UsersHealthClients usersHealthClients;
    private static final long REFRESH_EXPiRATION_TIME = 2592000000L;
    private static final long ACCESS_EXPIRATION_TIME = 60000L;
    @Override
    public ResponseEntity<?> signupUsers(Register register) {
        try {
            Users_Models users = usersRepositories.findByPhone(register.getPhone());
            if (users != null) {
                return ResponseEntity.badRequest().body("Phone number is already exist!");
            }
            String passwordEncode = passwordEncoder.encode(register.getPassWord());
            register.setPassWord(passwordEncode);
            register.setRole("USER");
            register.setEmail(null);
            register.setDateOfBirth(null);
            register.setSex(false);
            register.setFullName(null);
            register.setImage(null);
            register.setAddress(null);
            register.setProcessSignup(1);
            Users_Models userSave = usersMapper.toUsersEntity(register);
            usersRepositories.save(userSave);
            //usersHealthClients.save(usersMapper.toUsersRequest(userSave));
            return ResponseEntity.ok(userSave);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.badRequest().body("Error");
        }

    }

    @Override
    public List<ProfileUsers> getAllUsers() {
        List<ProfileUsers> models = new ArrayList<>();
        List<Users_Models> listUsers = usersRepositories.findAll();
        for (Users_Models users : listUsers) {
            ProfileUsers user = usersMapper.toProfileUsers(users);
            models.add(user);
        }
        return models;
    }

    @Override
    public ResponseEntity<?> loginAuth(LoginDto loginDtoRequest) throws Exception {
        try {

            Users_Models usersExist = usersRepositories.findByPhone(loginDtoRequest.getUserName());

            if (usersExist == null) {
                return ResponseEntity.status(HttpStatus.SC_UNAUTHORIZED).body("Số điện thoại không tồn tại trogn hệ thống");
            }
            String validPassword = usersExist.getPassWord();
            boolean jwt = passwordEncoder.matches(loginDtoRequest.getPassWord(), validPassword);
            if (jwt == false) {
                return ResponseEntity.status(HttpStatus.SC_UNAUTHORIZED).body("Mật khẩu không đúng");
            }
            // set các giá trị phù hợp với thư viện Authentication
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDtoRequest.getUserName(), loginDtoRequest.getPassWord()));
            Authenticated usersDtoRespones = usersMapper.toAuthenticated(usersExist);
            UserDetails userDetails = new User(usersDtoRespones.getUsername(), usersDtoRespones.getPassword(), usersDtoRespones.getAuthorities());
            // add authentication vào jwtUntils để tạo ra token
            String accessToken = jwtServices.generateToken(userDetails);

            // chứa thông tin mà refresh token mang theo
            HashMap<String, String> userInfo = new HashMap<>();
            userInfo.put("role", usersDtoRespones.getRole());
            String refreshToken = jwtServices.generateRefreshToken(userInfo, usersDtoRespones);
            Token token = new Token();
            token.setAccessToken(accessToken);
            token.setRefreshToken(refreshToken);
            usersDtoRespones.setToken(token);
            return ResponseEntity.ok(usersMapper.toUserUntill(usersDtoRespones));
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String userName) {
        Users_Models userLoad = usersRepositories.findByPhone(userName);
        Authenticated users = usersMapper.toAuthenticated(userLoad);
        return users;
    }

//    @Override
//    public Authenticated refreshToken(Authenticated authenticated) throws Exception {
//        ReturnToken tokenNew = new ReturnToken();
//        try {
//            String userName = jwtServices.extractUserName(authenticated.getToken().getRefreshToken());
////            System.out.println(userName);
//            Users_Models users = usersRepositories.findByPhone(userName);
//
//            try {
//                authenticated.setPhone(users.getPhone());
//                jwtServices.isTokenvalid(authenticated.getToken().getAccessToken(),authenticated);
//                // có nghiĩa l k cần refress token
//                return null;
//            } catch (Exception e) {
//                HashMap<String, String> claims = new HashMap<>();
//                claims.put("role", users.getRole());
//                ReturnToken newsToken = jwtServices.reloadRefreshToken(claims,authenticated.getToken().getRefreshToken(), authenticated);
//                authenticated.getToken().setAccessToken(newsToken.getAccessToken());
//                authenticated.getToken().setRefreshToken(newsToken.getRefreshToken());
//                return authenticated;
//            }
//
//        } catch (Exception e) {
//            System.out.println(e);
//           throw new Exception(e);
//        }
//    }

    @Override
    public ResponseEntity<?> getUserByUserName(String userName) {
        Users_Models users = usersRepositories.findByPhone(userName);
        if (users != null) {
            ProfileUsers user = usersMapper.toProfileUsers(users);
            return ResponseEntity.ok(user);
        } else {
            return null;
        }
    }

    @Override
    public ResponseEntity<?> updateUsers(UpdateUsers updateUsers) {
        try {
            Users_Models vertify = usersRepositories.findByPhone(updateUsers.getPhone());
            if(vertify == null) {
                return ResponseEntity.badRequest().body("Số đieện thoại khkong6 khả dụng");
            }
            String validPassword = vertify.getPassWord();
            boolean jwt = passwordEncoder.matches(updateUsers.getPassWord(), validPassword);
            if (jwt == false) {
                return ResponseEntity.badRequest().body("Mật khẩu không khả dụng");
            }
            Users_Models updatedUser = usersMapper.toUserModelUpdate(updateUsers);
            updatedUser.setPassWord(validPassword);
            updatedUser.setRole("USER");
            Users_Models savedUser = usersRepositories.save(updatedUser);
            return ResponseEntity.ok(savedUser);

        }
        catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @Override
    public ResponseEntity<?> generateTokenSignup(GenerateToken generateToken) {
        String accessToken = jwtServices.generateTokenSignup(generateToken.getId()+"");
        String refreshToken = jwtServices.generateRefreshTokenSignup(generateToken.getId()+"");
        ReturnToken token = new ReturnToken();
        token.setAccessToken(accessToken);
        token.setRefreshToken(refreshToken);
        return ResponseEntity.ok(token);
    }

    @Override
    public ResponseEntity<?> getUserByToken(Token token) {
        try {
            // check token
            jwtServices.isExpiration(token.getAccessToken());
            String userName = jwtServices.extractUserName(token.getAccessToken());
            //Users_Models vertify = usersRepositories.findByPhone(userName);
            System.out.println(userName);
           return ResponseEntity.ok(userName);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.badRequest().body("Error");
        }

    }

    @Override
    public UserDto getUserById(Long id) {
        Users_Models users = usersRepositories.getReferenceById(id);
        UserDto user = usersMapper.toUserDto(users);
        return user;
    }


}
