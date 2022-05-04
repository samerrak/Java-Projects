import java.util.HashMap;
import java.util.Objects;

public class Car implements Vehicle {
    private final String modelName;
    private final String brand;
    private int price;
    private Color color;
    private Transmission transmission;
    private String specification = "";
    private final String CarKey;
    private final static HashMap<String,Car> cars = new HashMap<>();

    public static void main(String[] args) {
        Car car1 = getCar("c-class", "mercedes", Transmission.MANUAL, 50000, Color.BLACK);
        Car car2 = getCar("c-class", "mercedes", Transmission.MANUAL, 50000, Color.BLACK);
        Car car3 = getCar("g-class", "mercedes", Transmission.MANUAL, 80000, Color.BLACK);
        Car car4 = getCar("g-class", "mercedes", Transmission.MANUAL, 80000, Color.BLACK);
        System.out.println(car1.hashCode());
        System.out.println(car2.hashCode());
        car1 = Car.addSpecifications(car1, Specification.LEATHER);
        System.out.println(car1.equals(car2));
        System.out.println(car1.hashCode());
        System.out.println(car2.hashCode());
    }

    private Car(String modelName, String brand, Transmission transmission, int price, Color color){
        this.modelName = modelName.toLowerCase();
        this.brand = brand.toLowerCase();
        this.transmission = transmission;
        this.color = color;
        this.price = price;
        CarKey =  (modelName + brand + transmission + price + color);
    }

    public static Car getCar(String modelName, String brand, Transmission transmission, int price, Color color){
        if (!(modelName!=null || brand !=null || transmission!=null || price != 0 || color != null))
            throw new RuntimeException();

        String key = (modelName.toLowerCase() + brand.toLowerCase() + transmission + price + color);
        if (cars.containsKey(key))
            return cars.get(key);
        else
        {
            Car newCar = new Car(modelName,brand,transmission,price,color);
            cars.put(key, newCar);
            return newCar;
        }
    }

    public static Car addSpecifications(Car car, Specification... specifications){
        assert specifications!=null;
        int newPrice = 0;
        for (Specification s: specifications){
            if (s.equals(Specification.LEATHER))
                newPrice = car.price + 600;
            if (s.equals(Specification.SMART))
                newPrice = car.price + 200;
        }
        Car car1 = getCar(car.modelName, car.brand, Transmission.MANUAL, newPrice, car.color);
        for (Specification s: specifications){
            car1.specification +=  s + " ";
        }
        return car1;
    }


//    @Override
//    public String toString() {
//        return brand + ", " + modelName + ", " + transmission + ", " + color + ", $" + price + " " + specification;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;
        Car car = (Car) o;
        return price == car.price && Objects.equals(modelName, car.modelName) && Objects.equals(brand, car.brand) && color == car.color && transmission == car.transmission && Objects.equals(CarKey, car.CarKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modelName, brand, price, color, transmission);
    }


    @Override
    public void available() {
        System.out.println("Car + " + brand + ", " + modelName + ", " + transmission + ", " + color + ", $" + price + " " + specification + "\n");
    }
}
