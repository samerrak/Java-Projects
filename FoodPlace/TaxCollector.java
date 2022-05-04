
import java.util.ArrayList;
import java.util.List;

public class TaxCollector {

	private List<FoodPlace> foodPlaces = new ArrayList<>();

	private double incomeTaxCollected;
	private double salesTaxCollected;

	public TaxCollector(List<FoodPlace> foodPlaces) {
		this.foodPlaces = foodPlaces;
		/* TODO: Add your code here */
	}

	public List<FoodPlace> getFoodPlaces() {
		return foodPlaces;
	}

	public double getIncomeTaxCollected() {
		return incomeTaxCollected;
	}

	public double getSalesTaxCollected() {
		return salesTaxCollected;
	}

	public void collectTax() {
		double incomeTax = 0;
		double salesTax = 0;
		for (FoodPlace foodie: foodPlaces){
			salesTax += foodie.getTotalSalesTax();
			for (IncomeTaxPayer taxed : foodie.getIncomeTaxPayers()){
				incomeTax += taxed.calculateIncomeTax();
			}
		}
		this.incomeTaxCollected = getIncomeTaxCollected() + incomeTax;
		this.salesTaxCollected = getSalesTaxCollected() + salesTax;

		/* TODO: Add your code here */
	}
	
	public String toString() {
		return "TaxCollector: income tax collected: " + incomeTaxCollected + ", sales tax collected: " + salesTaxCollected;
	}
	
}


