import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Drone {
    private Controls control;
    static private int x = 0;//insignificant to design just for aesthetic
    private ArrayList<String> remote = new ArrayList<>();


    public Drone(Controls control) {
        if (control == null) throw new Error("Enter a valid control name");
        this.control = control;
    }

    public void addControl (int order, String command){
        if (contains(command)){
            remote.add(order, command);
        }
        
        else
            remote.add(order, "Error: Please enter a valid function from the list provided in the manual\n");

    }

    public void removeControl (int order){
        if (equal(order,"Attempting to land....Landing successful\n")){
            control.unland();}
        else if (equal(order,"Attempting to Take-Off....Take-Off Successful\n"))
            control.unlaunch();
        else if (equal(order,control.capture(control.getPhotoType())))
            control.uncapture();
        else if (remote.get(order).equals(control.focus()))
            control.unfocus();

        remote.set(order,"");
    }

    private boolean equal(int order,String s){
        return remote.get(order).equals(s);
    }


    public void run(){
        x++;
        System.out.println("[PRE-PROCESSED PROGRAM ACTIVATED " + x + "]\n" );
        for (String s : Collections.unmodifiableList(remote)) {
            if (!s.equals("")){
                System.out.println("• " + s); }
        }
    }

    public static void manual(){
        System.out.println("""
                [DRONE MANUAL]:
                To add your device as a controller for the drone declare it as Controls device = new Controls()
                To connect your drone to your device, sync your drone to it by declaring as Drone droneName = new Drone(device)
                To add a command to your drone use the addControl function and provide the order and the command droneName.addControl(order, commandName of type String)
                To remove a function provide the order of the function you want removed droneName.removeControl(order) directly after the addControl of the function you want removed
                The order is used to provide the user with the option to declare which commands the user wants executed first and it starts from 0
                The following functions are available to use currently: launch(), focus(), capture(pic-type), save(filename), land(), downwards(distance), upwards(distance), forwards(distance), backwards(distance)
                
                ---------------------------------------------------------------------------------------------------------------------------------------------------
                """);
    }

    public void reset(){
        remote.clear();
        control.unlaunch();
        control.unfocus();
        control.uncapture();
        System.out.println("----------------PROGRAM RESET SUCCESS----------------\n");
    }

    private boolean contains (String command){
        pictureFormat image = control.getPhotoType();
        String filename = control.getFilename();
        int tDistance =  control.getDistance();
        ArrayList<String> messages = new ArrayList<>(Arrays.asList("Attempting to Take-Off....Take-Off Successful\n","Already in-motion, land first to take-off again\n",
                "Object detected, " + "camera is now in focus, ready to capture image\n",
                "Attempting to capture image of " + image + " format to the console......Image captured successfully\n", "Attempting to capture image of " + image + " format to the console......Image capture failure, Camera is not in focus.\n",
                "Attempting to save:" + filename + "." + image + "........ File saved successfully\n", "Capture a picture first to save\n",
                "Already landed, take-off first to land again\n", "Attempting to land....Landing successful\n",
                "Take-off first to attempt moving the drone\n", "Moving " + tDistance + " units downwards\n", "Please enter a value greater than or equal to zero to move\n",
                "Moving " + tDistance + " units upwards\n", "Moving " + tDistance + " units backwards\n", "Moving " + tDistance + " units forwards\n"));

        return messages.contains(command);
    }
//else if (equal(order,control.capture(pictureFormat.PDF)) || equal(order,control.capture(pictureFormat.JPG))
//                || equal(order,control.capture(pictureFormat.PNG)) || equal(order,control.capture(pictureFormat.RAW)))
//            control.uncapture();

}
