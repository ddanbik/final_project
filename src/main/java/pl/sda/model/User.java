package pl.sda.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "USER")
public class User {

    @Id
    @Column(name = "USER_ID")
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

//    @ManyToMany(cascade = {CascadeType>ALL})
//    @JoinTable{
//
//
//    }

    private List<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
