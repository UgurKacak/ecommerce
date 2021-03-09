package com.ugur.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "ec_users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "User Api model documentation", description = "Model")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "Unique id field of user object")
    private Integer id;


    @Column(name = "firstname")
    @ApiModelProperty(value = "firstName field of user object")
    private String firstName;

    @Column(name = "lastname")
    @ApiModelProperty(value = "lastName field of user object")
    private String lastName;

    @Column(name = "email")
    @ApiModelProperty(value = "email field of user object")
    private String email;

    @Column(name = "password")
    @ApiModelProperty(value = "password field of user object")
    private String password;

    @Column(name = "role")
    @ApiModelProperty(value = "role field of user object")
    private String role;

}
