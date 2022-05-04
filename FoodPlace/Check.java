
public class Check {
	private double menuPrice;
	private double salesTax;
	private double tip;

	public Check(double menuPrice) {
		this.menuPrice = menuPrice;
		this.salesTax = 15/100.0 * menuPrice;
		/* TODO: Add your code here */
	}

	public double getMenuPrice() {
		return this.menuPrice;
	}

	public double getSalesTax() {
		return this.salesTax;
	}

	public double getTip() {
		return this.tip;
	}

	public void setTipByPct(double tipPct ) {
		this.tip = (tipPct/100)*(getMenuPrice());
		/* TODO: Add your code here */
	}
}
