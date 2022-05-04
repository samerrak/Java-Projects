package eventbrite.events.guests;

public interface Guest {
    /**
     An interesting feature of this design is that it allows EventBrite to represent an absent artist or VIP guest
     by utilizing the Null Object Pattern and anonymous classes
     **/
    Guest NULL = new Guest() {
        @Override
        public String getName() {
            return null;
        }

        @Override
        public void setName(String name) {
        }

    };

    String getName();

    void setName(String name);


}
