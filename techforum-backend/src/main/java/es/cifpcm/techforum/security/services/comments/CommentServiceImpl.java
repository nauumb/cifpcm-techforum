package es.cifpcm.techforum.security.services.comments;

import es.cifpcm.techforum.models.Comment;
import es.cifpcm.techforum.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    /**@Override
    public Optional<Comment> findByCommentTitle(String title) {
        return commentRepository.findByCommentTitle(title);
    }*/

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }
}