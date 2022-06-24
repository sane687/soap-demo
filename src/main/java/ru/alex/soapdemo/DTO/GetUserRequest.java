package ru.alex.soapdemo.DTO;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "username",
})
@XmlRootElement(name = "getUserRequest")
public class GetUserRequest {
    public GetUserRequest() {
    }

    @XmlElement
    private String username;


    public String getUsername() {
        return username;
    }

    public void setUsername(String value) {
        this.username = value;
    }

}
