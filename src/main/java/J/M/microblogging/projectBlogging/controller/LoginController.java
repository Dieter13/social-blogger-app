package J.M.microblogging.projectBlogging.controller;

import J.M.microblogging.projectBlogging.dto.LogInUserDto;
import J.M.microblogging.projectBlogging.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @Autowired
    IUserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginForm(Model model) {
        LogInUserDto logInUserDto = new LogInUserDto();
        model.addAttribute("loggedUser", logInUserDto);
        return "login";
    }


}
