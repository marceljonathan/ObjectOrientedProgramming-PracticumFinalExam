package model;

public abstract class Drink {
	protected String drinkId;
	protected String drinkName;
	protected String drinkType;
	protected int drinkPrice;

	public Drink(String drinkId, String drinkName, String drinkType, int drinkPrice) {
		super();
		this.drinkId = drinkId;
		this.drinkName = drinkName;
		this.drinkType = drinkType;
		this.drinkPrice = drinkPrice;
	}

	public String getDrinkId() {
		return drinkId;
	}

	public void setDrinkId(String drinkId) {
		this.drinkId = drinkId;
	}

	public String getDrinkName() {
		return drinkName;
	}

	public void setDrinkName(String drinkName) {
		this.drinkName = drinkName;
	}

	public String getDrinkType() {
		return drinkType;
	}

	public void setDrinkType(String drinkType) {
		this.drinkType = drinkType;
	}

	public int getDrinkPrice() {
		return drinkPrice;
	}

	public void setDrinkPrice(int drinkPrice) {
		this.drinkPrice = drinkPrice;
	}
	
	public abstract void print();

}
