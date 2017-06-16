package com.example.sviostali.e_dnevnik.sugarclasses;

import com.orm.SugarRecord;


public class studentsubject extends SugarRecord {
    public usersugar user;
    public subjects subject;

    public studentsubject(String user, subjects sub) {
    }

    public studentsubject(usersugar user, subjects subject) {
        this.user = user;
        this.subject = subject;
    }

    public usersugar getUser() {
        return user;
    }

    public void setUser(usersugar user) {
        this.user = user;
    }

    public subjects getSubject() {
        return subject;
    }

    public void setSubject(subjects subject) {
        this.subject = subject;
    }
}
