package ru.alex.soapdemo.DTO;


import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "success",
        "errors"
})
@XmlRootElement(name = "updateUserResponse")
public class UpdateUserResponse {

    @XmlElement
    private boolean success;
    @XmlElement(name = "error", type = String.class)
    @XmlElementWrapper(name="errors")
    private List<String> errors;

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
