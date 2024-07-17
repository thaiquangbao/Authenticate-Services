package com.iuh.api_gateway.filter;



import com.iuh.api_gateway.service.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;


@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {
    public static class Config {
    }
    @Autowired
    private RouterValidator routerValidator;
    @Autowired
    private JWTUtils jwtUtils;
    public AuthFilter(){
        super(Config.class);
    }
    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = null;
           if (routerValidator.isSecured.test(exchange.getRequest())){
               if (!exchange.getRequest().getHeaders().containsKey("accessToken") ||
                       !exchange.getRequest().getHeaders().containsKey("refreshToken")) {
                   throw new RuntimeException("Missing accessToken or refreshToken header");
               }
               String accessToken = exchange.getRequest().getHeaders().get("accessToken").get(0);
               String refreshToken = exchange.getRequest().getHeaders().get("refreshToken").get(0);

               System.out.println("AccessToken: " + accessToken);
               System.out.println("RefreshToken: " + refreshToken);
               try {
                   // Validate accessToken. You might also want to check refreshToken validity or use it to refresh the accessToken
                   jwtUtils.validateToken(accessToken);
                   // Assuming generateToken uses the accessToken to generate or validate a token
                   request = exchange.getRequest()
                           .mutate()
                           .header("loggedUser", jwtUtils.generateToken(accessToken))
                           .build();
               } catch (Exception e) {
                   ServerHttpResponse response = exchange.getResponse();
                   response.setStatusCode(org.springframework.http.HttpStatus.UNAUTHORIZED);
               }
           }
            return chain.filter(exchange.mutate().request(request).build());
        });
    }
}
