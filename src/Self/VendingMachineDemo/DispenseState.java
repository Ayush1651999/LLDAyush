package Self.VendingMachineDemo;

import java.util.Map;

public class DispenseState implements State {
    private final VendingMachine machine;

    public DispenseState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void selectProduct(Product product) {

    }

    @Override
    public void pay(double amount) {

    }

    @Override
    public void dispenseProduct(Product product) {
        // do logic
        System.out.println("Product has been dispensed -> " + product.getName());

        Map<String, Integer> productMap = machine.getProductMap();
        int initialQuantity = productMap.get(product.getName());
        productMap.put(product.getName(), initialQuantity-1);
        machine.setProductMap(productMap);

        System.out.println("Amount returned as change = " + (machine.getAmountPaid()-product.getPrice()));
        machine.setAmountPaid(0);
        machine.setSelectedProduct(null);
        machine.setCurrentState(machine.getIdleState());
    }
}
