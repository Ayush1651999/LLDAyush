package Self.ElevatorSystem;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ElevatorController {
    List<Elevator> elevators;
    int numFloors;
    public ElevatorController(int numElevators, int numFloors) {
        this.numFloors = numFloors;
        elevators = new ArrayList<>();
        for(int i=0; i<numElevators; i++){
            elevators.add(new Elevator(i));
        }
    }

    int request(int floor, Direction direction){
        int elevatorAssigned = -1;
        for(Elevator elevator : elevators){
            if(elevator.canHandleRequest(floor, direction)){
                elevatorAssigned = elevator.getId();
                break;
            }
        }

        if(elevatorAssigned == -1){
            elevatorAssigned = elevators.stream().min(Comparator.comparingInt(Elevator::destinationCount)).map(Elevator::getId).orElse(0);
        }
        elevators.get(elevatorAssigned).addDestination(floor);

        return elevatorAssigned;
        /*
        How To include direction in canHandleRequest method?
        given dir must be equal to elev's direction.
        */
    }

    public void step() {
        for(Elevator elevator : elevators){
            elevator.step();
        }
    }
}
