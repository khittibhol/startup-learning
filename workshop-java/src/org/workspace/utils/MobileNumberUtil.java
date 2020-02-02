package org.workspace.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MobileNumberUtil {

	public static void main(String[] args) throws IOException {
		MobileNumberUtil.generateMobileNumber(100, 50);
	}

	public static void generateMobileNumber(long records, long distrinctRecords) throws IOException {
		Random random = new Random();
		IntStream stream = random.ints(records, 0, 99999);
		List<String> numbers = stream.boxed().map(in -> {
			String number = "0" + String.valueOf(Math.abs(in));

			if (number.length() < 10) {
				int deficit = 10 - number.length();

				Integer[] between = findBetweenOfBound(deficit);
				IntStream deficitStream = random.ints(1, between[0], between[1]);
				String fillNumber = String.valueOf(deficitStream.findFirst().getAsInt());
				number = number.concat(fillNumber);
			}

			
			return number;
		}).collect(Collectors.toList());
		stream.close();

		if (distrinctRecords > 0) {			
			randomDuplicate(numbers, distrinctRecords);
			swapRecords(numbers);
			
			List<String[]> generate = generateTimingAndExpensive(numbers);
			export(generate);
		}
	}

	private static Integer[] findBetweenOfBound(int intPosition) {
		String begin = "1";
		String end = "9";
		for (int i = 0; i < intPosition; i++) {
			begin = begin.concat("0");
			end = end.concat("9");
		}

		begin = begin.substring(0, intPosition);
		end = end.substring(0, intPosition);

		Integer[] between = new Integer[] { Integer.valueOf(begin), Integer.valueOf(end) };
		return between;
	}
	
	private static void randomDuplicate(List<String> numbers, long distrinctRecords) {
		long totalSize = numbers.size() + distrinctRecords;
		do {
			int random = new Random().ints(1, 0, numbers.size()).findFirst().getAsInt();
			numbers.add(numbers.get(random));
		} while (numbers.size() < totalSize);
	}
	
	private static void swapRecords(List<String> numbers) {
		for (int k = 0; k < numbers.size(); k++) {			
			int random = new Random().ints(1, 0, numbers.size()).findFirst().getAsInt();
			Collections.swap(numbers, k, random);
		}
	}

	private static List<String[]> generateTimingAndExpensive(List<String> numbers) {
		List<String[]> results = new ArrayList<>();
		
		for (String num : numbers) {
			String[] element = new String[3];
			
			Random random = new Random();
			long timing = new BigDecimal(random.doubles(1, 0, 999).findFirst().getAsDouble()).setScale(2, RoundingMode.HALF_UP).longValue();
			BigDecimal expense = new BigDecimal(random.doubles(1, 0, 999).findFirst().getAsDouble()).setScale(2, RoundingMode.HALF_UP);
			
			element[0] = num;
			element[1] = String.valueOf(timing);
			element[2] = expense.toPlainString();
			
			results.add(element);
		}
		
		return results;
	}
	
	private static void export(List<String[]> generate) throws IOException {
		FileWriter fw = null;
		try {
			File file = new File("resources/number.txt");
			if (!file.exists()) {
				file.createNewFile();
			} else {
				file.delete();
				file.createNewFile();
			}
			fw = new FileWriter(file);
			for (int i = 0; i < generate.size(); i++) {
				String[] elements = generate.get(i);
				
				String num = String.format("%s,%s,%s\n", elements);
				if (generate.size() <= i+1) {
					num = num.trim();
				}
				fw.write(num);					
			}
			fw.flush();
			System.out.println("File written Succesfully");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fw != null) {
				fw.close();
			}
		}
	}
}
