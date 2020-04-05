package es.cifpcm.techforum.repository.authorization;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import es.cifpcm.techforum.models.authorization.ERole;
import es.cifpcm.techforum.models.authorization.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}