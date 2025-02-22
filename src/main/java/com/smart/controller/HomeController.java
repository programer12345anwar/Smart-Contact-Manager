package com.smart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import com.smart.entities.User;
import com.smart.helper.Message;
import com.smart.repository.UserRepository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Controller
public class HomeController {

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

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
    
    /*
     @GetMapping("/form")
    public String showForm(Model model) {
        System.out.println("opening form");
        model.addAttribute("formData", new FormData());
        return "form"; // form.html
    }

    @PostMapping("/process")
    public String processForm(@Valid @ModelAttribute("formData") FormData formData,BindingResult result) {
        if(result.hasErrors()) {
            System.out.println(result);
            return "form";
        }
        System.out.println("Form submitted: " + formData);
        // Add logic to handle form submission, like saving data to a database
        return "success"; // success.html (a confirmation page)
    }
     */

    @GetMapping("/signup")
    public String signup(Model model,HttpSession session){
    	System.out.println("opening form");
        model.addAttribute("title","Register - Smart Contact Manager");
        model.addAttribute("user",new User());
         // Clear message from session
        session.removeAttribute("message");

        return "signup";//signup.html
    }

    //this handler is for registering a new user

    @PostMapping("/do_register")
    public String registerUser(@Valid @ModelAttribute("user") User user ,BindingResult result1,Model model,@RequestParam(value = "agreement",defaultValue = "false") boolean agreement ,HttpSession session){
    		/* */

       try{
        if(!agreement){
            System.out.println("You have not agreed the terms and condition");
            throw new Exception("You have not agreed the terms and condition");
        }

        if(result1.hasErrors()){
            System.out.println("Error in registration");
            System.out.println("ERROR"+ result1.toString());
            model.addAttribute("user",user);
            return "signup";
        }
        
        user.setRole("ROLE_USER");
        user.setEnabled(true);
        user.setImageUrl("default.png");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        
        System.out.println("Agreement "+agreement);
        System.out.println("user "+user);
        User result= this.userRepository.save(user);
     
        System.out.println(result);
        model.addAttribute("user",result);
        session.setAttribute("message", new Message("Successfully registered !!","alert-success"));
        return "redirect:/signin"; // Redirect to signin page after successful registration
        
       }
       catch(Exception e){
        e.printStackTrace();
        model.addAttribute("user",user);
        session.setAttribute("message", new Message("Something went wrong !! " ,"alert-danger"));
        return "signup";
       }
       
         
    }
    
    //signin controller
    
    @GetMapping("/signin")
    public String customLogin(Model model) {
    	model.addAttribute("title","Login Page");
    	return "login";
    	
    }

    // Handling the login POST request (make sure this method exists)
    
    

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
    

