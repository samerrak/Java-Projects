public class Controls {

    private int tDistance;
    private static boolean takeoff = false;
    private static boolean landing = false;
    private boolean focused = false;
    private pictureFormat photoType;
    private boolean picTaken = false;
    private String filename;


    final public String launch(){
        if (!takeoff) {
            takeoff = true;
            landing = false;
            return "Attempting to Take-Off....Take-Off Successful\n";
        }
        else
            return "Already in-motion, land first to take-off again\n";
    }

    final public String focus(){
        focused = true;
        return "Object detected, camera is now in focus, ready to capture image\n";
    }

    final public String capture(pictureFormat image){
        photoType = image;
        if (image == null) return "Enter a valid image type";
        if (focused ){
            picTaken =  true;
            return "Attempting to capture image of " + image + " format to the console......Image captured successfully\n";
        }
        else
            return "Attempting to capture image of " + image + " format to the console......Image capture failure, Camera is not in focus.\n";
    }


    final public String save(String filename){
        this.filename = filename;
        if (filename == null) return "Enter a valid filename";
        if (picTaken){
            return "Attempting to save:" + filename + "." + photoType + "........ File saved successfully\n";
        }
        return "Capture a picture first to save\n";
    }

    final public String land(){
        if (!takeoff) {
            return "Already landed, take-off first to land again\n";}
        else {
            landing = true;
            takeoff = false;
            return "Attempting to land....Landing successful\n";
        }
    }

    final public String downwards(int iDistance){
        if (!takeoff) return "Take-off first to attempt moving the drone\n";
        else {
            tDistance = iDistance;
            if (tDistance > 0)
                return "Moving " + tDistance + " units downwards\n";
            else
                return "Please enter a value greater than or equal to zero to move\n";
        }
    }

    final public String upwards(int iDistance){
        if (!takeoff) return "Take-off first to attempt moving the drone\n";
        else {
            tDistance = iDistance;
            if (tDistance > 0)
                return "Moving " + tDistance + " units upwards\n";
            else
                return "Please enter a value greater than or equal to zero to move\n";
        }
    }

    final public String backwards(int iDistance){
        if (!takeoff) return "Take-off first to attempt moving the drone\n";
        else {
            tDistance = iDistance;
            if (tDistance > 0)
                return "Moving " + tDistance + " units backwards\n";
            else
                return "Please enter a value greater than or equal to zero to move\n";
        }
    }

    final public String forwards( int iDistance){
        if (!takeoff) return "Take-off first to attempt moving the drone\n";
        else {
            tDistance = iDistance;
            if (tDistance > 0)
                return "Moving " + tDistance + " units forwards\n";
            else
                return "Please enter a value greater than or equal to zero to move\n";
        }
    }



    public String uncapture() {
         picTaken = false;
         return "";
    }

    public String unfocus() {
        focused = false;
        picTaken=false;
        return "";
    }


    public String unland() {
        landing = false;
        takeoff = true;
        return "";
    }

    public String unlaunch() {
        takeoff = false;
        return "";
    }

    public pictureFormat getPhotoType() {
        return photoType;
    }

    public int getDistance() {
        return tDistance;
    }

    public String getFilename() {
        return filename;
    }

}
