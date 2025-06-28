package Self.VendingMachineDemo;

import java.util.Map;

public class IdleState implements State {
    VendingMachine machine;

    public IdleState(VendingMachine machine){
        this.machine = machine;
    }

    @Override
    public void selectProduct(Product product) {
        Map<String, Integer> productMap = machine.getProductMap();
        if(productMap.containsKey(product.getName()) && productMap.get(product.getName())>0){
            machine.setSelectedProduct(product);
            machine.setCurrentState(machine.getReadyState());
            System.out.println("Product has been been selected -> " + product.getName());
        } else {
            System.out.println("Product is out of stock!!");
        }
    }

    @Override
    public void pay(double amount) {
        System.out.println("Please select a product first");
    }

    @Override
    public void dispenseProduct(Product product) {
        System.out.println("Please select a product first");
    }
}
