public enum Speed {
    LOW(20), MODERATE(45), HIGH(100);

    public final int valueOfEnum;

    private Speed(int speed){
        valueOfEnum = speed;
    }
}
