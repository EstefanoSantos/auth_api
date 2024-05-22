package br.com.auth_api.services;

import br.com.auth_api.controllers.dto.CreateUserRequest;
import br.com.auth_api.entities.Role;
import br.com.auth_api.entities.User;
import br.com.auth_api.repositories.RoleRepository;
import br.com.auth_api.repositories.UserRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder encoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.roleRepository = roleRepository;
    }

    public void createUser(CreateUserRequest createUserRequest) {
        var user = userRepository.findByUsername(createUserRequest.username());

        if (user.isEmpty()) {
            Role role = roleRepository.findByName("USER");
            User createUser = new User();
            createUser.setUsername(createUserRequest.username());
            createUser.setPassword(encoder.encode(createUserRequest.password()));
            createUser.setRoles(List.of(role));
            userRepository.save(createUser);
        } else {
            throw new EntityExistsException("Essa conta já existe!");
        }
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
