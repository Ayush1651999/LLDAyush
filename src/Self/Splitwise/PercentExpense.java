package Self.Splitwise;

import java.util.List;

public class PercentExpense extends Expense {
    public PercentExpense(User user, double amount, List<Split> percentSplits) {
        super(user, amount, percentSplits);

        for(Split split : percentSplits){
            split.setAmount(amount * (((PercentSplit) split).getPercent()) * 0.01);
        }
    }

    @Override
    public boolean isValid() {
        double totalPercent = 0;
        for(Split split : this.getSplits()){
            totalPercent += ((PercentSplit) split).getPercent();
        }
        return totalPercent==100.0;
    }
}
