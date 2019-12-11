package com.mxz.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @Description
 * @Date 2019/12/11 16:59
 * @Author mxz
 */
@Data
@Document(collection = "user")
public class User implements Serializable {
    private static final long serialVersionUID = -7257487638617643262L;

    private String username;

    private String password;

    private String sex;

    private Integer age;

    private String email;
}
