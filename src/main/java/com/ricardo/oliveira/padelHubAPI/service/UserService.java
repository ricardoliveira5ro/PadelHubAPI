package com.ricardo.oliveira.padelHubAPI.service;

import com.ricardo.oliveira.padelHubAPI.dto.request.LoginRequestDTO;
import com.ricardo.oliveira.padelHubAPI.dto.request.SignupRequestDTO;
import com.ricardo.oliveira.padelHubAPI.model.User;

public interface UserService {

    User signup(SignupRequestDTO signupRequestDTO);

    User authenticate(LoginRequestDTO loginDTO);
}
