public class Cars extends AbstractVehicle{

    public static void main(String[] args) {
        Cars car1 = new Cars(VehicleName.MERCEDES, "c-class", VehicleColor.RED, 45);
        car1.status();
        Truck truck1 = new Truck(VehicleName.MERCEDES, "c-class", VehicleColor.RED, 45, 60);
        truck1.status();
        car1.fuel();
        car1.status();
        truck1.fuel();
        truck1.status();
        DecoratorCar car2 = new DecoratorCar(VehicleName.MERCEDES, "c-class", VehicleColor.RED, 45);
        car2.fuel();
        car2.status();
    }

    Cars(VehicleName name, String model, VehicleColor color, Integer fuel) {
        super(name, model, color, fuel);
    }


}
