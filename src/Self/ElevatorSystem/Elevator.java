package Self.ElevatorSystem;

import java.util.TreeSet;

public class Elevator {
    int id;
    ElevatorState state;
    Direction direction;
    int currentFloor;
    TreeSet<Integer> destinationFloors;

    public Elevator(int id) {
        this.id = id;
        destinationFloors = new TreeSet<>();
        this.state = ElevatorState.STOPPED;
    }

    public int getId() {
        return id;
    }

    public boolean canHandleRequest(int requestedFloor, Direction givenDirection) {
//        return (this.state == ElevatorState.STOPPED &&
//                (this.direction == Direction.UP && requestedFloor>this.currentFloor) || (this.direction == Direction.DOWN && requestedFloor<this.currentFloor));
        if(this.state == ElevatorState.STOPPED) return true;

        if(givenDirection != this.direction) return false;

        return (requestedFloor >= this.currentFloor && givenDirection == Direction.UP) || (requestedFloor <= this.currentFloor && givenDirection == Direction.DOWN);
    }

    public void addDestination(int floor) {
        this.destinationFloors.add(floor);
        updateDirection();
    }

    public void step() {
        if(destinationFloors.isEmpty()){
            state = ElevatorState.STOPPED;
            direction = Direction.NONE;
        }
        if(state == ElevatorState.MOVING){
            if(this.direction == Direction.UP){
                currentFloor++;
            } else {
                currentFloor--;
            }
            System.out.println("Elevator " + id + " has arrived at floor " + currentFloor);

            if(destinationFloors.contains(currentFloor)){
                System.out.println("--------------Elevator " + id + " has stopped at floor " + currentFloor + "--------------");
                destinationFloors.remove(currentFloor);
            }
        }

        updateDirection();
    }

    private void updateDirection() {
        if(destinationFloors.isEmpty()){
            direction = Direction.NONE;
            state = ElevatorState.STOPPED;
            return;
        }

        Integer nextFloor = destinationFloors.first();
        if(nextFloor > currentFloor){
            this.direction = Direction.UP;
        } else {
            this.direction = Direction.DOWN;
        }

        state = ElevatorState.MOVING;
    }

    public int destinationCount() {
        return destinationFloors.size();
    }
}
