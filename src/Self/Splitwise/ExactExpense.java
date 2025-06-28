package Self.Splitwise;

import java.util.List;

public class ExactExpense extends Expense {
    public ExactExpense(User user, double amount, List<Split> exactSplits) {
        super(user, amount, exactSplits);
    }

    @Override
    public boolean isValid() {
        double totalAmount = 0;
        for(Split split : this.getSplits()){
            totalAmount += (split.getAmount());
        }
        return totalAmount==this.getAmount();
    }
}
