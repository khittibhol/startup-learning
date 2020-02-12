package org.workspace.solve;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import org.workspace.model.ExpensiveMobileModel;

/**
 * @author khittibhol
 *
 */
public class CalculateExpensiveMobileNumber {

	private static final DecimalFormat EXPENSE_FORMAT = new DecimalFormat("#,##0.00");
	public static void main(String[] args) throws IOException {
		Map<String, ExpensiveMobileModel> expenses = new HashMap<>();

		FileReader file = new FileReader("resources/number.txt");
		BufferedReader reader = new BufferedReader(file);
		try {
			String str;
			while ((str = reader.readLine()) != null) {
				String[] element = str.split(",");
				ExpensiveMobileModel expensive = new ExpensiveMobileModel(element[0], new BigDecimal(element[1]), new BigDecimal(element[2]));
				
				if (expenses.containsKey(expensive.getMobile())) { //หาค่าที่มีอยู่แล้ว
					ExpensiveMobileModel summary = expenses.get(expensive.getMobile());
					summary.setTiming(summary.getTiming().add(expensive.getTiming()));
					summary.setExpense(summary.getExpense().add(expensive.getExpense()));
					expensive = summary;
				}
				expenses.put(expensive.getMobile(), expensive);
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
	
	private static void result(Map<String, ExpensiveMobileModel> expenses) {
		System.out.println("====== Start for Calculate the expensive by mobile no. ======");
		
		expenses.entrySet().stream() // stream เอาไว้ใช้จัดการ data collection เช่น loop
			.sorted(Map.Entry.comparingByKey())
			.forEachOrdered(exp -> {
	        	resultFormat(exp.getValue());
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
	
	private static void resultFormat(ExpensiveMobileModel expensive) {		
		System.out.println("Mobile No.	: "+expensive.getMobile());
		System.out.println("Timing		: "+calcTiming(expensive.getTiming().toPlainString()));
		System.out.println("Expense		: "+EXPENSE_FORMAT.format(expensive.getExpense()));
		System.out.println("=============================================================");
		
	}
	
}
