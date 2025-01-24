package com.quiz_backend.api_gateway.routes;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class routes {

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
        HandlerFunctions.http("http://localhost:8081")).build();
    }
}
