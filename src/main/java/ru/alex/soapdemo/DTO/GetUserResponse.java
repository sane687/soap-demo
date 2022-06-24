package ru.alex.soapdemo.DTO;

import ru.alex.soapdemo.model.User;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "user"
})
@XmlRootElement(name = "getUserResponse")
public class GetUserResponse {
    public GetUserResponse() {
    }

    @XmlElement(name = "user")
    private UserDTO user;

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO value) {
        this.user = value;
    }
}
