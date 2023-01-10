package util;

import model.Food;

import java.util.*;
import java.util.stream.Collectors;

public class FileService {

    private int ownMoney;

    public static final Random RND = new Random();

    public void run() {
        printGoods();
        userInterface();
    }

    public void userInterface() {
        Scanner scanner = new Scanner(System.in);
        print("Enter your money witch you have: ");
        int userMoney = scanner.nextInt();
        setOwnMoney(getOwnMoney() + userMoney);
        get(scanner);
    }

    public void get(Scanner scanner) {
        printVersionsOfGoods(getFilteredGoods(), scanner);
    }

    public void printVersionsOfGoods(List<Food> filtered, Scanner scanner) {
        print("What do want to choose: \na - add more money \n");
        for (int i = 0; i < filtered.size(); i++) {
            if (getOwnMoney() < 25) {
                print("You can't buy any of goods. You need to put more money than " + getOwnMoney());
            } else {
                System.out.printf("%s - buy |%s|%n", filtered.get(i).getCode(), filtered.get(i).getName());
            }
        }
        print("x - cancel: ");
        String userChoice = scanner.next().toLowerCase();
        chooseVersion(userChoice, filtered, scanner);
    }

    public void chooseVersion(String userChoice, List<Food> list, Scanner scanner) {
        var newList = list.stream().map(Food::getCode).toList();
        switch (userChoice) {
            case "a":
                addMoney(list, scanner);
                break;
            case "b","c","d","e","f":
                giveFood(newList, userChoice);
            case "x":
                scanner.close();
                break;

        }
    }

    public void giveFood(List<String> list, String userChoice) {
        for (int i = i ) {

        }
    }

    public void addMoney(List<Food> list, Scanner scanner) {
        print("Enter how much money do want to put the machine: ");
        int plusMoney = new Scanner(System.in).nextInt();
        setOwnMoney(getOwnMoney() + plusMoney);
        System.out.println(getOwnMoney());
        get(scanner);
    }

    public List<Food> getFilteredGoods() {
        return getRandomGoods().stream()
                .filter((item) -> item.getCost() <= getOwnMoney()).toList();
    }

    public List<Food> getGoods() {
        List<Food> list = new ArrayList<>();
        list.add(new Food("Chips", 50, "b"));
        list.add(new Food("Water", 25, "c"));
        list.add(new Food("Coca-Cola", 45, "d"));
        list.add(new Food("Sandwich", 100, "e"));
        list.add(new Food("Salad", 80, "f"));
        return list;
    }

    public List<Food> getRandomGoods() {
        List<Food> goods = new ArrayList<>();
        LinkedList<Integer> linkedList = new LinkedList<>();
        int result;
        for (int i = 0; i < getGoods().size(); i++) {
            do result = RND.nextInt(getGoods().size());
            while (linkedList.contains(result));
            linkedList.add(result);
            goods.add(getGoods().get(result));
        }
        return goods;
    }

    public void printGoods() {
        print("What we have in the machine \n");
        print("-- price -- | -- food --\n");
        getRandomGoods().stream()
                .collect(Collectors.groupingBy(Food::getCost
                        , Collectors.mapping(Food::getName,
                                Collectors.toList()))).forEach((k, v) -> System.out.printf("%6s --> %12s%n", k, v));
    }

    public void print(String message) {
        System.out.printf(message);
    }

    public int getOwnMoney() {
        return ownMoney;
    }

    public void setOwnMoney(int ownMoney) {
        this.ownMoney = ownMoney;
    }
}
