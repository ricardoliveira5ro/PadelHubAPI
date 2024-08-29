package com.ricardo.oliveira.padelHubAPI.repository;

import com.ricardo.oliveira.padelHubAPI.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
