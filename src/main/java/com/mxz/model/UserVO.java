/*
package com.mxz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

*/
/**
*@Description 
*@author mxz
*2018-07-27
**//*

@Entity
@Table(name = "users")
@Data
public class UserVO extends BaseVO {
    
    @Column(nullable = true,length = 20)
    private String userName;
    @Column(nullable = true,length = 20)
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private Boolean isActive;
    private Date createdDate;
    private Long createdBy;
    private Date lastModifiedDate;
    private Long lastModifiedBy;
    
    public UserVO(String userName, String password, String email, String firstName) {
        super();
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
    }
}
*/
