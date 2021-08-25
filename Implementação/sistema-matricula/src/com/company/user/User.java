package com.company.user;

public abstract class User {

    protected String name;
    protected String email;
    protected String password;
    protected String registry;
    protected StatusUser status;

    public User(String name, String email, String password, String registry) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.registry = registry;
        this.status = StatusUser.ACTIVE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegistry() {
        return registry;
    }

    public void setRegistry(String registry) {
        this.registry = registry;
    }

    public StatusUser getStatus() {
        return status;
    }

    public String getTextStatus() {
        if(getStatus()==StatusUser.ACTIVE){
            return "ACTIVE";
        }else{
            //StatusUser.INACTIVE
            return "INACTIVE";
        }
    }

    public void setStatus(StatusUser status) {
        this.status = status;
    }

    public void setTextStatus(String status) {
        if(status.equals("ACTIVE")){
            this.status=StatusUser.ACTIVE;
        }else{
            this.status=StatusUser.INACTIVE;
        }
    }

    public boolean changePassword(String password01, String password02) {
        if (password01 == password02){
            setPassword(password01);
            System.out.println("Password change successful.");
            return true;
        } else {
            System.out.println("ATTENTION: Password change error.");
            return false;
        }
    }

    public void inactiveUser() {
        setStatus(StatusUser.INACTIVE);
    }

    public void activeUser() {
        setStatus(StatusUser.ACTIVE);
    }

    public String textFile() {
        return getRegistry() + ";" +
                getName() + ";" +
                getEmail() + ";" +
                getPassword() + ";" +
                getTextStatus() + "\n";
    }

    @Override
    public String toString() {
        return "\nUser Information:" +
                "\n\t- Name: " + getName() +
                "\n\t- Registry: " + getRegistry() +
                "\n\t- E-mail:" + getEmail() +
                "\n\t- Status: " + getStatus();
    }

}
