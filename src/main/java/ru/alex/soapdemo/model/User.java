package ru.alex.soapdemo.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 * Класс описывающий сущность пользователя в базе данных
 */
@Entity
@Table(name = "users")
public class User {
    public User() {
    }

    @Id
    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "username")
    private String username;


    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_login"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;


    public String getUsername() {
        return username;
    }

    public void setUsername(String value) {
        this.username = value;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String value) {
        this.login = value;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String value) {
        this.password = value;
    }

    public List<Role> getRoles() {
        if (roles == null) {
            roles = new ArrayList<Role>();
        }
        return this.roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
