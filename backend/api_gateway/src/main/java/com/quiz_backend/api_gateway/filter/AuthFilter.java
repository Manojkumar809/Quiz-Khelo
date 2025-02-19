package com.quiz_backend.api_gateway.filter;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.HandlerFilterFunction;
import org.springframework.web.servlet.function.HandlerFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;
import com.quiz_backend.api_gateway.feign.GatewayInterface;

@Component
public class AuthFilter implements HandlerFilterFunction<ServerResponse, ServerResponse>{

    @Autowired
    private GatewayInterface gatewayInterface;

    @SuppressWarnings("null")
    @Override
    public ServerResponse filter(ServerRequest request, HandlerFunction<ServerResponse> next) throws Exception {
        try {
            List<String> authHeader = request.headers().header("Authorization");
            if(authHeader.size() == 0){
                return ServerResponse.status(HttpStatus.BAD_REQUEST).body("No Token Found");
            }
            boolean isAuthorized = gatewayInterface.authorizeToken(authHeader.get(0)).getBody();
            if(!isAuthorized){
                return ServerResponse.status(HttpStatus.UNAUTHORIZED)
                .body("Un authorized request --from filter");
            }
            return next.handle(request);
        } catch (Exception e) {
            System.out.println(e);
            return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    
}
