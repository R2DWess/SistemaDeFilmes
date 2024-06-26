package com.wzzy.virtualmovies.usuarios.login.handler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Optional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.wzzy.virtualmovies.usuarios.login.model.LoginRequest;
import com.wzzy.virtualmovies.usuarios.login.model.LoginResponseDto;
import com.wzzy.virtualmovies.usuarios.login.services.LoginUserService;
import com.wzzy.virtualmovies.usuarios.util.LocalDateAdapter;

public class LoginUserHandler implements HttpHandler {

    private LoginUserService loginUserService;
    private Gson gson;

    public LoginUserHandler(LoginUserService loginUserService) {
        this.loginUserService = loginUserService;
        this.gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("POST".equals(exchange.getRequestMethod()) && "/login".equals(exchange.getRequestURI().getPath())) {
            try {
                String body = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
                LoginRequest loginRequest = gson.fromJson(body, LoginRequest.class);

                Optional<LoginResponseDto> user = loginUserService.login(loginRequest.getEmail(),
                        loginRequest.getPassword());

                String jsonResponse;
                if (user.isPresent()) {
                    jsonResponse = gson.toJson(user.get());
                    exchange.getResponseHeaders().add("Content-Type", "application/json");
                    exchange.sendResponseHeaders(200, jsonResponse.getBytes().length);
                } else {
                    jsonResponse = gson.toJson("Unauthorized");
                    exchange.getResponseHeaders().add("Content-Type", "application/json");
                    exchange.sendResponseHeaders(401, jsonResponse.getBytes().length);
                }

                OutputStream os = exchange.getResponseBody();
                os.write(jsonResponse.getBytes());
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
                String error = "Internal Server Error";
                exchange.sendResponseHeaders(500, error.length());
                OutputStream os = exchange.getResponseBody();
                os.write(error.getBytes());
                os.close();
            }
        } else {
            exchange.sendResponseHeaders(405, -1);
        }
    }
}
