
import java.util.ArrayList;
import java.util.List;

public class Restaurant extends FoodPlace {

	private Staff cook;
	private Server server;

	public Restaurant(String name, double fixedCosts, Owner owner, Staff cook, Server server) {
		super(name, fixedCosts, owner);
		this.cook = cook;
		this.server = server;
	}

	public Staff getCook() {
		return cook;
	}

	public Server getServer() {
		return server;
	}

	@Override
	public String toString() {
		return "Name of restaurant: " + this.getName() +
				"\n" + "Owner: " + this.getOwner() +
				"\n" + "Cook: " + cook +
				"\n" + "Server: " + server;
	}

	@Override
	public void workShift(int hours) {
		double cookHours = cook.workHours(hours);
		double serverHours = server.workHours(hours);
		double preExpenses = this.getOwner().getSalaryExpenses();
		this.getOwner().setSalaryExpenses(cookHours+serverHours+preExpenses);

	}

	@Override
	public List<IncomeTaxPayer> getIncomeTaxPayers() {

		List<IncomeTaxPayer> incomeTaxPayerArrayList = new ArrayList<>();
		incomeTaxPayerArrayList.add(this.getCook());
		incomeTaxPayerArrayList.add(this.getServer());
		incomeTaxPayerArrayList.add(this.getOwner());
		return incomeTaxPayerArrayList;
	}

	@Override
	public void distributeIncomeAndSalesTax(Check check) {
		double menuPrices = check.getMenuPrice();
		double preIncome = this.getOwner().getIncome();
		double finalIncome = menuPrices + preIncome;
		double salesT = getTotalSalesTax();
		this.getOwner().setIncome(finalIncome);
		this.setTotalSalesTax(check.getSalesTax() + salesT);
		this.getCook().setIncome((0.2*check.getTip()) + this.getCook().getIncome());
		this.getServer().setIncome((0.8*check.getTip()) + this.getServer().getIncome());
		/* Failed One of the Tests. */
	}

	@Override
	public double getTipPercentage() {
		/* TODO: Add your code here */
		/* TODO: Also remove return statement below*/
		return server.getTargetTipPct();
	}

}

