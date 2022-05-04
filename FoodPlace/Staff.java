public class Staff extends IncomeTaxPayer {

	private int salaryPerHour;
	final private int incomeTaxPercentage = 25;

	public Staff(String name, boolean isCook) {
		super(name);
		if (isCook) { salaryPerHour=20; }
		else { salaryPerHour=10; }
		/* TODO: Add your code here */
		/* TODO: Also edit the super call */
	}

	public int getSalaryPerHour() {
		return salaryPerHour;
	}

	public int getIncomeTaxPercentage() {
		return incomeTaxPercentage;
	}

	public double workHours(int numHours) {
		double inc1 = this.getIncome(); //if there's brackets in the end attach something to it.
		double d = this.salaryPerHour * 1.0;
		double inc = numHours * d;
		this.setIncome(inc + inc1);
		return inc; //return income from numHours only because this method has 2 funcs.
		/* TODO: Add your code here */
		/* TODO: Also remove return statement below*/
	}

	@Override
	public double calculateIncomeTax() {
		double c = this.getIncomeTaxPercentage() * 1.0;
		double taxeR = this.getIncome() * c/100.0;
		/* TODO: Add your code here */
		/* TODO: Also remove return statement below*/
		return taxeR;
	}

}
