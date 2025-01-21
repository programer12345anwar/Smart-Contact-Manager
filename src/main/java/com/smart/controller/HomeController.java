package com.smart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

 import org.springframework.ui.Model;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("title","Home Smart- Contact Manager");
        return "home";//home.html
    }

    @GetMapping("/about")
    public String about(Model model){
        model.addAttribute("title","About - Smart Contact Manager");
        return "about";//about.html
    }

    //=============================================
    // @Autowired
    // private UserRepository userRepository;

    // @GetMapping("/test")//we don't need to return html ,so for that need to use ResponseBody
    // @ResponseBody
    // public String test(){
    //    User user= new User();
    //    user.setName("Md Anwar Alam");
    //    user.setEmail("mdanwar40212@gmail.com");
    //    user.setAbout("I am a full stack java developer");
    //    user.setPassword("Anwar@123");
    //    user.setRole("developer");
    //    user.setImageUrl("/Spring-Learning-Project/Spring-Boot-Projects/thymeleaf_practice/src/main/resources/static/image/img3.jpg");
    //    user.setEnabled(true);

    //    userRepository.save(user);
    //     return "working";
    //}
    
}
