package util;

import model.Food;

import java.util.*;
import java.util.stream.Collectors;

public class FileService {

    public static final Random RND = new Random();

    public void run() {
        printGoods();
        userInterface();
    }

    public List<Food> getGoods() {
        List<Food> list = new ArrayList<>();
        list.add(new Food("Chips", 50, "a"));
        list.add(new Food("Water", 25, "b"));
        list.add(new Food("Coca-Cola", 45, "c"));
        list.add(new Food("Sandwich", 100, "d"));
        list.add(new Food("Salad", 80, "e"));
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

    public void userInterface() {
        print("Enter your money witch you have: ");
        int userMoney = new Scanner(System.in).nextInt();
        var filtered = getRandomGoods().stream()
                        .filter((item) -> item.getCost() <= userMoney).toList();
        print("What do want to choose: \n");
        for (int i = 0; i < filtered.size(); i++) {
            System.out.printf("%s - buy |%s|%n", filtered.get(i).getCode(), filtered.get(i).getName());
        }
    }

    public int getUserMoney() {
        print("Enter your money witch you have: ");
        return new Scanner(System.in).nextInt();
    }

    public void print(String message) {
        System.out.printf(message);
    }
}
