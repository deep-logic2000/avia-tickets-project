package org.main;

import java.io.Serializable;

public class User implements Serializable {
    String name;
    String surName;

    public User(String name, String surName) {
        this.name = name;
        this.surName = surName;
    }
}
