package pl.sda.model;

import javax.persistence.Column;
import javax.persistence.Id;

public class Role {



    @Id
    @Column(name = "ROLE")
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

}
