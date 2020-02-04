package ac.th.ksu.dit;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author khittibhol
 *
 */
public class CalculateExpensiveMobileNumber {

	public static void main(String[] args) throws IOException {

		FileReader file = new FileReader("resources/number.txt");
		BufferedReader reader = new BufferedReader(file);
		try {
			// To do write code here.
//			int i = 1;
			String data;
			System.out.println("========= Start for Calculate the expensive by mobile no. ========");
			while ((data = reader.readLine()) != null) {
//				System.out.println(i + " : " + data);
//				i = i + 1;
				String readData[] = data.split(",");
				System.out.println("Mobile No.\t: "+readData[0]+"\tTiming : "+readData[1]+"\nExpense : "+readData[2]);
				System.out.println("=======================================================");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			reader.close();
			file.close();
		}

		System.exit(0);
	}

}
