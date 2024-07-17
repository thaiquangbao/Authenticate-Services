package com.iuh.api_gateway.service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import java.util.function.Function;

@Component
public class JWTUtils {
    private final SecretKey secretKey;
    //time Hết Hạn là 1 ngày
    private static final long EXPiRATION_TIME = 2592000000L;

    public JWTUtils(){
        String secretString = "843567893696976453275974432697R634976R738467TR678T34865R6834R8763T478378637664538745673865783678548735687R3";
        byte[] keyByte = Base64.getDecoder().decode(secretString.getBytes(StandardCharsets.UTF_8));
        this.secretKey = new SecretKeySpec(keyByte, "hmacSHA256");
    }
    //tao token
    public void validateToken (String token){
        System.out.println("validateToken "+token);
        Claims rs = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        System.out.println(rs);
    }
    public String generateToken(String token){
        return  ExtractClaims(token,Claims::getSubject);
    }
    public <T> T ExtractClaims(String token, Function<Claims,T> claimsTFunction){
        return claimsTFunction.apply(
                Jwts.parser()
                        .verifyWith(secretKey)
                        .build().parseSignedClaims(token).getPayload());
    }
}
