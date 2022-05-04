
public class Owner extends IncomeTaxPayer {

	final private int incomeTaxPct = 10;
	private double salaryExpenses;

	private FoodPlace foodPlace;

	public Owner(String name) {
		super(name);
		/* TODO: Add your code here */
		/* TODO: Also edit the super call */
	}

	public FoodPlace getFoodPlace() {
		return foodPlace;
	}
	public int getIncomeTaxPct() {
		return incomeTaxPct;
	}

	public double getSalaryExpenses() {
		return salaryExpenses;
	}

	public void setSalaryExpenses(double salaryExpenses) {
		this.salaryExpenses = salaryExpenses;
	}

	public void setFoodPlace(FoodPlace foodPlace) {
		this.foodPlace = foodPlace;
	}


	@Override
	public double calculateIncomeTax() {
		setFoodPlace(foodPlace);
		double incomeT;
		double netProfit = this.getIncome() - (this.getSalaryExpenses() + foodPlace.getFixedCosts());

		if (netProfit > 0){
			incomeT = (this.getIncomeTaxPct()/100.0)*(netProfit); }
		else { incomeT = 0; }

		return incomeT;

		/* TODO: Add your code here */
		/* TODO: Also remove return statement below*/
	}
}

