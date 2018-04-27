package com.clara.taskdb.model;


import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Size;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @Size(min =1)
    private String text;

    private boolean urgent;
    private boolean completed = false;

    public Task(String s, boolean b, boolean b1) {

    }

}
