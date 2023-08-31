package ru.ibs.task4.spring;

public class Food {
    int id;
    String name;
    String type;
    int exotic;

    public Food(int id, String name, String type, int exotic) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.exotic = exotic;
    }

    public Food() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getExotic() {
        return exotic;
    }

    public void setExotic(int exotic) {
        this.exotic = exotic;
    }
}
