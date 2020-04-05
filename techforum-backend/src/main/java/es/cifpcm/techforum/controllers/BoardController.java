package es.cifpcm.techforum.controllers;

import es.cifpcm.techforum.models.comments.Comment;
import es.cifpcm.techforum.security.services.comments.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/all")
    public List<Comment> allAccess() {
        return commentService.findAll();
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMINISTRATOR')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/moderator")
    @PreAuthorize("hasRole('MODERATOR')")
    public String moderatorAccess() {
        return "Moderator Board.";
    }

    @GetMapping("/administrator")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public String adminAccess() {
        return "Administrator Board.";
    }
}