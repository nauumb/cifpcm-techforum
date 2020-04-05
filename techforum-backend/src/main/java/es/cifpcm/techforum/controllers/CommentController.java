package es.cifpcm.techforum.controllers;

import com.github.slugify.Slugify;
import es.cifpcm.techforum.models.comments.Comment;
import es.cifpcm.techforum.models.authorization.Role;
import es.cifpcm.techforum.models.authorization.User;
import es.cifpcm.techforum.payload.request.comments.AskRequest;
import es.cifpcm.techforum.payload.response.MessageResponse;
import es.cifpcm.techforum.repository.comments.CommentRepository;
import es.cifpcm.techforum.repository.authorization.RoleRepository;
import es.cifpcm.techforum.security.services.authorization.UserDetailsImpl;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static es.cifpcm.techforum.utils.authorization.Role.findRole;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    RoleRepository roleRepository;

    @PostMapping("/ask")
    public ResponseEntity<?> ask (@Valid @RequestBody AskRequest askRequest) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        HashSet<String> strRoles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toCollection(HashSet::new));

        Set<Role> roles = new HashSet<>();
        strRoles.forEach(role -> {
            int cutPoint = role.indexOf("_");
            role = role.substring(cutPoint + 1).toLowerCase();

            findRole(roles, role, roleRepository);
        });

        User user = new User(userDetails.getUsername(),  userDetails.getEmail(), roles);
        ObjectId discussion_id = ObjectId.get();
        String slug =  new Slugify().slugify(askRequest.getTitle());

        Comment comment = new Comment(discussion_id, askRequest.getTitle(),
                askRequest.getText(),slug, "full_slug_test", Instant.now(),user);

        String info = comment.getDiscussion_id().toString() +  comment.getPosted() + comment.getTitle()
                + comment.getText() + comment.getAuthor().getUsername() + comment.getAuthor().getEmail()
                + comment.getAuthor().getId() + comment.getAuthor().getRoles();

        commentRepository.save(comment);

        return ResponseEntity.ok(new MessageResponse(info));
    }
}
