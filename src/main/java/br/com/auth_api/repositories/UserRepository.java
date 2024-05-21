package br.com.auth_api.repositories;

import br.com.auth_api.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, UUID> {
}
