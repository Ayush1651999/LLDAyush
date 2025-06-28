package Self.VendingMachineDemo;

public interface State {
    public void selectProduct(Product product);

    public void pay(double amount);

    public void dispenseProduct(Product product);
}
