package com.example.sviostali.e_dnevnik.sugarclasses;


import com.orm.SugarRecord;

import java.util.List;

public class subjects extends SugarRecord {
    String name;
    usersugar user;

    public subjects(){

    }

    public subjects(String name, usersugar user) {
        this.name = name;
        this.user = user;
    }



    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }

    public usersugar getUser() {
        return user;
    }

    public void setUser(usersugar user) {
        this.user = user;
    }
}
