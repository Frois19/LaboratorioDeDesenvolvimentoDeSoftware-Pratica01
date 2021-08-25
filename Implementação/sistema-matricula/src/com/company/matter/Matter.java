package com.company.matter;

public class Matter {
    protected String name;
    protected String id;
    protected int credits;
    protected MatterType type;

    public Matter(String name, String id, int credits, MatterType type) {
        this.name = name;
        this.id = id;
        this.credits = credits;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public MatterType getType() {
        return type;
    }

    public String getTextType() {
        if(getType()==MatterType.MANDATORY){
            return "MANDATORY";
        } else {
            //MatterType.OPTIONAL
            return "OPTIONAL";
        }
    }

    public void setType(MatterType type) {
        this.type = type;
    }

    public void setTextType(String type) {
        if(type.equals("MANDATORY")){
            this.type=MatterType.MANDATORY;
        } else {
            this.type=MatterType.OPTIONAL;
        }
    }

    public String textFile() {
        return getName() + ";" +
                getId() + ";" +
                getCredits() + ";" +
                getTextType() + ";" + "\n";
    }

    @Override
    public String toString() {
        return "\nMatter Information:" +
                "\n\t- Name: " + getName() +
                "\n\t- Id: " + getId() +
                "\n\t- Credits:" + getCredits() +
                "\n\t- Type: " + getTextType();
    }
}
