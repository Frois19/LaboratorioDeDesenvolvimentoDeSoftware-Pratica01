package com.company.user.type;

import com.company.user.User;

public class Secretary extends User {

    public Secretary(String name, String email, String password, String registry) {
        super(name, email, password, registry);
    }

    public Secretary(String name, String email, String password, String registry, String status) {
        super(name, email, password, registry);
        setTextStatus(status);
    }

    public String textFile() {
        return "*" + getRegistry() + ",;" +
                getName() + ",;" +
                getEmail()+ ",;" +
                getPassword() + ",;" +
                getTextStatus() + ",;" + "\n";
    }

    @Override
    public String toString() {
        return "\nUser Information:" +
                "\n\t- Name: " + getName() +
                "\n\t- Registry: " + getRegistry() +
                "\n\t- E-mail:" + getEmail() +
                "\n\t- Status: " + getStatus()+ "\n";
    }



}
