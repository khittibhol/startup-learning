package org.workspace.solve;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author khittibhol
 *
 */
public class CalculateExpensiveMobileNumber {

	private static final DecimalFormat EXPENSE_FORMAT = new DecimalFormat("#,##0.00");
	public static void main(String[] args) throws IOException {
		Map<String, String[]> expenses = new HashMap<>();

		FileReader file = new FileReader("resources/number.txt");
		BufferedReader reader = new BufferedReader(file);
		try {
			String str;
			while ((str = reader.readLine()) != null) {
				String[] element = str.split(",");
				String number = element[0];
				String timing = element[1];
				String expense = element[2];
				
				if (expenses.containsKey(element[0])) {
					String[] summary = expenses.get(number);
					timing = new BigDecimal(summary[0]).add(new BigDecimal(timing)).toPlainString();
					expense = new BigDecimal(summary[1]).add(new BigDecimal(expense)).toPlainString();
				}
				expenses.put(number, new String[] { timing, expense });
			}

			result(expenses);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {			
			reader.close();
			file.close();
		}
		
		System.exit(0);
	}
	
	private static void result(Map<String, String[]> expenses) {
		System.out.println("====== Start for Calculate the expensive by mobile no. ======");
		
		expenses.entrySet().stream()
			.sorted(Map.Entry.comparingByKey())
			.forEachOrdered(exp -> {
	        	String format = "Mobile No.\t: %s\tTiming\t: %s\nExpense\t: %s";
				
				String[] element = exp.getValue();
				BigDecimal expense = new BigDecimal(element[1]);
				
				System.out.println(String.format(format, exp.getKey(), calcTiming(element[0]), EXPENSE_FORMAT.format(expense)));
				System.out.println("=============================================================");
        });

		System.out.println("Total Mobile No.\t: "+expenses.size());
		System.out.println("Completed!");
	}
	
	private static String calcTiming(String timing) {
		String hhmm = "%s Hour %s Minute";
		long hh = Long.parseLong(timing) / 60;
		long mm = Long.parseLong(timing) % 60;
		
		return String.format(hhmm, hh, mm);
	}
	
}
