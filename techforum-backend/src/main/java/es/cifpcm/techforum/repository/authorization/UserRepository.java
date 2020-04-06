package es.cifpcm.techforum.repository.authorization;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import es.cifpcm.techforum.models.authorization.User;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}