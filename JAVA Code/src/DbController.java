import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Drink;
import model.Transaction;
import model.MilkTea;
import model.Tea;

public class DbController {
	private Connect c = Connect.getConnection();
	
	public void getDrink(ArrayList<Drink> listDrinks) {
		String qT = "SELECT * FROM tea";
		String qMT = "SELECT * FROM milktea";
		
		ResultSet rs = c.executeQuery(qT);
		
		try {
			while(rs.next()) {
				String drinkId = rs.getString("DrinkID");
				String drinkName = rs.getString("DrinkName");
				String drinkType = rs.getString("DrinkType");
				int drinkPrice = rs.getInt("DrinkPrice");
				String sugarType = rs.getString("SugarType");
				
				listDrinks.add(new Tea(drinkId, drinkName, drinkType, drinkPrice, sugarType));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ResultSet rs2 = c.executeQuery(qMT);
		
		try {
			while(rs2.next()) {
				String drinkId = rs2.getString("DrinkID");
				String drinkName = rs2.getString("DrinkName");
				String drinkType = rs2.getString("DrinkType");
				int drinkPrice = rs2.getInt("DrinkPrice");
				String milkType = rs2.getString("MilkType");
				
				listDrinks.add(new MilkTea(drinkId, drinkName, drinkType, drinkPrice, milkType));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void insertTr(String transactionId, String drinkId, String buyerName, int qty) {
		String q = String.format("INSERT INTO transaction VALUES ('%s', '%s', '%s', '%d')", transactionId, drinkId, buyerName, qty);
		
		c.executeUpdate(q);
	}
	
	
	public void getTr(ArrayList<Transaction> listTransactions) {
		String q = "SELECT * FROM transaction";
		
		ResultSet rs = c.executeQuery(q);
		
		try {
			while(rs.next()) {
				String transactionId = rs.getString("TransactionID");
				String drinkId = rs.getString("DrinkID");
				String buyerName = rs.getString("BuyerName");
				int qty = rs.getInt("Quantity");
				
				listTransactions.add(new Transaction(transactionId, drinkId, buyerName, qty));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void deleteTr(String id) {
		String q = String.format("DELETE FROM transaction WHERE TransactionID = '%s'", id);
		
		c.executeUpdate(q);
	}
}
