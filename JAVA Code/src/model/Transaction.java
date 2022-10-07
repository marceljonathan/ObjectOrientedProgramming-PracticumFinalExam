package model;

public class Transaction {
	private String transactionId;
	private String drinkId;
	private String buyerName;
	private int qty;

	public Transaction(String transactionId, String drinkId, String buyerName, int qty) {
		super();
		this.transactionId = transactionId;
		this.drinkId = drinkId;
		this.buyerName = buyerName;
		this.qty = qty;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getDrinkId() {
		return drinkId;
	}

	public void setDrinkId(String drinkId) {
		this.drinkId = drinkId;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

}
