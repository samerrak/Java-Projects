import java.util.Comparator;

public class Car implements Comparable<Car>{
    private final String modelName;
    private CarName brand;
    private final int speed;
    private final int price;
    private Seats seats;

    public static void main(String[] args){

        System.out.println(new Car(CarName.MERCEDES, "C-Class", 200, 40000, Seats.SIX));
    }

    public Car(CarName brand, String modelName, int speed, int price, Seats seats ) {
        this.brand = brand;
        this.modelName = modelName;
        this.speed = speed;
        this.price = price;
        this.seats = seats;
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public String toString() {
        return
                brand + ", " + modelName +
                ", " + speed +
                " kph, $" + price +
                ", " + seats + " seater ";
    }

    public static Comparator<Car> sortBySpeed()
    {
        return new Comparator<Car>() {
            @Override
            public int compare(Car o1, Car o2) {
                if (o1.speed > o2.speed)
                    return -1;
                else if (o1.speed < o2.speed)
                    return 1;
                else
                    return 0;
            }
        };
    }

    @Override
    public int compareTo(Car o) {
        if (this.speed > o.speed)
            return -1;
        else if (this.speed < o.speed)
            return 1;
        else
            return 0;
    }
}
