import java.util.ArrayList;
import java.util.Scanner;

import model.Drink;
import model.Transaction;
import model.MilkTea;
import model.Tea;

public class Main {
	
	private Scanner scan = new Scanner(System.in);
	private DbController db = new DbController();
	private ArrayList<Drink> listDrinks = new ArrayList<>();
	private ArrayList<Transaction> listTransactions = new ArrayList<>();
	
	void cls() {
		for(int i = 0; i < 5; i++) {
			System.out.println("");
		}
	}
	
	private void enter() {
		System.out.print("Press enter to continue...");
		scan.nextLine();
		menu();
	}

	public Main() {
		db.getDrink(listDrinks);
		menu();
	}

	private void menu() {
		cls();
		System.out.println("Ngeteg Yuk!");
		System.out.println("===========");
		System.out.println("1. Buy Tea");
		System.out.println("2. View Transaction");
		System.out.println("3. Delete Transaction");
		System.out.println("4. Exit");
		System.out.print(">> ");
		int choose = -2;
		try {
			choose = scan.nextInt();
		} catch (Exception e) {
			// TODO: handle exception
		}
		scan.nextLine();
		switch (choose) {
		case 1:
			cls();
			buyTea();
			break;

		case 2:
			cls();
			viewTr();
			break;
			
		case 3:
			cls();
			deleteTr();
			break;
			
		case 4:
			cls();
			System.out.println("Thank you for using Ngeteh Yuk! Appliaction!");
			break;
		}
	}
	
	private void viewDrinkList() {
		int num = 1;
		System.out.println("|==============================================================================================================|");
		System.out.printf("|%-4s|%-10s|%-20s|%-15s|%-15s|%-20s|%-20s|\n", "No", "Drink ID", "Drink Name", "Drink Type", "Drink Price", "Sugar Type", "Milk Type");
		System.out.println("|==============================================================================================================|");
		for (Drink d : listDrinks) {
			if(d instanceof Tea) {
				System.out.printf("|%-4d|", num);
				d.print();
			}else if(d instanceof MilkTea) {
				System.out.printf("|%-4d|", num);
				d.print();
			}
			num++;
		}
		System.out.println("|==============================================================================================================|");
		System.out.println("");
	}

	private void buyTea() {
		String transactionId = "TR" + (int) (0 + Math.random()*10) + (int) (0 + Math.random()*10) + (int) (0 + Math.random()*10);
		String drinkId;
		String buyerName;
		int qty = -1, choose = -1;
		
		viewDrinkList();
		
		do {
			System.out.print("Input the number of index you want to buy[1 .. " + listDrinks.size() + "] : ");
			try {
				choose = scan.nextInt();
			} catch (Exception e) {
				// TODO: handle exception
			}
			scan.nextLine();
		} while (choose < 1 || choose > listDrinks.size());
		
		do {
			System.out.print("Input quantity [>0] : ");
			try {
				qty = scan.nextInt();
			} catch (Exception e) {
				// TODO: handle exception
			}
			scan.nextLine();
		} while (qty < 1);
		
		do {
			System.out.print("Input your name [must be 2 words] : ");
			buyerName = scan.nextLine();
		} while (!buyerName.contains(" "));
		
		int tax = -1, price = -1;
		
		System.out.println("|==================================================================|");
		System.out.println("|                      Detail Transaction                          |");
		System.out.println("|==================================================================|");
		System.out.printf("|%-19s:%-46s|\n", "Transaction ID", transactionId);
		System.out.printf("|%-19s:%-46s|\n", "Buyer Name", buyerName);
		System.out.printf("|%-19s:%-46s|\n", "Drink Name", getDrinkName(choose));
		System.out.printf("|%-19s:%-46d|\n", "Drink Price", getDrinkPrice(choose));
		System.out.printf("|%-19s:%-46d|\n", "Quantity", qty);
		if(getDrinkType(choose).equals("t")) {
			tax = 2000;
			price = (getDrinkPrice(choose)*qty)+tax;
		}else if(getDrinkType(choose).equals("mt")) {
			tax = 3500;
			price = (getDrinkPrice(choose)*qty)+tax;
		}
		System.out.printf("|%-19s:%-46d|\n", "Tax", tax);
		System.out.printf("|%-19s:%-46d|\n", "Total Price", price);
		System.out.println("|==================================================================|");
		
		db.insertTr(transactionId, getDrinkId(choose), buyerName, qty);
		
		System.out.println("");
		System.out.println("Successfully inserted new transaction!");
		
			
		enter();
	}
	
	private String getDrinkId(int choose) {
		int num = 1;
		for (Drink d : listDrinks) {
			if(d instanceof Tea && num == choose) {
				return d.getDrinkId();
			}else if(d instanceof MilkTea && num == choose) {
				return d.getDrinkId();
			}
			num++;
		}
		return null;
	}
	
	private String getDrinkName(int choose) {
		int num = 1;
		for (Drink d : listDrinks) {
			if(d instanceof Tea && num == choose) {
				return d.getDrinkName();
			}else if(d instanceof MilkTea && num == choose) {
				return d.getDrinkName();
			}
			num++;
		}
		return null;
	}
	
	private String getDrinkType(int choose) {
		int num = 1;
		for (Drink d : listDrinks) {
			if(d instanceof Tea && num == choose) {
				return "t";
			}else if(d instanceof MilkTea && num == choose) {
				return "mt";
			}
			num++;
		}
		return null;
	}
	
	private int getDrinkPrice(int choose) {
		int num = 1;
		for (Drink d : listDrinks) {
			if(d instanceof Tea && num == choose) {
				return d.getDrinkPrice();
			}else if(d instanceof MilkTea && num == choose) {
				return d.getDrinkPrice();
			}
			num++;
		}
		return 0;
	}
	
	private void viewTrList() {
		int num = 1;
		System.out.println("|========================================================================================================================================|");
		System.out.printf("|%-5s|%-18s|%-25s|%-15s|%-15s|%-15s|%-10s|%-10s|%-15s|\n", "ID", "Transaction ID", "Buyer Name", "Drink Name", "Drink Type", "Drink Price", "Quantity", "Tax", "Total Price");
		System.out.println("|========================================================================================================================================|");
		for (Transaction t : listTransactions) {
			int tax = drinkTax(t.getDrinkId());
			int qty = t.getQty();
			int price = drinkPrice(t.getDrinkId());
			int totalPrice = (price * qty)+tax;
			System.out.printf("|%-5d|%-18s|%-25s|%-15s|%-15s|%-15d|%-10d|%-10d|%-15d|\n", num, t.getTransactionId(), t.getBuyerName(), drinkName(t.getDrinkId()), drinkType(t.getDrinkId()), drinkPrice(t.getDrinkId()), t.getQty(), drinkTax(t.getDrinkId()), totalPrice);
			num++;
		}
		System.out.println("|========================================================================================================================================|");
		System.out.println("");
	}
	
	private int drinkTax(String id) {
		int num = 1;
		for (Drink d : listDrinks) {
			if(d instanceof Tea && d.getDrinkId().equals(id)) {
				return 2000;
			}else if(d instanceof MilkTea && d.getDrinkId().equals(id)) {
				return 3500;
			}
			num++;
		}
		
		return 0;
	}
	
	private String drinkName(String id) {
		int num = 1;
		for (Drink d : listDrinks) {
			if(d instanceof Tea && d.getDrinkId().equals(id)) {
				return d.getDrinkName();
			}else if(d instanceof MilkTea && d.getDrinkId().equals(id)) {
				return d.getDrinkName();
			}
			num++;
		}
		
		return null;
	}
	
	private String drinkType(String id) {
		int num = 1;
		for (Drink d : listDrinks) {
			if(d instanceof Tea && d.getDrinkId().equals(id)) {
				return d.getDrinkType();
			}else if(d instanceof MilkTea && d.getDrinkId().equals(id)) {
				return d.getDrinkType();
			}
			num++;
		}
		
		return null;
	}
	
	private int drinkPrice(String id) {
		int num = 1;
		for (Drink d : listDrinks) {
			if(d instanceof Tea && d.getDrinkId().equals(id)) {
				return d.getDrinkPrice();
			}else if(d instanceof MilkTea && d.getDrinkId().equals(id)) {
				return d.getDrinkPrice();
			}
			num++;
		}
		
		return 0;
	}

	private void viewTr() {
		db.getTr(listTransactions);
		if(listTransactions.isEmpty()) {
			System.out.println("There is no transaction...");
			System.out.println("");
		}else {
			viewTrList();
		}
		listTransactions.clear();
		
		enter();
	}

	private void deleteTr() {
		db.getTr(listTransactions);
		if(listTransactions.isEmpty()) {
			System.out.println("There is no transaction...");
			System.out.println("");
		}else {
			viewTrList();
			
			int choose = -1;
			do {
				System.out.print("Input the number of index you want to delete[1 .. " + listTransactions.size() + "] : ");
				try {
					choose = scan.nextInt();
				} catch (Exception e) {
					// TODO: handle exception
				}
				scan.nextLine();
			} while (choose < 1 || choose > listTransactions.size());
			
			db.deleteTr(getTrId(choose));
			
			System.out.println("");
			System.out.println("Successfully delete transaction!");
		}
		listTransactions.clear();
		enter();
	}

	private String getTrId(int choose) {
		int num = 1;
		for (Transaction t : listTransactions) {
			if(num == choose) {
				return t.getTransactionId();
			}
			num++;
		}
		
		return null;
	}

	public static void main(String[] args) {
		new Main();
	}

}
