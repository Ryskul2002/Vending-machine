package model;

public class Food {
    private String name;
    private int cost;
    private String code;

    public Food(String name, int cost, String code) {
        this.name = name;
        this.cost = cost;
        this.code = code;
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

    public String getCode() {
        return code;
    }

    public void setId(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", cost=" + cost +
                ", id=" + code +
                '}';
    }
}
