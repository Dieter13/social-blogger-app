package J.M.microblogging.projectBlogging.controller;


import J.M.microblogging.projectBlogging.dto.CommentDto;
import J.M.microblogging.projectBlogging.dto.LikeDto;
import J.M.microblogging.projectBlogging.dto.PostDto;
import J.M.microblogging.projectBlogging.dto.UserDto;
import J.M.microblogging.projectBlogging.service.ICommentService;
import J.M.microblogging.projectBlogging.service.ILikeService;
import J.M.microblogging.projectBlogging.service.IPostService;
import J.M.microblogging.projectBlogging.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PostController{

    private final IPostService postService ;
    private final ICommentService commentService;
    private final ILikeService likeService;
    private final UserService userService;

    public PostController(IPostService postService, ICommentService iCommentService, ILikeService iLikeService, UserService userService) {
        this.postService = postService;
        this.commentService = iCommentService;
        this.likeService = iLikeService;
        this.userService = userService;
    }

    @RequestMapping(value = "/message/{id}", method = RequestMethod.GET)
    public String showMessage(Model model, @PathVariable Long id) {

        PostDto postDto = postService.getPostById(id);
        List<CommentDto> commentDtos =commentService.findAllCommentsFromPost(id);
        Long countLikes=likeService.countLikesForPost(id);

        model.addAttribute("post", postDto);
        model.addAttribute("comments", commentDtos);
        model.addAttribute("countLikes", countLikes);

        return "postWithCommentsPage";
    }

    @RequestMapping(value = "/message", method = RequestMethod.GET)
    public String showAllPosts(Model model) {
        List<PostDto> postsDto = postService.findAllPosts();
        if(postsDto.size() > 0){
            model.addAttribute("posts", postsDto);
        }
        List<UserDto> usersDto = userService.getAllUsers();
        model.addAttribute("users", usersDto);

        return "homePage";
    }



    @RequestMapping(value = "/message", method = RequestMethod.POST)
        public String addMessageinPost(@ModelAttribute("newMessage") @Valid PostDto postDto,
                                        BindingResult result){

        if (!result.hasErrors()) {
            createPostByUser(postDto, result);
        }

        return "redirect:/message";
    }


    @RequestMapping(value = "/like/{postId}", method = RequestMethod.POST)
    public String addLike( @PathVariable Long postId){

           likeService.newLike(postId);

        return "redirect:/message#post_" + postId;
    }

    @RequestMapping(value = "/likee/{postId}", method = RequestMethod.POST)
    public String addLikeinsecondPage( @PathVariable Long postId, Model model){

        likeService.newLike(postId);

        Long likesCount = likeService.countLikesForPost(postId);
        model.addAttribute("likesCount", likesCount);
       // return  "redirect:/message";
        return "redirect:/message/{postId}";
    }


    @RequestMapping(value = "/follow/{userId}", method = RequestMethod.POST)
    public String followUser( @PathVariable("userId") Long id){

        userService.addFollower(id);

        return "redirect:/message";
    }

    @RequestMapping(value = "/unfollow/{userId}", method = RequestMethod.POST)
    public String unfollowUser( @PathVariable("userId") Long id){

        userService.removeFollower(id);

        return "redirect:/message";
    }


    @RequestMapping(value = "user/users", method = RequestMethod.GET)
    public String showAllUsers(Model model) {
        List<UserDto> usersDto = userService.getAllUsers();
        model.addAttribute("users", usersDto);

        return "homePage";
    }




    private void createPostByUser(PostDto postDto, BindingResult result) {

        postService.registerNewPost(postDto);
    }
}
