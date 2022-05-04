import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {
    Cars car1;
    Cars car2;
    Truck truck1;
    Integer fuel = 45;
    GasStation gasStation;


    @BeforeEach
    void setUp() {
        car1 = new Cars(VehicleName.MERCEDES, "c-class", VehicleColor.RED, fuel);
        car2 = new DecoratorCar(VehicleName.MERCEDES, "c-class", VehicleColor.RED, fuel);
        truck1 = new Truck(VehicleName.MERCEDES, "c-class", VehicleColor.RED, fuel, 60);
        gasStation = new GasStation();
        gasStation.vehicleAdded(car1);
        gasStation.vehicleAdded(car2);
        gasStation.vehicleAdded(car1);
        gasStation.vehicleAdded(car2);
        gasStation.vehicleAdded(car1);
        gasStation.vehicleAdded(car2);
        gasStation.vehicleAdded(truck1);
        gasStation.vehicleAdded(truck1);

    }

    @Test
    void TestFuelAll()
    {
        gasStation.fuelAtMaxAll();
        assertEquals(truck1.getFuel(), 100);
        assertEquals(car1.getFuel(), 100);
        assertEquals(car2.getFuel(), 100);
    }

    @Test
    void TestFuelCarsEject()
    {
        gasStation.fuelCars();
        assertTrue(gasStation.getVehicleList().size() == 2);
        assertEquals(car2.getFuel(), 100);
        assertEquals(car1.getFuel(), 100);

    }



    @Test
    void TestFuelingTruck()
    {
        truck1.fuel();
        assertEquals(truck1.getFuel(), fuel+10);

    }

    @Test
    void TestFuelingCar()
    {
        car1.fuel();
        assertEquals(car1.getFuel(), fuel+10);
    }

    @Test
    void TestFuelingDecoratorCar()
    {
        car2.fuel();
        assertEquals(car2.getFuel(), fuel+10);
    }




}