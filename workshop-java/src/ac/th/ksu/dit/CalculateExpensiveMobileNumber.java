package ac.th.ksu.dit;

import java.io.BufferedReader;
import java.util.*;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * @author khittibhol
 *
 */
public class CalculateExpensiveMobileNumber {

	public static void main(String[] args) throws IOException {

		FileReader file = new FileReader("resources/number.txt");
		BufferedReader reader = new BufferedReader(file);
		try {
			String data;
			List<String[]> dataExpense = new ArrayList<>();
			Hashtable<String, String[]> table = new Hashtable<>();
			System.out.println("========= Start for Calculate the expensive by mobile no. ========");
			while ((data = reader.readLine()) != null) {
				String readData[] = data.split(",");
				dataExpense.add(data.split(","));
				if (table.containsKey(readData[0])) {
					String[] getData = table.get(readData[0]);
					BigDecimal dTiming = new BigDecimal(getData[0]);
					BigDecimal timing = dTiming.add(new BigDecimal(readData[0]));
					BigDecimal dExpense = new BigDecimal(getData[1]);
					BigDecimal expense = dExpense.add(new BigDecimal(readData[1]));

					table.put(readData[0], new String[] { timing.toPlainString(), expense.toPlainString() });
				} else {
					table.put(readData[0], new String[] { readData[1], readData[2] });
				}

				table.forEach( 
			            (key,value) -> System.out.println("Telephone Number : " + key + ", \tTiming : " + value[0]+ ", \t\tExpense : " + value[1])); 			
				}

			
			System.out.println("HashTable sizing : " + table.size());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			reader.close();
			file.close();
		}

		System.exit(0);
	}

}
