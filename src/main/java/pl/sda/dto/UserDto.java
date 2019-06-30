package pl.sda.dto;

import pl.sda.model.AccountStatus;
import pl.sda.model.AccountType;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

public class UserDto implements Serializable {

    @Email(message = "Zły format adresu email")
//    @Pattern(regexp = "^[\\w\\.]+@[\\w]+\\.[\\w]+(\\.[a-z]{2,3})?$", message = "Zły format adresu email")
    private String username;

    @Pattern(regexp = "^.{5,}$", message = "Hasło jest za krótkie")
    private String password;


    private String confirmedPassword;

    @NotEmpty
    private String city;

    @NotEmpty
    private String address;


    private AccountStatus status;

    private AccountType type;

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }



}
