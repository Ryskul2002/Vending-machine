package util;

import model.Food;

import java.util.*;
import java.util.stream.Collectors;

public class FileService {

    private int ownMoney;
    private boolean pay;
    public static final Random RND = new Random();

    public void run() {
        pay = false;
        printGoods();
        payment();
    }

    public void payment() {
        try {
            Scanner scanner = new Scanner(System.in);
            print("You need to choose how you will pay \n 1 -> with card \n 2 -> with coin: ");
            int answer = new Scanner(System.in).nextInt();
            if (answer == 1) {
                cardOfUser(scanner);
            } else if (answer == 2) {
                userInterface();
            } else {
                System.out.println("didn't find this answer. ");
            }
        } catch (InputMismatchException e) {
            e.printStackTrace();
        }
    }

    public void cardOfUser(Scanner scanner) {
        print("You have to enter the numbers of the card \n Example - 4000123456789010: ");
        String numberOfCard = scanner.nextLine().trim();
        print("You have to enter password of the card \n Example - 345: ");
        String password = scanner.nextLine();
        if (!isAlphaCard(numberOfCard) && !isAlphaPassword(password)) {
            checkCard(numberOfCard, password, scanner);
        } else {
            System.out.println("You should enter correctly. ");
        }
    }

    public void checkCard(String numbersOfCard, String password, Scanner scanner) {
        if (numbersOfCard.length() > 16 || numbersOfCard.length() < 16) {
            print(numbersOfCard + "is wrong");
        } else if (password.length() > 3 || password.length() < 3) {
            print(password + "is wrong");
        } else {
            setOwnMoney(100);
            pay = true;
            get(scanner);
        }
    }

    public boolean isAlphaCard(String numberOfCard) {
        if (numberOfCard == null) {
            return false;
        }
        for (int i = 0; i < numberOfCard.length(); i++) {
            char c = numberOfCard.charAt(i);
            if (!(c >= 'A' && c <= 'Z') && !(c >= 'a' && c <= 'z')) {
                return false;
            }
        }
        return true;
    }

    public boolean isAlphaPassword(String paasword) {
        if (paasword == null) {
            return false;
        }
        for (int i = 0; i < paasword.length(); i++) {
            char c = paasword.charAt(i);
            if (!(c >= 'A' && c <= 'Z') && !(c >= 'a' && c <= 'z')) {
                return false;
            }
        }
        return true;
    }

    public void userInterface() {
        try {
            Scanner scanner = new Scanner(System.in);
            print("Enter your money witch you have: ");
            int userMoney = scanner.nextInt();
            setOwnMoney(getOwnMoney() + userMoney);
            get(scanner);
        } catch (InputMismatchException e) {
            e.printStackTrace();
        }
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
        switch (userChoice) {
            case "a":
                if (this.pay) {
                    print("You cannot, You have card");
                } else {
                    addMoney(scanner);
                }
                break;
            case "b", "c", "d", "e", "f":
                giveFood(userChoice, list);
                break;
            case "x":
                scanner.close();
                break;
            default:
                System.out.println("qwerty");
        }
    }

    public void giveFood(String userChoice, List<Food> filtered) {
        var tr = filtered.stream().filter(el -> el.getCode().equals(userChoice)).toList();
        print("You bought " + tr.stream().map(Food::getName).toList());
    }

    public void addMoney(Scanner scanner) {
        print("Enter how much money do want to put the machine: ");
        int plusMoney = new Scanner(System.in).nextInt();
        setOwnMoney(getOwnMoney() + plusMoney);
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
