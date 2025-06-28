package Self.VendingMachineDemo;

import java.util.HashMap;
import java.util.Map;

public class VendingMachine {
    static VendingMachine instance;
    private Map<String, Integer> productMap;
    private Product selectedProduct;
    private State currentState;
    private State readyState;
    double amountPaid;
    private State dispenseState;
    private State idleState;

    private VendingMachine(){
        this.productMap = new HashMap<>();
        this.readyState = new ReadyState(this);
        this.dispenseState = new DispenseState(this);
        this.idleState = new IdleState(this);
        this.currentState = idleState;
        this.amountPaid = 0;
    }

    public static synchronized VendingMachine getInstance() {
        if(instance == null){
            instance = new VendingMachine();
        }
        return instance;
    }

    public Product addProduct(String productName, double price, int quantity) {
        Product product = new Product(productName, price);
        if(productMap.containsKey(productName)){
            productMap.put(productName, productMap.get(productName) + quantity);
        } else {
            productMap.put(productName, quantity);
        }

        return product;
    }

    public void selectProduct(Product product) {
        currentState.selectProduct(product);
    }

    public void makePayment(double amount) {
        this.currentState.pay(amount);
    }

    public void setSelectedProduct(Product product) {
        this.selectedProduct = product;
    }

    public Map<String, Integer> getProductMap() {
        return productMap;
    }

    public void setCurrentState(State state) {
        this.currentState = state;
    }

    public State getReadyState() {
        return this.readyState;
    }

    public double getAmountPaid() {
        return this.amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Product getSelectedProduct() {
        return this.selectedProduct;
    }

    public State getDispenseState() {
        return dispenseState;
    }

    public State getIdleState() {
        return idleState;
    }

    public void setProductMap(Map<String, Integer> productMap) {
        this.productMap = productMap;
    }

    public void dispenseProduct() {
        currentState.dispenseProduct(selectedProduct);
    }
}
