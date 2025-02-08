package com.smart.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smart.entities.Contact;
import com.smart.entities.User;
import com.smart.repository.UserRepository;

@Controller
@RequestMapping("/user")//to access this controller
public class UserController {

	@Autowired
	private UserRepository userRepository;

	//method for adding comman data to the response
    @ModelAttribute
    public void addCommonData(Model model,Principal principal) {
		System.out.println("user dashboard is opened");
		String userName=principal.getName();//it will give email
		System.out.println("USER NAME: "+userName);

		//get the userName using username(Email)

		User user=userRepository.getUserByEmail(userName);
		System.out.println("user "+user);//to string method of User will be called
		model.addAttribute("user",user);
    }
	//dashboard home
	//@Transactional
	 @RequestMapping("/index")
	public String dashboard(Model model, Principal principal) {
        model.addAttribute("title", "User Dashboard");
		return "/normal/user_dashboard";//user_dashboard.html
	}


    //open add form handler

    @RequestMapping("/add-contact")
    public String openAddContactForm(Model model){

        System.out.println("open add contact form");
		model.addAttribute("title", "Add Contact");
		//add new contact object to the model to be used in form
		model.addAttribute("contact", new Contact());//add new contact object to the model to be used in form
        return "normal/add_contact_form";
    }
	  

}


/*Lazy Initialization Exception: Hibernate uses lazy loading by default, meaning it does not load related collections (like contacts) until they are explicitly accessed.
The Spring session closes before the contacts collection is accessed in User.toString(), causing the error.
✅ Solution: Fix Lazy Loading Issue
1️⃣ Option 1: Fetch Contacts Eagerly (Recommended)
Modify your User entity to fetch contacts eagerly instead of lazily:

java
Copy
Edit
@Entity
public class User {
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)  // Change Lazy to EAGER
    private List<Contact> contacts;
}
✅ Why?
This ensures that contacts are fetched immediately when retrieving the User entity, preventing the LazyInitializationException.
2️⃣ Option 2: Initialize the Contacts Collection Before Closing the Session
If you must keep FetchType.LAZY, modify the dashboard method to fetch contacts manually inside the transaction:

java
Copy
Edit
@RequestMapping("/index")
public String dashboard(Model model, Principal principal) {
    System.out.println("user dashboard is opened");
    String userName = principal.getName(); // Get email
    System.out.println("USER NAME: " + userName);

    User user = userRepository.getUserByEmail(userName);
    System.out.println("user " + user); // toString method of User will be called

    // ✅ Force Hibernate to initialize the contacts collection
    Hibernate.initialize(user.getContacts()); 

    return "/normal/user_dashboard"; // user_dashboard.html 
}
✅ Why?
Hibernate.initialize(user.getContacts()) ensures that the contacts collection is loaded before the session closes.
3️⃣ Option 3: Use @Transactional on the Method
Another approach is marking the dashboard method as @Transactional, which ensures that the Hibernate session remains open:

java
Copy
Edit
@Transactional
@RequestMapping("/index")
public String dashboard(Model model, Principal principal) {
    String userName = principal.getName();
    User user = userRepository.getUserByEmail(userName);
    System.out.println("User: " + user);
    return "/normal/user_dashboard";
}
✅ Why?
The @Transactional annotation ensures that Hibernate keeps the session open while executing the method.
🎯 Best Solution for You
If contacts should always be loaded → Use FetchType.EAGER ✅
If contacts are not always needed → Use Hibernate.initialize(user.getContacts())
If needed for multiple relations → Use @Transactional
I recommend Option 1 if contacts is frequently needed, otherwise Option 2. 🚀 Let me know if you need more help! 😊*/
