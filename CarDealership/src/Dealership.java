import java.util.HashSet;
import java.util.Set;

public class Dealership {

    private static final Dealership INSTANCE = new Dealership();
    private final Set<Vehicle> carCollection = new HashSet<>();


    Car car1 = Car.getCar("c-class", "mercedes", Transmission.MANUAL, 50000, Color.BLACK);
    Car car2 = Car.getCar("c-class", "mercedes", Transmission.MANUAL, 50000, Color.BLACK);
    Car car3 = Car.getCar("g-class", "mercedes", Transmission.MANUAL, 80000, Color.BLACK);
    Car car4 = Car.getCar("g-class", "mercedes", Transmission.MANUAL, 80000, Color.BLACK);
    Car car5 = Car.addSpecifications(car1, Specification.LEATHER);


    private Dealership(){
        carCollection.add(car1);
        carCollection.add(car2);
        carCollection.add(car3);
        carCollection.add(car4);
        carCollection.add(car5);
    }

    public void order(String modelName, String brand, Transmission transmission, int price, Color color, Specification... specification){
        Car car1 = Car.getCar(modelName,brand,transmission,price,color);
        car1 = Car.addSpecifications(car1, specification);
        if (carCollection.contains(car1)){
            car1.available();
        }
        else
            Vehicle.NULL_CAR.available();
    }





    public static Dealership instance(){
        return INSTANCE;
    }


}
