
public abstract class IncomeTaxPayer {

	private static int currentMaxTaxID;
	private int taxID;
	private String name;
	private double income;

	public IncomeTaxPayer(String name){
		this.name = name;
		currentMaxTaxID = currentMaxTaxID + 1;
		this.taxID = currentMaxTaxID;
		/* TODO: Add your code here */
	}

	public static int getCurrentMaxTaxID() {
		return currentMaxTaxID;
	}

	public int getTaxID() {
		return taxID;
	}

	public String getName() {
		return name;
	}

	public double getIncome() {
		return this.income;
	}

	public void setIncome( double income) {
		this.income = income;
	}

	public String toString() {
		return "  " + taxID + " " + name + " income " + income ;
	}

	public boolean equals(Object obj) {
		return obj instanceof IncomeTaxPayer && ((IncomeTaxPayer) obj).getTaxID() == this.taxID;
		/* TODO: Add your code here */
		/* TODO: Also remove return statement below*/
	}

	public abstract double calculateIncomeTax();
}
