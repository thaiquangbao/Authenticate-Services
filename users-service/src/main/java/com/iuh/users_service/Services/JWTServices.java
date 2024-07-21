package com.iuh.users_service.Services;

import com.iuh.users_service.Dtos.DoctorDtoCheck;
import com.iuh.users_service.Dtos.Reponse.Authenticated;
import com.iuh.users_service.Dtos.Reponse.DoctorAuth;
import com.iuh.users_service.Dtos.Request.ReturnToken;
import com.iuh.users_service.Dtos.UserDto;
import com.iuh.users_service.Dtos.UserDtoCheck;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Component
public class JWTServices implements Serializable {
    private final SecretKey secretKey;
    //time Hết Hạn là 1 ngày
    private static final long REFRESH_EXPiRATION_TIME = 2592000000L;
    private static final long ACCESS_EXPIRATION_TIME = 60000L;
    public JWTServices(){
        String secretString = "843567893696976453275974432697R634976R738467TR678T34865R6834R8763T478378637664538745673865783678548735687R3";
        //chuẩn hóa theo UTF-8
        byte[] keyByte = Base64.getDecoder().decode(secretString.getBytes(StandardCharsets.UTF_8));

        this.secretKey = new SecretKeySpec(keyByte, "hmacSHA256");
    }
    //tao token access
    public String generateToken(UserDetails userDetails){
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ACCESS_EXPIRATION_TIME))
                .signWith(secretKey)
                .compact();
    }
    //refresh token là cái chính
    public String generateRefreshToken(HashMap<String, String> claims, Authenticated userDetails){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+REFRESH_EXPiRATION_TIME))
                .signWith(secretKey)
                .compact();
    }
    public String generateRefreshTokenDoctor(HashMap<String, String> claims, DoctorAuth userDetails){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+REFRESH_EXPiRATION_TIME))
                .signWith(secretKey)
                .compact();
    }
    // reload token
    public ReturnToken reloadRefreshToken(HashMap<String, String> claims, String token, UserDtoCheck userDetails){
        // lấy time còn lại của refresh token
        Date expirationDate = extractClaims(token, Claims::getExpiration);

        // thời ian còn lại
        long remainingTime = expirationDate.getTime() - System.currentTimeMillis();

        // Tạo ra lại access token
        String newAccessToken = Jwts.builder()
                .setSubject(userDetails.getData().getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_EXPIRATION_TIME))
                .signWith(secretKey)
                .compact();

        // Generate new refresh token with the remaining time
        String newRefreshToken = Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getData().getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + remainingTime))
                .signWith(secretKey)
                .compact();

        // Return the new access token
        ReturnToken tokenNew = new ReturnToken();
        tokenNew.setAccessToken(newAccessToken);
        tokenNew.setRefreshToken(newRefreshToken);
        return tokenNew;
    }
    // reload token for doctor
    public ReturnToken reloadRefreshTokenDoctor(HashMap<String, String> claims, String token, DoctorDtoCheck userDetails){
        // lấy time còn lại của refresh token
        Date expirationDate = extractClaims(token, Claims::getExpiration);

        // thời ian còn lại
        long remainingTime = expirationDate.getTime() - System.currentTimeMillis();

        // Tạo ra lại access token
        String newAccessToken = Jwts.builder()
                .setSubject(userDetails.getData().getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_EXPIRATION_TIME))
                .signWith(secretKey)
                .compact();

        // Generate new refresh token with the remaining time
        String newRefreshToken = Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getData().getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + remainingTime))
                .signWith(secretKey)
                .compact();

        // Return the new access token
        ReturnToken tokenNew = new ReturnToken();
        tokenNew.setAccessToken(newAccessToken);
        tokenNew.setRefreshToken(newRefreshToken);
        return tokenNew;
    }
    //lay username tu token Nếu token coòn thời gian sông
    public String extractUserName(String token){
        return extractClaims(token,Claims::getSubject);
    }
    //Hàm get userName từ token Nếu token coòn thời gian sông
    public <T> T extractClaims(String token, Function<Claims,T> claimsTFunction){
        return claimsTFunction.apply(
                Jwts.parser()
                        .verifyWith(secretKey)
                        .build()
                        .parseSignedClaims(token)
                        .getPayload());
    }
    //check token còn sử dụng đucợ hay không
    public boolean isTokenvalid(String token,UserDetails userDetails){
        final String userName = extractUserName(token);
        return (userName.equals((userDetails.getUsername())) && !isExpiration(token));
    }

    //token đã hết hạn hay chưa
    public boolean isExpiration (String token){
        return extractClaims(token,Claims::getExpiration).before(new Date());

    }
    public String generateTokenSignup(String id){
        return Jwts.builder()
                .subject(id)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ACCESS_EXPIRATION_TIME))
                .signWith(secretKey)
                .compact();
    }
        public String generateRefreshTokenSignup(String id){
        return Jwts.builder()
                .setSubject(id)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+REFRESH_EXPiRATION_TIME))
                .signWith(secretKey)
                .compact();
    }
}
