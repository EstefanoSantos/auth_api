package br.com.auth_api.controllers;

import br.com.auth_api.controllers.dto.CreateUserRequest;
import br.com.auth_api.entities.User;
import br.com.auth_api.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus
    @PostMapping(value = "/newUser")
    public ResponseEntity<Void> createUser(@RequestBody CreateUserRequest request) {
        userService.createUser(request);

        return ResponseEntity.ok().build();
    }

    @ResponseBody
    @GetMapping("/user/{username}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Optional<User>> getUser(@PathVariable("username") String username) {
        var user = userService.getUser(username);

        return ResponseEntity.ok(user);
    }

    @GetMapping(value = "/users")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<List<User>> listUsers() {
        var users = userService.getUsers();
        return ResponseEntity.ok(users);
    }
}
