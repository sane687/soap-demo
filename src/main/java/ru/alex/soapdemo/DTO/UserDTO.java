package ru.alex.soapdemo.DTO;

import ru.alex.soapdemo.model.Role;
import javax.xml.bind.annotation.*;
import java.util.Set;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "user")
public class UserDTO {
    public UserDTO() {
    }

    @XmlElement
    private String login;

    @XmlElement
    private String username;

    @XmlElement
    private String password;

    @XmlElement(name = "role")
    @XmlElementWrapper(name="roles")
    private Set<Role> roles;

    public UserDTO(String login, String username, String password) {
        this.login = login;
        this.username = username;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
