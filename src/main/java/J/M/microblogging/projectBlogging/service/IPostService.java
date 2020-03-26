package J.M.microblogging.projectBlogging.service;

import J.M.microblogging.projectBlogging.dto.PostDto;
import J.M.microblogging.projectBlogging.model.Like;
import J.M.microblogging.projectBlogging.model.Post;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
public interface IPostService  {

    Post registerNewPost(PostDto postDto);

    PostDto getPostById(Long postId);

    List<PostDto> findAllPosts();

    List<PostDto> findAllPostsOfLoggedUser(Long id);

}
