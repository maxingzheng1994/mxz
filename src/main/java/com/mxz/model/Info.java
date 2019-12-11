package com.mxz.model;

import lombok.Data;

import java.io.Serializable;

@Data
//@Document(collection = "info")
public class Info implements Serializable {
    private static final long serialVersionUID = 4494527542566322152L;

    private String username;

    private String description;
}