package com.company.user.type;

import com.company.user.User;

public class Secretary extends User {

    public Secretary(String name, String email, String password, String id) {
        super(name, email, password, id);
    }

    public Secretary(String name, String email, String password, String id, String status) {
        super(name, email, password, id);
        setTextStatus(status);
    }

    public String textFile() {
        return "*" + getId() + ",;" +
                getName() + ",;" +
                getEmail()+ ",;" +
                getPassword() + ",;" +
                getTextStatus() + ",;" + "\n";
    }

    @Override
    public String toString() {
        return "\nUser Information:" +
                "\n\t- Name: " + getName() +
                "\n\t- Id: " + getId() +
                "\n\t- E-mail:" + getEmail() +
                "\n\t- Status: " + getStatus()+ "\n";
    }



}
