package J.M.microblogging.projectBlogging.service;

import J.M.microblogging.projectBlogging.dao.CommentRepository;
import J.M.microblogging.projectBlogging.dao.PostRepository;
import J.M.microblogging.projectBlogging.dao.UserRepository;
import J.M.microblogging.projectBlogging.dto.CommentDto;
import J.M.microblogging.projectBlogging.model.Comment;
import J.M.microblogging.projectBlogging.model.Post;
import J.M.microblogging.projectBlogging.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService implements ICommentService {

    private PostRepository postRepository;
    private UserRepository userRepository;
    private CommentRepository commentRepository;

    public CommentService(PostRepository postRepository, UserRepository userRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment registerNewComment(Long id, CommentDto commentDto) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String userName = ((UserDetails)principal).getUsername();

            if((userRepository.findByUserName(userName)).isPresent()) {
                User user = (userRepository.findByUserName(userName)).get();
                Post post = (postRepository.findById(id)).get();
                Comment comment = new Comment();
                comment.setComment(commentDto.getComment());
                comment.setUser(user);
                comment.setPost(post);
                return commentRepository.save(comment);
            }
        } else {
            String userName = principal.toString();
        }
        return null;
    }

    @Override
    public List<CommentDto> findAllCommentsFromPost(Long id) {

        return commentRepository.findAllByPostId(id).stream().map(c-> {
            CommentDto cc = new CommentDto(c.getId(),c.getComment(),
                    c.getUser(),c.getPost(), c.getCommentDate());
                    return cc; })
                    .sorted(Comparator.comparing(CommentDto::getDataCreation).reversed())
                    .collect(Collectors.toList());

    }
}
