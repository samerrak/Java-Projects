public class RunDrone {



    public static void main(String[] args)
    {
        Drone.manual();

        Controls controller = new Controls();
        Drone DJI = new Drone(controller);

        DJI.addControl(0, controller.launch());// this message will not appear

        DJI.removeControl(0);

        DJI.addControl(1, controller.forwards(2)); // will print error message since we removed the launch command the drone won't be able to move
        DJI.addControl(2, controller.launch()); // will launch successfully
        DJI.addControl(3, controller.forwards(2)); // moves 2 units upwards downwards backwards forwards
        DJI.addControl(4, controller.upwards(2));
        DJI.addControl(5, controller.backwards(2));
        DJI.addControl(6, controller.downwards(2));
        DJI.addControl(7, controller.focus()); // will not focus since command is removed
        
        DJI.removeControl(7);

        DJI.addControl(8,controller.capture(pictureFormat.PDF)); // will print error since not focused


        DJI.addControl(9, controller.focus()); //focused successfully
        DJI.addControl(10, controller.capture(pictureFormat.JPG)); //will not capture anything

        DJI.removeControl(10);

        DJI.addControl(11, controller.save("IMAGE1")); // error message since nothing is captured
        DJI.addControl(12, controller.capture(pictureFormat.PNG)); //will successfully capture and save as IMAGE1.PNG
        DJI.addControl(13, controller.save("IMAGE1"));
        DJI.addControl(14, controller.launch()); // error message since drone already launched
        DJI.addControl(15, controller.forwards(2)); // will not move since it's removed

        DJI.removeControl(15);

        DJI.addControl(16, controller.land()); // will not land

        DJI.removeControl(16);

        DJI.addControl(17, controller.land());
        DJI.addControl(18, controller.land());
        DJI.run();

        //New Program to Highlight Functionality of my Code

        DJI.reset();

        DJI.addControl(0, controller.launch());
        DJI.addControl(1, controller.forwards(2));
        DJI.addControl(2, controller.land());

        DJI.run();








    }
}
