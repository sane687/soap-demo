package ru.alex.soapdemo.DTO;


import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "userList"
})
@XmlRootElement(name = "getAllUsersResponse")
public class GetAllUsersResponse {

    @XmlElement(name = "user")
    @XmlElementWrapper(name = "users")
    List<UserDTO> userList;

    public List<UserDTO> getUserList() {
        return userList;
    }

    public void setUserList(List<UserDTO> userList) {
        this.userList = userList;
    }
}
