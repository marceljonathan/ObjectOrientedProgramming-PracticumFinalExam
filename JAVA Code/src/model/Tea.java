package model;

public class Tea extends Drink {
	private String sugarType;

	public Tea(String drinkId, String drinkName, String drinkType, int drinkPrice, String sugarType) {
		super(drinkId, drinkName, drinkType, drinkPrice);
		this.sugarType = sugarType;
	}

	public String getSugarType() {
		return sugarType;
	}

	public void setSugarType(String sugarType) {
		this.sugarType = sugarType;
	}

	@Override
	public void print() {
		System.out.printf("%-10s|%-20s|%-15s|%-15d|%-20s| %-19s|\n", drinkId, drinkName, drinkType, drinkPrice, sugarType, "-");
	}
	
}
