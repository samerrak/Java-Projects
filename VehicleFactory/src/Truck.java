public final class Truck extends AbstractVehicle{
    Integer load;

    Truck(VehicleName name, String model, VehicleColor color, Integer fuel, Integer load) {
        super(name, model, color, fuel);
        this.load = load;

    }

    @Override
    public String toString() {
        return super.toString() + ", load weight of truck: " + load;
    }
}
