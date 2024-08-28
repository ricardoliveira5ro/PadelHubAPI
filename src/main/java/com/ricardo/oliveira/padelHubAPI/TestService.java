package com.ricardo.oliveira.padelHubAPI;

import java.util.List;

public interface TestService {

    List<User> findAll();

    User findById(Integer id);
}
