import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    @Test
    void testCarNoSpec(){
        Car car1 = Car.getCar("c-class", "mercedes", Transmission.MANUAL, 50000, Color.BLACK);
        Car car2 = Car.getCar("c-class", "mercedes", Transmission.MANUAL, 50000, Color.BLACK);
        Car car3 = Car.getCar("g-class", "mercedes", Transmission.MANUAL, 80000, Color.BLACK);
        Car car4 = Car.getCar("x-class", "ferrari", Transmission.MANUAL, 80000, Color.RED);

        assertEquals(car1, car2);
        assertNotEquals(car1, car3);
        assertNotEquals(car3, car4);
    }

    @Test
    void CarSpec(){
        Car car1 = Car.getCar("c-class", "mercedes", Transmission.MANUAL, 50000, Color.BLACK);
        Car car2 = Car.getCar("c-class", "mercedes", Transmission.MANUAL, 50000, Color.BLACK);

        assertEquals(car1,car2);
        car2 = Car.addSpecifications(car2, Specification.LEATHER, Specification.SMART);
        assertNotEquals(car1,car2);

    }


    @Test
    public void testCarFails()
    {
        try{
            Car car6 = Car.getCar(null, "mercedes", Transmission.MANUAL, 50000, Color.BLACK);
            fail();
        }
        catch (RuntimeException e) {
            boolean x = true;
            assertTrue(x = true);
        }
    }



}