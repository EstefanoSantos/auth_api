package br.com.auth_api.config;

import br.com.auth_api.entities.Role;
import br.com.auth_api.entities.User;
import br.com.auth_api.repositories.RoleRepository;
import br.com.auth_api.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
public class UserAdminConfig implements CommandLineRunner {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserAdminConfig(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {

        final Role[] roleAdminContainer = new Role[2];

        roleAdminContainer[0] = roleRepository.findByName("ADMIN");

        if (roleAdminContainer[0] == null) {
            Role roleAdm = new Role();
            roleAdm.setName("ADMIN");
            roleAdminContainer[0] = roleRepository.save(roleAdm);

            Role roleUser = new Role();
            roleUser.setName("USER");
            roleAdminContainer[1] = roleRepository.save(roleUser);
        }


        var userAdmin = userRepository.findByUsername("admin");

        userAdmin.ifPresentOrElse(
                user -> {
                    System.out.println("ADMIN and USER already exists");
                },
                () -> {
                    User user = new User();
                    user.setUsername("admin");
                    user.setPassword(passwordEncoder.encode("1234"));
                    user.setRoles(List.of(roleAdminContainer[0]));
                    userRepository.save(user);
                }
        );

    }
}
