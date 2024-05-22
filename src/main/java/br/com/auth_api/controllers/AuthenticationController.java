package br.com.auth_api.controllers;

import br.com.auth_api.controllers.dto.LoginRequest;
import br.com.auth_api.controllers.dto.LoginResponse;
import br.com.auth_api.security.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping(value = "/auth")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {

        String token = authenticationService.authenticateUser(request);

        return ResponseEntity.ok(new LoginResponse(token));
    }
}
