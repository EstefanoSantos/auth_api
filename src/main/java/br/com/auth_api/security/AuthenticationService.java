package br.com.auth_api.security;

import br.com.auth_api.controllers.dto.LoginRequest;
import br.com.auth_api.entities.User;
import br.com.auth_api.repositories.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final JwtService jwtService;

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public AuthenticationService(JwtService jwtService,
                                 UserRepository userRepository,
                                 BCryptPasswordEncoder encoder) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public String authenticateUser(LoginRequest request) {
        var isUser = userRepository.findByUsername(request.username());

        if (isUser.isEmpty() || !isUser.get().isLoginCorrect(request, encoder)) {
            throw new BadCredentialsException("User or password invalid!");
        }

        User userDetails = isUser.get();

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword());

        return jwtService.generateToken(authenticationToken);
    }
}
