package ru.alex.soapdemo.DTO;

import ru.alex.soapdemo.model.User;

import javax.xml.bind.annotation.*;
import java.math.BigInteger;
import java.util.List;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "role")
public class RoleDTO {

    private BigInteger id;

    @XmlElement
    private String name;

    @XmlTransient
    private List<User> users;

    @Override
    public String toString() {
        return "RoleDTO{" +
                "name='" + name + '\'' +
                '}';
    }

    public RoleDTO() {
    }

    public RoleDTO(String name) {
        this.name = name;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
