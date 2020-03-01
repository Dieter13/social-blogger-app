package J.M.microblogging.projectBlogging.controller;

import J.M.microblogging.projectBlogging.dto.CommentDto;
import J.M.microblogging.projectBlogging.dto.PostDto;
import J.M.microblogging.projectBlogging.service.ICommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CommentControler {

    private ICommentService iCommentService;

    public CommentControler(ICommentService iCommentService) {
        this.iCommentService = iCommentService;
    }


    @RequestMapping(value = "/comment/{postId}", method = RequestMethod.POST)
    public String addComment(@ModelAttribute("newComment") @Valid CommentDto commentDto,
                             @PathVariable("postId") Long id, BindingResult result){

        if(!result.hasErrors()){
            iCommentService.registerNewComment(id, commentDto);
        }

        return "redirect:/message/{postId}";
    }


}
