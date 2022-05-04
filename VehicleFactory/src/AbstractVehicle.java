public abstract class AbstractVehicle implements FuelingVehicles {
    private Integer fuel;
    private VehicleName name;
    private String model;
    private VehicleColor color;

    protected AbstractVehicle(VehicleName name, String model, VehicleColor color, Integer fuel)
    {
        assert model != null;
        this.fuel = fuel;
        this.name = name;
        this.color = color;
        this.model = model;
    }

    private String fuelLevel()
    {
        return this.toString() + ", current fuel level " + fuel;
    }

    public String toString() {
        return this.getClass() + " " +
                name +
                ", " + model  +
                ", " + color;
    }

    public void status()
    {
        System.out.println(this.fuelLevel());
    }

    private Command fuelingCommand()
    {
        return new Command() {
            @Override
            public void fuel() {
                fuel += 10;
            }
        };
    }

    public void fuel()
    {
        this.fuelingCommand().fuel();
    }

    public Integer getFuel() {
        return fuel;
    }

    @Override
    public void fuelAtMax(int fuel) {
        this.fuel = fuel;
    }
}
