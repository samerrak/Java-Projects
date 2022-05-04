import java.util.ArrayList;
import java.util.List;

public final class GasStation implements VehicleObserver{
    Integer tank = 100;

    private List<FuelingVehicles> vehicleList = new ArrayList<>();

    GasStation()
    {
    }

    public void fuelAtMaxAll(){
        for (int i = 0; i< vehicleList.size(); i++)
        {
            vehicleList.get(i).fuelAtMax(tank);
        }

    }

    public void fuelCars(){
        for (int i = 0; i< vehicleList.size(); i++)
        {
            if ( vehicleList.get(i) instanceof Cars)
            {
                vehicleList.get(i).fuelAtMax(tank);
                vehicleRemoved(vehicleList.get(i));
                i--;}
        }
    }




    @Override
    public void vehicleAdded(FuelingVehicles vehicle) {
        vehicleList.add(vehicle);

    }

    @Override
    public void vehicleRemoved(FuelingVehicles vehicle) {
        vehicleList.remove(vehicle);
    }

    public List<FuelingVehicles> getVehicleList() {
        return vehicleList;
    }
}
