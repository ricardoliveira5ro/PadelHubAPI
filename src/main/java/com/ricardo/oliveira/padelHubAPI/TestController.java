package com.ricardo.oliveira.padelHubAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    private TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/users")
    public List<User> findAll() {
        return testService.findAll();
    }

    @GetMapping("/users/{userId}")
    public User findById(@PathVariable int userId) {
        User user = testService.findById(userId);

        if (user == null) {
            throw new RuntimeException("Employee id not found - " + userId);
        }

        return user;
    }
}
