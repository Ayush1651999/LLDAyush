package Self.ElevatorSystem;

public class Demo {
    public static void main(String[] args) throws InterruptedException {
        ElevatorController controller = new ElevatorController(2, 10);

        int elevatorAssigned = controller.request(3, Direction.UP);
        System.out.println("Elevator " + elevatorAssigned + " has been assigned");
        elevatorAssigned = controller.request(7, Direction.DOWN);
        System.out.println("Elevator " + elevatorAssigned + " has been assigned");

        for(int i=1; i<=10; i++){
            controller.step();
            Thread.sleep(100);
        }
    }
}
