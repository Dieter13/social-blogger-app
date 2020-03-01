package J.M.microblogging.projectBlogging.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeResource {



    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showStartPage() {
        return "startPage";
    }


//    @RequestMapping(value = "/loginButton", method = RequestMethod.GET)
//    public String showLoginPage() {
//        return "redirect:/login";
//    }
//
//    @RequestMapping(value = "/registerButton", method = RequestMethod.GET)
//    public String showRegistrationPage() {
//        return "redirect:/registration";
//    }





//
//    @GetMapping("/admin")
//    public String admin(){
//        return("<h1>Welcome admin</h1>");
//    }


}
