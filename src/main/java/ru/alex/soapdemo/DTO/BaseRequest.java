package ru.alex.soapdemo.DTO;

import javax.xml.bind.annotation.*;
import java.util.List;
import java.util.Set;

/**
 * Базовый класс обработки запросов на добавление и обновление пользователя
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "username",
        "login",
        "password",
        "roles"
})
public class BaseRequest {
    @XmlElement
    private String username;
    @XmlElement
    private String login;
    @XmlElement
    private String password;
    @XmlElement(name = "role", type = RoleDTO.class)
    @XmlElementWrapper(name = "roles")
    private Set<RoleDTO> roles;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDTO> roles) {
        this.roles = roles;
    }
}
