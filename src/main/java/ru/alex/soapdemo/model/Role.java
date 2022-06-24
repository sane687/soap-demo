package ru.alex.soapdemo.model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 * Класс описывающий сущность роли в базе данных
 */

@Entity
@Table(name = "roles")
public class Role {
    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(name = "name")
    private String name;


    @ManyToMany(mappedBy = "roles")
    private List<User> users;



    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger value) {
        this.id = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public List<User> getUsers() {
        if (users == null) {
            users = new ArrayList<User>();
        }
        return this.users;
    }

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                '}';
    }

}
