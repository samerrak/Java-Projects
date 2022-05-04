public class Client {

    public static void main(String[] args) {
        Dealership dealer = Dealership.instance();
        dealer.order("c-class", "mercedes", Transmission.MANUAL, 50000, Color.BLACK);
        dealer.order("a-class", "mercedes", Transmission.MANUAL, 50000, Color.BLACK);
        dealer.order("a8", "audi", Transmission.MANUAL, 50000, Color.BLACK);
    }
}
