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

    public void setType(MatterType type) {
        this.type = type;
    }
}
