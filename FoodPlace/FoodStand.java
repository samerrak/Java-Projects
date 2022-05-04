
import java.util.ArrayList;
import java.util.List;

public class FoodStand extends FoodPlace {

    public FoodStand(String name, double fixedCosts, WorkingOwner owner) {
        super(name, fixedCosts, owner);
    }

    @Override
    public String toString() {
        return "Name of FoodStand: " + this.getName() +
                "\n" + "Owner: " + this.getOwner();
    }

    @Override
    public void workShift(int hours) {
        // no salaried workers so do nothing
    }

    @Override
    public List<IncomeTaxPayer> getIncomeTaxPayers() {
        List<IncomeTaxPayer> incomeTaxPayerArrayList = new ArrayList<>();
        incomeTaxPayerArrayList.add(this.getOwner());
        /* TODO: Add your code here */
        /* TODO: Also remove return statement below*/
        return incomeTaxPayerArrayList;

    }

    @Override
    public void distributeIncomeAndSalesTax(Check check) {
        double menuPrices = check.getMenuPrice();
        double preIncome = this.getOwner().getIncome();
        double finalIncome = menuPrices + preIncome + check.getTip();
        double salesT = this.getTotalSalesTax();
        this.getOwner().setIncome(finalIncome);
        this.setTotalSalesTax(check.getSalesTax() + salesT);
        /* TODO: Add your code here */
    }

    @Override
    public double getTipPercentage() {
        /* TODO: Add your code here */
        /* TODO: Also remove return statement below*/
        return ((WorkingOwner) getOwner()).getTargetTipPct();
    }
}
