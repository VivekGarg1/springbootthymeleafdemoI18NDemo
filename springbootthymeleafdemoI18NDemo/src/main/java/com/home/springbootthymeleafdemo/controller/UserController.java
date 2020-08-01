package com.home.springbootthymeleafdemo.controller;

import com.home.springbootthymeleafdemo.model.User;
import com.home.springbootthymeleafdemo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    public static final Logger logger= LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/createuser")
    public ModelAndView createUserView(){
        ModelAndView modelAndView=new ModelAndView("createUser");
        modelAndView.addObject("user",new User());
        modelAndView.addObject("allProfiles",getProfiles());
        return modelAndView;
    }

    private List<String> getProfiles() {
        List<String> list=new ArrayList<>();
        list.add("Doctor");
        list.add("Engineer");
        list.add("Teacher");
        list.add("Businessman");
        return list;
    }

    @PostMapping("/createuser")
    public ModelAndView createUser(@Valid User user, BindingResult result){
        ModelAndView modelAndView=new ModelAndView();
        if(result.hasErrors()){
            logger.info("Validation error while submitting form...");
            modelAndView.setViewName("createUser");
            modelAndView.addObject("user",user);
            modelAndView.addObject("allProfiles",getProfiles());
            return modelAndView;
        }
        userService.addUser(user);
        modelAndView.addObject("allUsers",userService.getAllUsers());
        modelAndView.setViewName("userInfo");
        logger.info("User submitted successfully...");
        return modelAndView;
    }
    
}
