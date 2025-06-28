package Self.Splitwise;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class User {
    private final String id;
    private final String name;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() { return id; }
    public String getName() { return name; }
}

class ExpenseManager {
    private final Map<String, User> users = new HashMap<>();
    private final Map<String, Map<String, Double>> balanceSheet = new HashMap<>();

    public void addUser(User user) {
        users.put(user.getId(), user);
        balanceSheet.put(user.getId(), new HashMap<>());
    }

    public void addExpense(Expense expense) {
        if (!expense.isValid()) {
            System.out.println("Please enter a valid expense!!");
            return;
        }

        User creator = expense.getCreator();
        List<Split> splits = expense.getSplits();

        for (Split split : splits) {
            User paidTo = split.getUser();
            double amount = split.getAmount();

            balanceSheet.get(paidTo.getId()).put(creator.getId(), balanceSheet.get(paidTo.getId()).getOrDefault(creator.getId(), 0.0) + amount);
            balanceSheet.get(creator.getId()).put(paidTo.getId(), balanceSheet.get(creator.getId()).getOrDefault(paidTo.getId(), 0.0) - amount);
        }
    }

    public void showBalances() {
        for (String user1 : balanceSheet.keySet()) {
            for (Map.Entry<String, Double> entry : balanceSheet.get(user1).entrySet()) {
                String user2 = entry.getKey();
                double amount = entry.getValue();
                if (amount > 0) {
                    System.out.println(users.get(user1).getName() + " owes " + users.get(user2).getName() + ": ₹" + amount);
                }
            }
        }
    }
}

public class SplitwiseDemo {
    public static void main(String[] args) {
        ExpenseManager manager = new ExpenseManager();

        User u1 = new User("u1", "Alice");
        User u2 = new User("u2", "Bob");
        User u3 = new User("u3", "Charlie");

        manager.addUser(u1);
        manager.addUser(u2);
        manager.addUser(u3);

        List<Split> equalSplits = Arrays.asList(new EqualSplit(u1), new EqualSplit(u2), new EqualSplit(u3));
        double amount = 300;
        for(Split split : equalSplits){
            split.setAmount(amount/ equalSplits.size());
        }
        Expense expense1 = new EqualExpense(u1, 300, equalSplits);
        manager.addExpense(expense1);

        List<Split> percentSplits = Arrays.asList(new PercentSplit(u1, 50), new PercentSplit(u2, 30), new PercentSplit(u3, 20));
        Expense expense2 = new PercentExpense(u2, 1000, percentSplits);
        manager.addExpense(expense2);

        List<Split> exactSplits = Arrays.asList(new ExactSplit(u1, 200), new ExactSplit(u2, 300), new ExactSplit(u3, 400));
        Expense expense3 = new ExactExpense(u3, 900, exactSplits);
        manager.addExpense(expense3);

        manager.showBalances();
    }
}
