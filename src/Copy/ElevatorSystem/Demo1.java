package Copy.ElevatorSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

enum Direction {
    UP, DOWN, NONE
}

enum ElevatorState {
    IDLE, MOVING, MAINTENANCE
}

class Elevator {
    private int id;
    private int currentFloor;
    private ElevatorState state;
    private Direction direction;
    private TreeSet<Integer> destinationFloors; // Sorted destinations

    public TreeSet<Integer> getDestinationFloors(){ // just for debugging
        return destinationFloors;
    }

    public Elevator(int id) {
        this.id = id;
        this.currentFloor = 0;
        this.state = ElevatorState.IDLE;
        this.direction = Direction.NONE;
        this.destinationFloors = new TreeSet<>();
    }

    public int getId() { return id; }
    public int getCurrentFloor() { return currentFloor; }
    public ElevatorState getState() { return state; }

    public void addDestination(int floor) {
        destinationFloors.add(floor);
        updateDirection();
    }

    private void updateDirection() {
        if (destinationFloors.isEmpty()) {
            direction = Direction.NONE;
            state = ElevatorState.IDLE;
            return;
        }

        int next = destinationFloors.first();
        direction = (next > currentFloor) ? Direction.UP : Direction.DOWN;
        state = ElevatorState.MOVING;
    }

    public void step() {
        if (destinationFloors.isEmpty()) {
            state = ElevatorState.IDLE;
            direction = Direction.NONE;
            return;
        }

        if (direction == Direction.UP) {
            currentFloor++;
        } else if (direction == Direction.DOWN) {
            currentFloor--;
        }

        System.out.println("Copy.ElevatorSystem.Elevator " + id + " at floor " + currentFloor);

        if (destinationFloors.contains(currentFloor)) {
            destinationFloors.remove(currentFloor);
            System.out.println("Copy.ElevatorSystem.Elevator " + id + " stopped at floor " + currentFloor);
        }

        updateDirection();
    }

    public boolean isIdle() {
        return state == ElevatorState.IDLE;
    }

    public boolean canTakeRequest(int requestFloor) {
        return isIdle() || (direction == Direction.UP && requestFloor >= currentFloor)
                || (direction == Direction.DOWN && requestFloor <= currentFloor);
    }
}

class ElevatorSystem {
    private List<Elevator> elevators;

    public List<Elevator> getElevatorList(){
        return elevators;
    }

    public ElevatorSystem(int numberOfElevators) {
        elevators = new ArrayList<>();
        for (int i = 0; i < numberOfElevators; i++) {
            elevators.add(new Elevator(i));
        }
    }

    public void requestPickup(int floor, Direction direction) {
        Elevator bestElevator = null;

        for (Elevator elevator : elevators) {
            if (elevator.canTakeRequest(floor)) {
                bestElevator = elevator;
                break;
            }
        }

        if (bestElevator == null) {
            bestElevator = elevators.get(0); // Fallback
        }

        System.out.println("Pickup requested at floor " + floor + " -> Assigned to Copy.ElevatorSystem.Elevator " + bestElevator.getId());
        bestElevator.addDestination(floor);
    }

    public void step() {
        for (Elevator elevator : elevators) {
            elevator.step();
        }
    }
}

// Main.java
public class Demo1 {
    public static void main(String[] args) throws InterruptedException {
        ElevatorSystem system = new ElevatorSystem(1);

        system.requestPickup(9, Direction.UP);
//        system.requestPickup(6, Direction.DOWN);

        for (int i = 0; i < 30; i++) {
            System.out.print("Time is " + i + " seconds : ");
            system.step();
            Thread.sleep(100); // Simulate time step
            if(i==6){
                system.requestPickup(1, Direction.UP);
            }
        }

        System.out.println(system.getElevatorList().get(0).getDestinationFloors().toString());
    }
}
