package ru.alex.soapdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alex.soapdemo.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository <User, String> {

    List<User> findAll();
    User findByUsername(String username);
    User findByLogin(String login);
    void deleteUserByUsername(String username);
    User findUserByLogin(String login);


}
