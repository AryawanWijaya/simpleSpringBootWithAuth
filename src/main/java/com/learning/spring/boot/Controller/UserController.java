package com.learning.spring.boot.Controller;

import com.learning.spring.boot.Model.User;
import com.learning.spring.boot.Service.ProductService;
import com.learning.spring.boot.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private ProductService service;

    @Autowired
    private UserRepository repo;

    @RequestMapping("/indexUser")
    public String viewHomePage(){

        return "indexUser";
    }
    @RequestMapping("/register")
    public String showSignUpForm(Model model){
        model.addAttribute("user", new User());

        return "signup_form";
    }

    @RequestMapping(value ="/process_register",method = RequestMethod.POST)
    public String processRegistration(@ModelAttribute(name = "user") User user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password =encoder.encode(user.getPassword());
        user.setPassword(password);
        repo.save(user);
        return "register_success";
    }
    @RequestMapping(value ="/list_users")
    public String viewUserList(Model model){
        List<User>listUser= repo.findAll();
        model.addAttribute("listUsers",listUser);

        return "users";
    }
}
