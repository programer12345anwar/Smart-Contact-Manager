package com.smart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.servlet.http.HttpSession;


import com.smart.entities.User;
import com.smart.helper.Message;
import com.smart.repository.UserRepository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Controller
public class HomeController {

    @Autowired
    UserRepository userRepository;

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

    @GetMapping("/signup")
    public String signup(Model model,HttpSession session){
        model.addAttribute("title","Register - Smart Contact Manager");
        model.addAttribute("user",new User());
         // Clear message from session
        session.removeAttribute("message");

        return "signup";//signup.html
    }

    //this handler is for registering a new user

    @PostMapping("/do_register")
    public String registerUser(@ModelAttribute("user") User user ,@RequestParam(value = "agreement",defaultValue = "false") boolean agreement ,BindingResult result1,Model model,HttpSession session){


       try{
        if(!agreement){
            System.out.println("You have not agreed the terms and condition");
            throw new Exception("You have not agreed the terms and condition");
        }

        if(result1.hasErrors()){
            System.out.println("Error in registration");
            model.addAttribute("user",user);
            return "signup";
        }
        user.setRole("ROLE_USER");
        user.setEnabled(true);
        System.out.println("Agreement "+agreement);
        System.out.println("user "+user);
        User result= this.userRepository.save(user);
        model.addAttribute("user",result);
        session.setAttribute("message", new Message("Successfully registered !!","alert-success"));
        return "signup";//signup.html
       }
       catch(Exception e){
        e.printStackTrace();
        model.addAttribute("user",user);
        session.setAttribute("message", new Message("Something went wrong !!"+e.getMessage(),"alert-danger"));
        return "signup";
       }
       
         
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
