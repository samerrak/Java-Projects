
import java.util.ArrayList;
import java.util.List;

public class FastFood extends FoodPlace {

    private List<Staff> staff = new ArrayList<>();

    public FastFood(String name, double fixedCosts, Owner owner, List<Staff> staff) {
        super(name, fixedCosts, owner);
        this.staff = new ArrayList<Staff>(staff); //shallow copy
        /* TODO: Add your code here */
        /* TODO: Also edit the super call */
    }

    public List<Staff> getStaff() {
        return staff;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Name of FastFood: " + this.getName() +
                "\n" + "Owner: " + this.getOwner());
        int index = 1;
        for (Staff staff: staff) {
            builder.append("\n" + "Staff " + index++ + " : " + staff );
        }
        return builder.toString();
    }


    @Override
    public void workShift(int hours) {
        for (Staff workers: this.getStaff())
        {
            double preExpenses = this.getOwner().getSalaryExpenses();
            this.getOwner().setSalaryExpenses((workers.workHours(hours))+preExpenses);

        }

        /* TODO: Add your code here */
    }

    @Override
    public List<IncomeTaxPayer> getIncomeTaxPayers() {
        List<IncomeTaxPayer> incomeTaxPayerArrayList = new ArrayList<>();
        for (Staff payers: this.getStaff())
        {
            incomeTaxPayerArrayList.add(payers);
            incomeTaxPayerArrayList.add(getOwner());

        }
        return incomeTaxPayerArrayList;


        /* TODO: Add your code here */
        /* TODO: Also remove return statement below*/

    }

    @Override
    public void distributeIncomeAndSalesTax(Check check) {
        double menuPrices = check.getMenuPrice();
        double preIncome = this.getOwner().getIncome();
        double finalIncome = menuPrices + preIncome;
        double salesT = getTotalSalesTax();
        this.getOwner().setIncome(finalIncome);
        this.setTotalSalesTax(check.getSalesTax() + salesT);

        for (Staff workers: this.getStaff())
        {
            workers.setIncome((check.getTip()/this.getStaff().size()) + workers.getIncome());


        }
    }

    @Override
    public double getTipPercentage() {
        return 0;
    }
}


