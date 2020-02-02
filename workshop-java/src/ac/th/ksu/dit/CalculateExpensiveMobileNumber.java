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

//			To do write code here.
		} catch (Exception e) {
			e.printStackTrace();
		} finally {			
			reader.close();
			file.close();
		}
		
		System.exit(0);
	}

}
