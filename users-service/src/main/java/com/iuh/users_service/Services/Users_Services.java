package com.iuh.users_service.Services;

import com.iuh.users_service.Clients.DoctorClient;
import com.iuh.users_service.Dtos.*;
import com.iuh.users_service.Dtos.Reponse.*;
import com.iuh.users_service.Dtos.Request.*;
import com.iuh.users_service.IServices.IUsers_Services;
import com.iuh.users_service.Mapper.Users_Mapper;
import com.iuh.users_service.Models.Users_Models;
import com.iuh.users_service.Repositories.UsersRepositories;
import lombok.RequiredArgsConstructor;
import org.apache.hc.core5.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
    private DoctorClient doctorClient;
    private static final Logger log = LoggerFactory.getLogger(Users_Services.class);

    @Override
    public ResponseEntity<?> signupUsers(Register register) {
        try {
            Users_Models users = usersRepositories.findByPhone(register.getPhone());
            if (users != null) {
                return ResponseEntity.badRequest().body("Số điện thoại này đã tồn tại trong hệ thống!!!");
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
//            userSave.setRole("DOCTOR");
            //doctorClient.save(usersMapper.toUsersRequest(userSave));
            return ResponseEntity.ok(userSave);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.badRequest().body("Error");
        }

    }

    @Override
    public ResponseEntity<?> getAllUsers(Token token) {
        List<ProfileUsers> models = new ArrayList<>();
        List<Users_Models> listUsers = usersRepositories.findAll();
        for (Users_Models users : listUsers) {
            ProfileUsers user = usersMapper.toProfileUsers(users);
            models.add(user);
        }

        return ResponseEntity.ok(usersMapper.toGetAllUsers(models, token));
    }

    @Override
    public ResponseEntity<?> loginAuth(LoginDto loginDtoRequest) throws Exception {
        try {

            Users_Models usersExist = usersRepositories.findByPhone(loginDtoRequest.getUserName());

            if (usersExist == null) {
                return ResponseEntity.status(HttpStatus.SC_UNAUTHORIZED).body("Số điện thoại không tồn tại trong hệ thống");
            }
            String validPassword = usersExist.getPassWord();
            boolean jwt = passwordEncoder.matches(loginDtoRequest.getPassWord(), validPassword);
            if (jwt == false) {
                return ResponseEntity.status(HttpStatus.SC_UNAUTHORIZED).body("Mật khẩu không đúng");
            }

            if (usersExist.getRole().matches("DOCTOR")) {
                try {
                    DoctorFeign doctorFeign = doctorClient.getById(usersExist.getId());
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDtoRequest.getUserName(), loginDtoRequest.getPassWord()));
                    DoctorAuth doctorReponse = usersMapper.toDoctorAuth(doctorFeign);
                    UserDetails doctorDetails = new User(doctorReponse.getUsername(), doctorReponse.getPassword(), doctorReponse.getAuthorities());
                    // add authentication vào jwtUntils để tạo ra token

                    String accessTokenDoctor = jwtServices.generateToken(doctorDetails);

                    // chứa thông tin mà refresh token mang theo
                    HashMap<String, String> userInfo = new HashMap<>();
                    userInfo.put("role", doctorReponse.getRole());
                    String refreshTokenDoctor = jwtServices.generateRefreshTokenDoctor(userInfo, doctorReponse);
                    Token token = new Token();
                    token.setAccessToken(accessTokenDoctor);
                    token.setRefreshToken(refreshTokenDoctor);

                    return ResponseEntity.ok(usersMapper.toDoctorLogin(usersMapper.toDoctorDto(doctorFeign, doctorFeign.getRating()), token));
                } catch (Exception e) {

                    log.warn(e.toString());
                    return ResponseEntity.badRequest().body("Không tìm thấy dư liệu bác sĩ");
                }

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
            usersMapper.toUserUntill(usersDtoRespones);

            return ResponseEntity.ok(usersMapper.userDtoUpdate(usersExist, token));
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
                return ResponseEntity.badRequest().body("Số điện thoại không khả dụng");
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
            Users_Models users_models = usersRepositories.getReferenceById(Long.parseLong(userName));
            if(users_models.getRole().matches("DOCTOR")) {
                try {
                    DoctorFeign doctorFeign = doctorClient.getById(users_models.getId());
                    DoctorLogin doctorLogin = new DoctorLogin();
                    doctorLogin.setData(usersMapper.toDoctorDto(doctorFeign, doctorFeign.getRating()));
                    doctorLogin.setToken(token);
                    return ResponseEntity.ok(doctorLogin);
                } catch (Exception e) {

                    log.warn(e.toString());
                    return ResponseEntity.badRequest().body("Không tìm thấy dư liệu bác sĩ");
                }

            }
            UserLogin userLogin = usersMapper.userDtoUpdate(users_models, token);
           return ResponseEntity.ok(userLogin);
        } catch (Exception e) {
            try {
                jwtServices.isExpiration(token.getRefreshToken());
                String userName = jwtServices.extractUserName(token.getRefreshToken());
                String accessToken = jwtServices.generateTokenSignup(userName);
                String refreshToken = jwtServices.generateRefreshTokenSignup(userName);
                Token tokenDoctor = new Token();
                tokenDoctor.setAccessToken(accessToken);
                tokenDoctor.setRefreshToken(refreshToken);
                Users_Models users_models1 = usersRepositories.getReferenceById(Long.parseLong(userName));
                if(users_models1.getRole().matches("DOCTOR")) {
                    try {
                        DoctorFeign doctorFeign = doctorClient.getById(users_models1.getId());
                        DoctorLogin doctorLogin = new DoctorLogin();
                        doctorLogin.setData(usersMapper.toDoctorDto(doctorFeign, doctorFeign.getRating()));
                        doctorLogin.setToken(tokenDoctor);
                        return ResponseEntity.ok(doctorLogin);
                    } catch (Exception ex) {

                        log.warn(ex.toString());
                        return ResponseEntity.badRequest().body("Không tìm thấy dư liệu bác sĩ");
                    }

                }
                UserLogin userLogin1 = usersMapper.userDtoUpdate(users_models1, tokenDoctor);
                return ResponseEntity.ok(userLogin1);
            }
            catch (Exception ex) {
                return ResponseEntity.badRequest().body("Phiên đăng nhập đã hết hạn");
            }
        }

    }

    @Override
    public UserDto getUserById(Long id) {
        Users_Models users = usersRepositories.getReferenceById(id);
        UserDto user = usersMapper.toUserDto(users);
        return user;
    }

    @Override
    public ResponseEntity<?> createDoctor(RegisterDoctor register, Token token) {
        try {
            boolean existPhone = usersRepositories.existsByPhone(register.getPhone());
            if (existPhone) {
                return ResponseEntity.badRequest().body("Số điện thoại này đã tồn tại trong hệ thống!!!");
            }
            boolean existEmail = usersRepositories.existsByEmail(register.getEmail());
            if (existEmail) {
                return ResponseEntity.badRequest().body("Email này đã tồn tại trong hệ thống!!!");
            }
            String passwordEncode = passwordEncoder.encode(register.getPassWord());
            register.setPassWord(passwordEncode);
            register.setRole("DOCTOR");
            register.setImage("https://th.bing.com/th/id/R.be953f29410b3d18ef0e5e0fbd8d3120?rik=Dm2iDRVLgVcpdA&pid=ImgRaw&r=0");
            register.setProcessSignup(3);
            try {
                Users_Models userSave = usersMapper.toUsersEntity(usersMapper.toRegisterDoctor(register));
                Users_Models userSaved = usersRepositories.save(userSave);
                register.setId(userSaved.getId());
                register.setPassWord(passwordEncode);
                UsersRequest doctor = doctorClient.save(register);
                doctor.setId(userSaved.getId());
                return ResponseEntity.ok(usersMapper.doctorReponse(doctor, token));
            } catch (Exception e) {
                System.out.println(e);
                return ResponseEntity.badRequest().body("Số điện thoại đã tồn tại trong dữ liệu bác sĩ!!!");
            }
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @Override
    public DoctorAuth getDoctorById(Long id) {
        try {
            DoctorFeign doctorFeign = doctorClient.getById(id);
            return usersMapper.toDoctorAuth(doctorFeign);
        } catch (Exception e) {
            log.warn(e.toString());
            return null;

        }
    }

    @Override
    public Users_Models updateDoctor(UpdateDoctors updateUsers, Long id) {
        try {
//            Users_Models vertify = usersRepositories.getReferenceById(id);
//            if(vertify == null) {
//                return ResponseEntity.badRequest().body("Không tìm thấy bác sĩ");
//            }
            Users_Models updatedDoctor = usersMapper.toDoctorModelUpdate(updateUsers);
            updatedDoctor.setRole("DOCTOR");
            Users_Models savedUser = usersRepositories.save(updatedDoctor);
            return savedUser;

        }
        catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public boolean deleteOneDoctor(Long id) {
        try {
            Users_Models users = usersRepositories.getReferenceById(id);
            if(users != null) {
                usersRepositories.deleteById(id);
                return true;
            } else {
                log.warn("Không tìm thấy người dùng");
                return false;
            }
        } catch (Exception e) {
            log.warn(e.toString());
            return false;
        }
    }

    @Override
    public boolean deleteManyDoctor(List<Long> ids) {
        try {
            usersRepositories.deleteAllById(ids);
            return true;
        } catch (Exception e) {
            log.warn(e.toString());
            return false;
        }
    }

    @Override
    public UserLogin getOneUser(Long id, Token token) {
        try {
            Users_Models users = usersRepositories.getReferenceById(id);
            return usersMapper.userDtoUpdate(users, token);
        } catch (Exception e) {
            log.warn(e.toString());
            return null;
        }
    }

    @Override
    public int updateOneUser(Long id, UserDtoUpdate userDtoUpdate) {
        try {
            usersRepositories.save(usersMapper.toUserUpdateModel(userDtoUpdate));
            return 1;
        } catch (Exception e) {
            log.warn(e.toString());
            throw new RuntimeException(e);
        }
    }
    @Override
    public int checkEmailAndPhone(String phone, String email, Long id) {
        try {
            boolean existPhone = usersRepositories.existsByPhoneAndIdIsNot(phone, id);
            if (existPhone) {
                return 2;
            }
            boolean existEmail = usersRepositories.existsByEmailAndIdIsNot(email, id);
            if (existEmail) {
                return 3;
            }
            return 1;
        } catch (Exception e) {
            log.warn(e.toString());
            throw new RuntimeException(e);
        }
    }
}
