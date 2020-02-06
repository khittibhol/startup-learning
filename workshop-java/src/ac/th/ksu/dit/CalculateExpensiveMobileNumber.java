package ac.th.ksu.dit;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
			// List<Object[][]> groupMobile = new ArrayList<>();
			// To do write code here.
			int i = 0;
			String dataMobile[][]=new String[150][3];
			String data;
			System.out.println("========= Start for Calculate the expensive by mobile no. ========");
			while ((data = reader.readLine()) != null) {
//				System.out.println(i + " : " + data);

				String readData[] = data.split(",");
			//	System.out.println("Mobile No.\t: "+readData[0]+"\tTiming : "+readData[1]+"\tExpense : "+readData[2]);
			//	System.out.println("=======================================================");
				//
				// String[] mobile = new String[] { readData[0] };
				// String[] timingAndExpense = new String[] { readData[1],
				// readData[2] };
				//
				// groupMobile.add(new Object[][] { mobile, timingAndExpense });
				
				dataMobile[i][0] = readData[0];
				dataMobile[i][1] = readData[1];
				dataMobile[i][2] = readData[2];
				//-------------
				/*
				for (int r = 0; r < dataMobile.length; r++) {
					for (int c = 0; c < dataMobile[r].length; c++) {
						dataMobile[r][c] = readData[c];
						System.out.println(dataMobile.length);
						System.out.println(dataMobile[r].length);
						
					}
				}
				*/
				//----------
				i++;
			}
			for (String[] a : dataMobile) {
				for (String b : a) {
					System.out.print(b + "  ");
				}
				System.out.println();
			}
			// for (int i = 0; i < groupMobile.size(); i++) {
			// Object[][] element = groupMobile.get(i);
			// String[] mobile = (String[]) element[0];
			// String[] timinAndExpense = (String[]) element[1];
			// System.out.println(i+" : "+mobile[0] + " : " + timinAndExpense[0]
			// + " : " + timinAndExpense[1]);
			// }

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			reader.close();
			file.close();
		}

		System.exit(0);
	}

}
