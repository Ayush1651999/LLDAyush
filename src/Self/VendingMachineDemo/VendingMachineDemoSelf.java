package Self.VendingMachineDemo;

public class VendingMachineDemoSelf {
    public static void main(String[] args){
        VendingMachine machine = VendingMachine.getInstance();

        Product gulabJamun = machine.addProduct("Gulab Jamun", 15.0, 5);
        Product samosa = machine.addProduct("Samosa", 10.0, 10);

        machine.selectProduct(gulabJamun);
        machine.makePayment(5.0);
        machine.makePayment(10.0);
        machine.dispenseProduct();

        System.out.println("-------------------------");

        machine.selectProduct(samosa);
        machine.makePayment(5.0);
        machine.makePayment(10.1);
        machine.dispenseProduct();
    }
}
