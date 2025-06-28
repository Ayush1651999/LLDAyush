package Copy.Splitwise;// Splitwise LLD - Java Code

import java.util.*;

// User class
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

// Abstract Split
abstract class Split {
    private final User user;
    private double amount;

    public Split(User user) {
        this.user = user;
    }

    public User getUser() { return user; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) {
        this.amount = amount;
    }
}

class EqualSplit extends Split {
    public EqualSplit(User user) { super(user); }
}

class ExactSplit extends Split {
    public ExactSplit(User user, double amount) {
        super(user);
        setAmount(amount);
    }
}

class PercentSplit extends Split {
    private final double percent;

    public PercentSplit(User user, double percent) {
        super(user);
        this.percent = percent;
    }

    public double getPercent() { return percent; }
}

// Abstract Expense
abstract class Expense {
    protected double amount;
    protected User paidBy;
    protected List<Split> splits;

    public Expense(double amount, User paidBy, List<Split> splits) {
        this.amount = amount;
        this.paidBy = paidBy;
        this.splits = splits;
    }

    public abstract boolean validate();
    public List<Split> getSplits() { return splits; }
    public User getPaidBy() { return paidBy; }
    public double getAmount() { return amount; }
}

class EqualExpense extends Expense {
    public EqualExpense(double amount, User paidBy, List<Split> splits) {
        super(amount, paidBy, splits);
    }

    @Override
    public boolean validate() {
        return true;
    }
}

class ExactExpense extends Expense {
    public ExactExpense(double amount, User paidBy, List<Split> splits) {
        super(amount, paidBy, splits);
    }

    @Override
    public boolean validate() {
        double total = 0;
        for (Split split : splits) {
            total += split.getAmount();
        }
        return total == amount;
    }
}

class PercentExpense extends Expense {
    public PercentExpense(double amount, User paidBy, List<Split> splits) {
        super(amount, paidBy, splits);
        for (Split split : splits) {
            PercentSplit ps = (PercentSplit) split;
            split.setAmount(amount * ps.getPercent() / 100.0);
        }
    }

    @Override
    public boolean validate() {
        double totalPercent = 0;
        for (Split split : splits) {
            PercentSplit ps = (PercentSplit) split;
            totalPercent += ps.getPercent();
        }
        return totalPercent == 100;
    }
}

// Expense Manager
class ExpenseManager {
    private final Map<String, User> users = new HashMap<>();
    private final Map<String, Map<String, Double>> balanceSheet = new HashMap<>();

    public void addUser(User user) {
        users.put(user.getId(), user);
        balanceSheet.put(user.getId(), new HashMap<>());
    }

    public void addExpense(Expense expense) {
        if (!expense.validate()) {
            System.out.println("Invalid expense");
            return;
        }

        List<Split> splits = expense.getSplits();
        User paidBy = expense.getPaidBy();
        double totalAmount = expense.getAmount();

        for (Split split : splits) {
            String paidTo = split.getUser().getId();
            double amount = split.getAmount();

            balanceSheet.get(paidTo).put(paidBy.getId(), balanceSheet.get(paidTo).getOrDefault(paidBy.getId(), 0.0) + amount);
            balanceSheet.get(paidBy.getId()).put(paidTo, balanceSheet.get(paidBy.getId()).getOrDefault(paidTo, 0.0) - amount);
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

// Main class for testing
public class SplitwiseApp {
    public static void main(String[] args) {
        ExpenseManager manager = new ExpenseManager();

        User u1 = new User("u1", "Alice");
        User u2 = new User("u2", "Bob");
        User u3 = new User("u3", "Charlie");

        manager.addUser(u1);
        manager.addUser(u2);
        manager.addUser(u3);

        // Equal Expense Example
        List<Split> equalSplits = Arrays.asList(new EqualSplit(u1), new EqualSplit(u2), new EqualSplit(u3));
        double equalTotal = 300;
        double equalShare = equalTotal / equalSplits.size();
        for (Split s : equalSplits) s.setAmount(equalShare);
        Expense e1 = new EqualExpense(equalTotal, u1, equalSplits);
        manager.addExpense(e1);

        // Exact Expense Example
        List<Split> exactSplits = Arrays.asList(
                new ExactSplit(u1, 50),
                new ExactSplit(u2, 150),
                new ExactSplit(u3, 100)
        );
        Expense e2 = new ExactExpense(300, u2, exactSplits);
        manager.addExpense(e2);

        // Percent Expense Example
        List<Split> percentSplits = Arrays.asList(
                new PercentSplit(u1, 40),
                new PercentSplit(u2, 20),
                new PercentSplit(u3, 40)
        );
        Expense e3 = new PercentExpense(500, u3, percentSplits);
        manager.addExpense(e3);

        manager.showBalances();
    }
}
