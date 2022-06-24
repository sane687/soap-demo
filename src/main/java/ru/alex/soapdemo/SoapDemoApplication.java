package ru.alex.soapdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.alex.soapdemo.repository.UserRepository;
import ru.alex.soapdemo.service.UserService;

@SpringBootApplication
public class SoapDemoApplication {

    static UserService userService;

    @Autowired
    public SoapDemoApplication(UserService userService) {
        this.userService = userService;
    }

    public static void main(String[] args) {

        SpringApplication.run(SoapDemoApplication.class, args);

        System.out.println(userService.findUserByUsername("Дмитрий").getUsername());


    }

}
