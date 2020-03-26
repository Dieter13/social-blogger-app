package J.M.microblogging.projectBlogging.service;

import J.M.microblogging.projectBlogging.dao.LikeRepository;
import J.M.microblogging.projectBlogging.dao.PostRepository;
import J.M.microblogging.projectBlogging.dao.UserRepository;
import J.M.microblogging.projectBlogging.model.Like;
import J.M.microblogging.projectBlogging.model.Post;
import J.M.microblogging.projectBlogging.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class LikeService implements ILikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public LikeService(LikeRepository likeRepository, UserRepository userRepository, PostRepository postRepository) {
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public Like newLike(Long postId) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String userName = ((UserDetails)principal).getUsername();

            if((userRepository.findByUserName(userName)).isPresent()) {
                User user = (userRepository.findByUserName(userName)).get();
                Post post = (postRepository.findById(postId)).get();
                Like like = new Like();
                like.setUser(user);
                like.setPost(post);
                return likeRepository.save(like);
            }
        } else {
            String userName = principal.toString();
        }
        return null;
    }

    @Override
    public Long countLikesForPost(Long postId) {

        return likeRepository.findAllByPostId(postId).stream().count();

    }
}
