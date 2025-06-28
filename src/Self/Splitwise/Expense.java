package Self.Splitwise;

import java.util.List;

public abstract class Expense {
    private final List<Split> splits;
    private User creator;
    private double amount;


    public Expense(User creator, double amount, List<Split> splits) {
        this.creator = creator;
        this.amount = amount;
        this.splits = splits;
    }

    public User getCreator() {
        return this.creator;
    }

    public abstract boolean isValid();

    public List<Split> getSplits() {
        return splits;
    }

    protected double getAmount() {
        return amount;
    }
}
