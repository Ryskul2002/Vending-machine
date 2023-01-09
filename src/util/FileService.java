package util;

import model.Food;

import java.util.*;
import java.util.stream.Collectors;

public class FileService {

    public static final Random RND = new Random();

    public void run() {
        printGoods();
    }

    public List<Food> getGoods() {
        List<Food> list = new ArrayList<>();
        list.add(new Food("Chips", 50, 1));
        list.add(new Food("Water", 25, 2));
        list.add(new Food("Coca-Cola", 45, 3));
        list.add(new Food("Sandwich", 100, 4));
        list.add(new Food("Salad", 80, 5));
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
        print("What we have in the machine");
        print("-- price -- | -- food --\n");
        getRandomGoods().stream()
                .collect(Collectors.groupingBy(Food::getCost
                        , Collectors.mapping(Food::getName,
                                Collectors.toList()))).forEach((k, v) -> System.out.printf("%6s --> %12s%n", k, v));
    }

    public void print(String message) {
        System.out.print(message);
    }
}
