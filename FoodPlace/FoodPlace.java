
import java.util.List;

public abstract class FoodPlace {

    private static int currentMaxFoodPlaceID;
    private final int foodPlaceID;
    private final String name;
    private final double fixedCosts;
    private double totalSalesTax;
    private final Owner owner;

    public FoodPlace(String name, double fixedCosts, Owner owner){
        this.name = name;
        this.fixedCosts = fixedCosts;
        this.owner = owner;
        owner.setFoodPlace(this);
        currentMaxFoodPlaceID += 1;
        this.totalSalesTax = getTotalSalesTax();
        this.foodPlaceID = currentMaxFoodPlaceID;
    }

    public static int getCurrentMaxFoodPlaceID() {
        return currentMaxFoodPlaceID;
    }

    public int getFoodPlaceID() {
        return foodPlaceID;
    }

    public String getName() {
        return this.name;
    }

    public double getFixedCosts() {
        return this.fixedCosts;
    }

    public double getTotalSalesTax() {
        return this.totalSalesTax;
    }

    public void setTotalSalesTax(double totalSalesTax) {
        this.totalSalesTax = totalSalesTax;
    }

    public Owner getOwner() {
        return this.owner;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof FoodPlace && ((FoodPlace) obj).getFoodPlaceID() == this.foodPlaceID;
        /* TODO: Add your code here */
        /* TODO: Also remove return statement below*/
    }

    abstract void workShift(int hours);

    abstract List<IncomeTaxPayer> getIncomeTaxPayers();

    abstract void distributeIncomeAndSalesTax(Check check);

    abstract double getTipPercentage();
}
