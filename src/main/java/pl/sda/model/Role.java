package pl.sda.model;

import javax.persistence.*;

@Entity //
@Table(name = "ROLE")   //
public class Role {

    @Id
    @GeneratedValue   //
    @Column(name = "ROLE_ID")  //
    private Long id;

    @Column(name = "role_name")     //
    private String name;

//    @Column(name = "password_hash")
//    private String password;

}
