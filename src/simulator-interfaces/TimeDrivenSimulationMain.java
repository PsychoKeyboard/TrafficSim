import java.util.Scanner;

public class TimeDrivenSimulationMain {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {

            int maxTime, roadLength, speedLimit;

            System.out.println("COMP 2150 (hyper-realistic) road simulator");

            System.out.print("How long should this simulation run for? ");
            maxTime = sc.nextInt();

            System.out.print("How long should the road be? ");
            roadLength = sc.nextInt();

            System.out.print("What is the road's speed limit? ");
            speedLimit = sc.nextInt();

            RoadSimulatorSettings settings = settings();
            TimeDrivenSimulation tds = simulation();

            settings.setMaxTimeInstant(timeUnitInstant().fromInteger(maxTime));
            settings.setRoadLength(roadLength);
            settings.setSpeedLimit(speedLimit);

            tds.applySettings(settings);

            // run the simulation until it ends.
            while (tds.currentTime().difference(tds.maxTime()) != 0) {
                tds.step();
                tds.printState();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                }
            }
            // print out a final report
            tds.printReport();
        }
    }

    // YOU SHOULD MODIFY THE METHODS BELOW TO RETURN INSTANCES OF YOUR 
    // IMPLEMENTATIONS:

    public static RoadSimulatorSettings settings() {
        throw new UnsupportedOperationException(
                "You must provide an implementation of RoadSimulatorSettings.");
        // return new <YOUR IMPLEMENTATION OF RoadSimulatorSettings>;
    }

    public static TimeDrivenSimulation simulation() {
        throw new UnsupportedOperationException(
                "You must provide an implementation of TimeDrivenSimulation.");
        // return new <YOUR IMPLEMENTATION OF TimeDrivenSimulation>;
    }

    public static TimeUnitInstant timeUnitInstant() {
        throw new UnsupportedOperationException(
                "You must provide an implementation of TimeUnitInstant.");
        // return new <YOUR IMPLEMENTATION OF TimeUnitInstant>;
    }

    public static Queue queue() {
        throw new UnsupportedOperationException("You must provide an implementation of Queue.");
        // return new <YOUR IMPLEMENTATION OF Queue>;
    }

    public static Vehicle vehicle() {
        throw new UnsupportedOperationException("You must provide an implementation of Vehicle.");
        // return new <YOUR IMPLEMENTATION OF Vehicle>;
    }

    public static Road road() {
        throw new UnsupportedOperationException("You must provide an implementation of Road.");
        // return new <YOUR IMPLEMENTATION OF Road>();
    }
}
