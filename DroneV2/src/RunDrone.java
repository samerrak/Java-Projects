import java.util.LinkedList;
import java.util.List;

public class RunDrone {

    public static void main(String[] args) {

        LinkedList<Integer> list = new LinkedList<>();
        list.
        Drone DJI = new Drone("DJI");

        Tricks takeoff0 = new Takeoff( true, 4);
        Tricks pucker0 = new Pucker(true, Speed.LOW, 5);
        Tricks sidewinder0 = new Sidewinder(true,6);
        Tricks takeoff1 = new Takeoff( true,3);
        Tricks sidewinder1 = new Sidewinder( false,9);




        Flight flight0 = new Flight();
        flight0.addControl(takeoff0);
        flight0.addControl(pucker0);
        flight0.addControl(sidewinder0);

        Flight flight1 = new Flight();
        flight1.addControl(takeoff0);
        flight1.addControl(sidewinder0);
        flight1.addControl(takeoff1);

        Flight flight2 = new Flight();
        flight2.addControl(takeoff0);
        flight2.addControl(pucker0);
        flight2.addControl(sidewinder0);
        flight2.addControl(takeoff1);
        flight2.addControl(sidewinder1);

        System.out.println("\nNumber of unique directions for Flight 0,1,2 respectively " + flight0.uniqueDirections() +"," + flight1.uniqueDirections() + ","  + flight2.uniqueDirections() + "\n");
        System.out.println("\nNumber of unique movements for Flight 0,1,2 respectively " + flight0.uniqueMotion() +"," + flight1.uniqueMotion() + ","  + flight2.uniqueMotion() + "\n");
        System.out.println("\nNumber of tricks for Flight 0,1,2 respectively " + flight0.numberOfTricks() +"," + flight1.numberOfTricks() + ","  + flight2.numberOfTricks() + "\n");

        DJI.addFlight(flight0);
        DJI.addFlight(flight1);
        DJI.addFlight(flight2);

        flight2.setOfTricks();
        DJI.uniqueMoveSort();
        DJI.uniqueSizeSort();

        

        DJI.executeFlights();
        DJI.record();
        DJI.save("Rec2", Recording.MKV);




    }
}
