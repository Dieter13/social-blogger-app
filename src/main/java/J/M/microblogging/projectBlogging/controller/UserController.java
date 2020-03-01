package J.M.microblogging.projectBlogging.controller;

import J.M.microblogging.projectBlogging.dto.LogInUserDto;
import J.M.microblogging.projectBlogging.dto.UserDto;
import J.M.microblogging.projectBlogging.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    IUserService userService;


    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public String showUser(Model model, @PathVariable Long id) {
        UserDto userDto = userService.getUserById(id);
        model.addAttribute("user", userDto);

        return "redirect:/message";
    }

//    @RequestMapping(value = "/user", method = RequestMethod.GET)
//    public String showAllUsers(Model model) {
//        List<UserDto> usersDto = userService.getAllUsers();
//        model.addAttribute("users", usersDto);
//
//        return "redirect:/message";
//    }



//    @RequestMapping(value = "/user", method = RequestMethod.POST)
//    public String addFollower(@ModelAttribute("newFollower") @Valid PostDto postDto,
//                              BindingResult result){
//
//        if (!result.hasErrors()) { userService.addFollower(followerDto, result);}
//
//        return "redirect:/follower";
//    }

}
