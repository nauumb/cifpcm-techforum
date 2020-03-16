package es.cifpcm.techforum.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import es.cifpcm.techforum.models.ERole;
import es.cifpcm.techforum.models.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}