package com.ricardo.oliveira.padelHubAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestServiceImpl implements TestService {

    private TestRepository testRepository;

    @Autowired
    public TestServiceImpl(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @Override
    public List<User> findAll() {
        return testRepository.findAll();
    }

    @Override
    public User findById(Integer id) {
        Optional<User> result = testRepository.findById(id);

        User user = null;

        if (result.isPresent()) {
            user = result.get();
        }
        else {
            throw new RuntimeException("Did not find employee id - " + id);
        }

        return user;
    }
}
