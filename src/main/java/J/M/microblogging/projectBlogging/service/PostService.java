package J.M.microblogging.projectBlogging.service;

import J.M.microblogging.projectBlogging.dao.PostRepository;
import J.M.microblogging.projectBlogging.dao.UserRepository;
import J.M.microblogging.projectBlogging.dto.PostDto;
import J.M.microblogging.projectBlogging.exceptions.PostNotFoundException;
import J.M.microblogging.projectBlogging.model.Like;
import J.M.microblogging.projectBlogging.model.Post;
import J.M.microblogging.projectBlogging.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class PostService implements IPostService{


    private PostRepository postRepository;
    private UserRepository userRepository;
    private IUserService userService;


    public PostService(PostRepository postRepository, UserRepository userRepository, IUserService userService) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Override
    public Post registerNewPost(PostDto postDto){

                User user = userService.currentUser();
                Post post = new Post();
                post.setMessage(postDto.getMessage());
                //post.setPostStatus(PostStatus.ORIGINAL);
                //post.setPostType(PostType.POST);
                post.setUser(user);
                return postRepository.save(post);
    }

    @Override
    public PostDto getPostById(Long id) {

        Post p = postRepository.findById(id)
                .orElseThrow(()-> new PostNotFoundException("Could not find post with "+ id));

        return new PostDto(p.getId(), p.getMessage(),p.getUser(), p.getCreationDate(), p.getLikes().size());


    }

    @Override
    public List<PostDto> findAllPostsOfLoggedUser(Long id){
        User loggedUser = userService.currentUser();
        return postRepository.findAll()
                .stream()
                .filter(p -> p.getUser().getFollowers().contains(loggedUser.getId()))
                .map(p -> {
                    PostDto pp = new PostDto(
                        p.getId(),
                        p.getMessage(),
                        p.getUser(),
                        p.getCreationDate(),
                        p.getLikes().size()
                    );
                    return pp;
                })
                .sorted(Comparator.comparing(PostDto:: getDataCreation).reversed())
                .collect(Collectors.toList());
    }


    @Override
    public List<PostDto> findAllPosts(){
        return postRepository.findAll()
                .stream()
                .map(p -> {
                    PostDto pp = new PostDto(p.getId(), p.getMessage(),
                            p.getUser(), p.getCreationDate(),p.getLikes().size());
                    return pp;
                })
                .sorted(Comparator.comparing(PostDto:: getDataCreation).reversed())
                .collect(Collectors.toList());
    }


    public void deletePostById(Long id){
        postRepository.deleteById(id);
    }


}
