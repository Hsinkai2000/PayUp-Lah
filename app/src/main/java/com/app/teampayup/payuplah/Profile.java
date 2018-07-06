package com.app.teampayup.payuplah;

public class Profile {
    String name;
    int id;
    int budget;

    public Profile(int id, String name, int budget) {
        this.name = name;
        this.id = id;
        this.budget = budget;
    }

    public int getBudget() {

        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
