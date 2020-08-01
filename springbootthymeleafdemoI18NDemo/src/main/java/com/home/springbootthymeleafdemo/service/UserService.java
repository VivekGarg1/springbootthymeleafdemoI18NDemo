package com.home.springbootthymeleafdemo.service;

import com.home.springbootthymeleafdemo.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private List<User> userList=new ArrayList<>();
    public void addUser(User user) {
        userList.add(user);
    }

    public List<User> getAllUsers() {
        return  userList;
    }
}
