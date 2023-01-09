package model;

public class Food {
    private String name;
    private int cost;
    private int id;

    public Food(String name, int cost, int id) {
        this.name = name;
        this.cost = cost;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", cost=" + cost +
                ", id=" + id +
                '}';
    }
}
