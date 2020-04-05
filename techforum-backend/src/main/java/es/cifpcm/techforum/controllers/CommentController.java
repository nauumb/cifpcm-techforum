package es.cifpcm.techforum.controllers;


import es.cifpcm.techforum.models.Comment;
import es.cifpcm.techforum.models.Role;
import es.cifpcm.techforum.models.User;
import es.cifpcm.techforum.payload.request.AskRequest;
import es.cifpcm.techforum.payload.response.MessageResponse;
import es.cifpcm.techforum.repository.CommentRepository;
import es.cifpcm.techforum.repository.UserRepository;
import es.cifpcm.techforum.security.services.authorization.UserDetailsImpl;
import org.apache.catalina.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
<<<<<<< HEAD
import java.security.Principal;
import java.time.Instant;
=======
import java.util.Date;
>>>>>>> parent of e668360... Major changes.
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    CommentRepository commentRepository;


    @PostMapping("/ask")
    public ResponseEntity<?> ask (@Valid @RequestBody AskRequest askRequest) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();


<<<<<<< HEAD
        Set<Role> roles = new HashSet<>();
        strRoles.forEach(role -> {
            int cutPoint = role.indexOf("_");
            role = role.substring(cutPoint + 1).toLowerCase();
=======
        /*HashSet<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toCollection(HashSet::new));*/

>>>>>>> parent of e668360... Major changes.

        User user = new User(userDetails.getUsername(),  userDetails.getEmail());

        Comment comment = new Comment("discussion_id_test", "parent_id_test", askRequest.getTitle(),
                askRequest.getText(),"slug_text", "full_slug_test", new Date(),user);


        String info = comment.getDiscussion_id() +  comment.getPosted() + comment.getTitle() +
                comment.getText() + comment.getAuthor().getUsername() + comment.getAuthor().getEmail()+ comment.getAuthor().getId();


        commentRepository.save(comment);

        return ResponseEntity.ok(new MessageResponse(info));
    }


}
