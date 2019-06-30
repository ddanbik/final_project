package pl.sda.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    private Long id;

    @Column
    private String username;

    @Column(name = "password_hash")
    private String password;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "user_role",
            joinColumns = { @JoinColumn(name = "USER_ID")},
            inverseJoinColumns = { @JoinColumn(name = "ROLE_ID")}
    )
    private List<Role> roles;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

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
