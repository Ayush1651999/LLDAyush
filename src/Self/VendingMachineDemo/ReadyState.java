package Self.VendingMachineDemo;

public class ReadyState implements State {
    private final VendingMachine machine;

    public ReadyState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void selectProduct(Product product) {
        System.out.println("Product already selected. Please make the payment");
    }

    @Override
    public void pay(double newAmount) {
        double amountPaid = machine.getAmountPaid();
        this.machine.setAmountPaid(newAmount + amountPaid);
        System.out.println("Amount received = " + newAmount);

        if(newAmount + amountPaid >= machine.getSelectedProduct().getPrice()){
            machine.setCurrentState(machine.getDispenseState());
        }
    }

    @Override
    public void dispenseProduct(Product product) {

    }
}
