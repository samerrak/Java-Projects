public class DecoratorCar extends Cars {

    DecoratorCar(VehicleName name, String model, VehicleColor color, Integer fuel){
        super(name, model, color, fuel);
    }

    @Override
    public void fuel() {
        super.fuel();
        System.out.println("Refueling Car");
    }
}
