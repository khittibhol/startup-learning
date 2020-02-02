package ac.th.ksu.dit;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Napatwarun.Sr
 *
 */
public class CashWithdraw {
	private static BigDecimal CURRENT_BALANCE = new BigDecimal("5000");
	private static final DecimalFormat BALANCE_FORMAT = new DecimalFormat("#,##0.00");
	private static final String USERNAME = "Khittibhol";
	private static final String PASSWORD = "p@ssw0rd";
	private static final int INCORRECT_AUTH = 3;

	public static void main(String[] args) {
		starting();

		Scanner press = new Scanner(System.in);
		String inUsername, inPassword;

		int counterAuthFailed = 0;

		boolean isAuthenticated = false;
		do {
			press = new Scanner(System.in);

			System.out.print("Username : ");
			inUsername = press.nextLine();

			System.out.print("Password : ");
			inPassword = press.nextLine();

			if (USERNAME.equals(inUsername) && PASSWORD.equals(inPassword)) {
				isAuthenticated = true;
				System.out.println("Authentication Passed!");
			} else {
				System.out.println("Authentication Failed!");
				counterAuthFailed++;
				if (INCORRECT_AUTH == counterAuthFailed) {
					System.out.println("Account Locked!");
					System.exit(0);
				}
			}

		} while (!isAuthenticated);
		
		do {
			checkoutBalance();
			BigDecimal modBalance = CURRENT_BALANCE.remainder(new BigDecimal(100)); // มอด
			BigDecimal canWithdraw = CURRENT_BALANCE.subtract(modBalance); //ลบ
			System.out.println("The amount that you can withdraw : " + BALANCE_FORMAT.format(canWithdraw));
			try {
				press = new Scanner(System.in);

				String inWithDraw;
				System.out.print("Press the amount you wish to withdraw : ");
				inWithDraw = press.nextLine();
				
				validateWithDraw(inWithDraw, canWithdraw);
				
				String confirm;
				do {
					System.out.print("Do you want confirm to withdraw (Press Y/N)? : ");
					confirm = press.next();
					if (confirm.equalsIgnoreCase("N")) {
						throw new Exception("Cancel this transaction.");
					}
				} while (!confirm.equalsIgnoreCase("Y"));
				
				System.out.println("Processing withdraw...");
				
				creditFinance(inWithDraw);
				aggregateBalance(inWithDraw);
				
			} catch (Exception e) {
				System.out.println("Can not to withdraw : " + e.getMessage());
			}
		} while (continueWithDraw(press));

		press.close();
		
		System.exit(0);;
	}

	private static boolean continueWithDraw(Scanner press) {
		String proceed;
		do {
			System.out.print("Do you want to proceed with the transaction (Press Y/N)? : ");
			proceed = press.next();
			if (proceed.equalsIgnoreCase("N")) {
				closed();
				return false;
			}
		} while (!proceed.equalsIgnoreCase("Y"));
		
		return true;
	}

	private static void starting() {
		System.out.println("==================================");
		System.out.println("===== Starting Cash Withdraw =====");
		System.out.println("==================================");
	}

	private static void checkoutBalance() {
		System.out.print(String.format("\nAccount Name : %s \t", USERNAME));
		System.out.println("Balance : " + BALANCE_FORMAT.format(CURRENT_BALANCE));
	}

	private static void validateWithDraw(String inWithDraw, BigDecimal canWithDraw) throws Exception {
		Pattern pattern = Pattern.compile("[0-9]+");
		Matcher matcher = pattern.matcher(inWithDraw);
		BigDecimal modInWithDraw = new BigDecimal(inWithDraw).remainder(new BigDecimal(100)); 
		
		if (matcher.matches()) {
			if (new BigDecimal(100).compareTo(new BigDecimal(inWithDraw)) == 1) {
				throw new Exception("Amount must be from 100 up");
			}

			if (canWithDraw.compareTo(new BigDecimal(inWithDraw)) < 0) {
				throw new Exception("Overdue balance");
			}
			if (new BigDecimal(0).compareTo(modInWithDraw) != 0){
				throw new Exception("This terminal available for 100-baht Banknote up");
			}
		} else {
			throw new Exception("Invalid format");
		}
	}

	private static void creditFinance(String inWithDraw) {
		CURRENT_BALANCE = CURRENT_BALANCE.subtract(new BigDecimal(inWithDraw));
	}

	private static void aggregateBalance(String inWithDraw) {
		System.out.println("==================================");
		System.out.println("===== Aggregate Cash Withdraw ====");
		System.out.println("==================================");
		System.out.print(String.format("Account Name : %s \t", USERNAME));
		System.out.println("Balance : " + BALANCE_FORMAT.format(CURRENT_BALANCE));

		System.out.println("===== Completed Cash Withdraw ====");

	}
	
	private static void closed() {
		System.out.println("==================================");
		System.out.println("====== Closed Cash Withdraw ======");
		System.out.println("==================================");
	}
}
