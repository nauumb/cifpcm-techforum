package es.cifpcm.techforum.controllers;

import com.github.slugify.Slugify;
import es.cifpcm.techforum.models.comments.Comment;
import es.cifpcm.techforum.models.authorization.Role;
import es.cifpcm.techforum.models.authorization.User;
import es.cifpcm.techforum.payload.request.comments.AskRequest;
import es.cifpcm.techforum.repository.comments.CommentRepository;
import es.cifpcm.techforum.repository.authorization.RoleRepository;
import es.cifpcm.techforum.security.services.authorization.UserDetailsImpl;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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

    @PostMapping("/post")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMINISTRATOR')")
    public ResponseEntity<Comment> post (@Valid @RequestBody AskRequest askRequest) {

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

        User user = new User(userDetails.getId(),userDetails.getUsername(), userDetails.getEmail() ,roles);

        String slug =  new Slugify().slugify(askRequest.getTitle());

        Comment comment;
        if(askRequest.getParent_id() == null){
            ObjectId discussion_id = ObjectId.get();
            comment = new Comment(discussion_id, askRequest.getTitle(),
                    askRequest.getText(),slug, Instant.now(),user);
        }
        else {
            String parent_id = askRequest.getParent_id().toString();
            Optional<Comment> parentComment = commentRepository.findById(parent_id);

            if (parentComment.isPresent()) {
                ObjectId discussion_id = parentComment.get().getDiscussion_id();

                comment = new Comment(discussion_id, askRequest.getParent_id(), askRequest.getTitle(),
                        askRequest.getText(), slug, Instant.now(), user);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>(commentRepository.save(comment), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMINISTRATOR')")
    @PutMapping("/{id}/edit")
    public ResponseEntity<Comment> updateTutorial(@PathVariable("id") String id,@Valid @RequestBody AskRequest askRequest) {
        Optional<Comment> commentData = commentRepository.findById(id);

        if (commentData.isPresent()) {
            Comment _comment = commentData.get();
            _comment.setTitle(askRequest.getTitle());
            _comment.setText(askRequest.getText());

            return new ResponseEntity<>(commentRepository.save(_comment), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMINISTRATOR')")
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") String id) {
        try {
            commentRepository.deleteThread(new ObjectId(id));
            commentRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable("id") String id) {
        Optional<Comment> commentData = commentRepository.findById(id);

        if (commentData.isPresent()) {
            return new ResponseEntity<>(commentData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("{id}/thread")
    public ResponseEntity<List<Comment>> getThreadById(@PathVariable("id") String id) {
        List<Comment> commentData = commentRepository.findThread( new ObjectId(id));

        if (!commentData.isEmpty()) {
            return new ResponseEntity<>(commentData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
