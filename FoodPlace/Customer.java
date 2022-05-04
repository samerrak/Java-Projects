

public class Customer  {

	private String name;
	private int targetTipPct;

	public Customer(String name, int targetTipPct) {
		this.name = name;
		this.targetTipPct = targetTipPct;
		/* TODO: Add your code here */
	}

	public String getName() {
		return name;
	}

	public int getTargetTipPct() {
		return targetTipPct;
	}

	public String getDescriptiveMessage(FoodPlace foodPlace) {
		return this.name + " dined in " + foodPlace.getName();
	}

	public void dineAndPayCheck(FoodPlace foodPlace, double menuPrice ) {
		Check cheque = new Check(menuPrice);
		double avgTip = (foodPlace.getTipPercentage() + this.getTargetTipPct())/200.0;
		cheque.setTipByPct(avgTip);
		foodPlace.distributeIncomeAndSalesTax(cheque);

		/* TODO: Add your code here */
	}
}

