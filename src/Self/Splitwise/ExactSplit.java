package Self.Splitwise;

public class ExactSplit extends Split {
    public ExactSplit(User user, double amount) {
        super(user);
        this.setAmount(amount);
    }
}
