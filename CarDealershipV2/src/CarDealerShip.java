import java.util.ArrayList;
import java.util.LinkedList;

public class CarDealerShip {

    private ArrayList<Car> cars = new ArrayList<>();



    public static void main(String[] args){

        LinkedList<Integer> x = new LinkedList<>();
        CarDealerShip ford = new CarDealerShip();
        ford.addCar(new Car(CarName.MERCEDES, "G-Class", 200, 40000, Seats.SIX));
        ford.addCar(new Car(CarName.ASTONMARTIN, "DBX", 200, 30000, Seats.FOUR));
        ford.addCar(new Car(CarName.FERRARI, "Roma", 180, 200000, Seats.TWO));

        ford.availableCars();

        ford.addCar(new Car(CarName.FERRARI, "Roma", 300, 400000, Seats.TWO));

        ford.availableCars();

        ford.sortCarsBySpeed();
        ford.availableCars();

    }


    public final void addCar(Car car){
        cars.add(car);
    }

    public final void buyCar(int index){
        cars.remove(index);
    }

    public final void availableCars(){
        for (Car car: cars){
            System.out.println(cars.indexOf(car) + ". " + car);
        }
        System.out.println("\n");
    }

    public final void sortCarsBySpeed(){
        cars.sort(Car.sortBySpeed());
    }




}
