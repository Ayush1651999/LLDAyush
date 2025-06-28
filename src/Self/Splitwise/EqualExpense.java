package Self.Splitwise;

import java.util.List;

public class EqualExpense extends Expense {
    public EqualExpense(User creator, double amount, List<Split> splits) {
        super(creator, amount, splits);
    }

    @Override
    public boolean isValid() {
        return true;
    }
}
