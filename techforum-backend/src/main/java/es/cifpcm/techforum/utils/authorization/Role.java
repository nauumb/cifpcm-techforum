package es.cifpcm.techforum.utils.authorization;

import es.cifpcm.techforum.models.authorization.ERole;
import es.cifpcm.techforum.repository.authorization.RoleRepository;

import java.util.Set;

public class Role {
    public static void findRole(Set<es.cifpcm.techforum.models.authorization.Role> roles, String role, RoleRepository roleRepository) {
        switch (role) {
            case "administrator":
                es.cifpcm.techforum.models.authorization.Role adminRole = roleRepository.findByName(ERole.ROLE_ADMINISTRATOR)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(adminRole);
                break;

            case "moderator":
                es.cifpcm.techforum.models.authorization.Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(modRole);
                break;

            default:
                es.cifpcm.techforum.models.authorization.Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(userRole);
        }
    }
}
