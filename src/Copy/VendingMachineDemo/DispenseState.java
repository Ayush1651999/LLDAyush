package Copy.VendingMachineDemo;

class DispenseState implements VendingMachineState {
    private final VendingMachine vendingMachine;

    public DispenseState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectProduct(Product product) {
        System.out.println("Product already selected. Please collect the dispensed product.");
    }

    @Override
    public void insertCoin(Coin coin) {
        System.out.println("Payment already made. Please collect the dispensed product.");
    }

    @Override
    public void insertNote(Note note) {
        System.out.println("Payment already made. Please collect the dispensed product.");
    }

    @Override
    public void dispenseProduct() {
        Product product = vendingMachine.getSelectedProduct();
        vendingMachine.getInventory().updateQuantity(product, vendingMachine.getInventory().getQuantity(product) - 1);
        System.out.println("Product dispensed: " + product.getName());
        vendingMachine.setState(vendingMachine.getReturnChangeState()); // Change the state to ReturnChangeState
    }

    @Override
    public void returnChange() {
        System.out.println("Please collect the dispensed product first.");
    }
}
