package com.ricardo.oliveira.padelHubAPI.service;

import com.ricardo.oliveira.padelHubAPI.dto.LoginDTO;
import com.ricardo.oliveira.padelHubAPI.dto.RegisterDTO;
import com.ricardo.oliveira.padelHubAPI.model.User;
import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(Integer id);

    User save(User user);

    User signup(RegisterDTO registerDTO);

    User authenticate(LoginDTO loginDTO);
}
