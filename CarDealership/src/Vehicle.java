public interface Vehicle {

    public static final Vehicle NULL_CAR = new Vehicle()
    {
        @Override
        public void available() {
            System.out.println("Car not available\n");
        }
    };

    void available();

}
