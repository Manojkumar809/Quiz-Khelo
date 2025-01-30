package com.quiz_backend.api_gateway.routes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;
import com.quiz_backend.api_gateway.filter.AuthFilter;

@Configuration
public class routes {

    @Autowired
    private AuthFilter authFilter;

    @Bean
    RouterFunction<ServerResponse> userRoutes(){
        return GatewayRouterFunctions.route("USER-SERVICE")
        .route(RequestPredicates.path("/user/**"), 
        HandlerFunctions.http("http://localhost:8082")).build();
    }

    @Bean
    RouterFunction<ServerResponse> questionRoutes(){
        return GatewayRouterFunctions.route("QUESTION-SERVICE")
        .route(RequestPredicates.path("/question/**"), 
        HandlerFunctions.http("http://localhost:8080")).build();
    }

    @Bean
    RouterFunction<ServerResponse> quizRoutes(){
        return GatewayRouterFunctions.route("QUIZ-SERVICE")
        .route(RequestPredicates.path("/quiz/**"), 
        HandlerFunctions.http("http://localhost:8081")).filter(authFilter).build();
    }
}
