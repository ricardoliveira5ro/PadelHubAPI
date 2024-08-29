package com.ricardo.oliveira.padelHubAPI.service;

import com.ricardo.oliveira.padelHubAPI.model.User;
import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(Integer id);
}
