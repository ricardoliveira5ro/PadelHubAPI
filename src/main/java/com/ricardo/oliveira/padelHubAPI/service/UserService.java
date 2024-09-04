package com.ricardo.oliveira.padelHubAPI.service;

import com.ricardo.oliveira.padelHubAPI.dto.LoginDTO;
import com.ricardo.oliveira.padelHubAPI.dto.RegisterDTO;
import com.ricardo.oliveira.padelHubAPI.model.User;

public interface UserService {

    User signup(RegisterDTO registerDTO);

    User authenticate(LoginDTO loginDTO);
}
