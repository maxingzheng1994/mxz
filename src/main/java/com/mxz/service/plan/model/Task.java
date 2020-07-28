package com.mxz.service.plan.model;

import lombok.Data;

import javax.persistence.*;

/**
 * TODO: 注释.
 *
 * @author mxz on 2020/07/25 14:42
 */
@Entity
@Table(name = "task")
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

}
