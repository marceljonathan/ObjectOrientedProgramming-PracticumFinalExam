package model;

public class MilkTea extends Drink {
	private String milkType;

	public MilkTea(String drinkId, String drinkName, String drinkType, int drinkPrice, String milkType) {
		super(drinkId, drinkName, drinkType, drinkPrice);
		this.milkType = milkType;
	}

	public String getMilkType() {
		return milkType;
	}

	public void setMilkType(String milkType) {
		this.milkType = milkType;
	}

	@Override
	public void print() {
		System.out.printf("%-10s|%-20s|%-15s|%-15d| %-19s|%-20s|\n", drinkId, drinkName, drinkType, drinkPrice, "-", milkType);
	}
	
}
