package student;

import java.util.Scanner;

public class Nareerat {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String name = "nareerat";
		String pass = "phukan";
		double b = 5000;
		int i = 0;
		int f = 0;
		boolean m = true;

		Scanner sn = new Scanner(System.in);

		do {
			System.out.print("input User Name" + " ");
			String n = sn.nextLine();

			System.out.print("input Password" + " ");
			String p = sn.nextLine();

			if (n.equals(name) && (p.equals(pass))) {
				// i = 3;
				do {
					System.out.print("User :: " + name);
					System.out.println(" \tยอดเงินคงเหลือ  = " + b);

					System.out.print("ระบุจำนวนเงินที่ต้องการถอน " + " ");
					f = sn.nextInt();

					if (f > b) {
						System.out.println(" เงินในบัญชีมีไม่พอจ่าย.");
					} else if (f < 100) {
						System.out.println("ถอนขั้นต่ำ 100 บาท ");
					} else if (f % 100 != 0) {
						System.out.println(" ตู้สามารถจ่ายแบงค์ 100 500 1000 ได้เท่านั้น");
					} else {
						b = b - f;
						System.out.println("คุณถอนไปจำนวน  " + f + "  บาท " + "\tจำนวนเงินคงเหลือ  " + b);
						m = false;
					}

				} while (m);
				System.out.println("จบการทำงาน");
				return;
			} else {
				i++;
				System.out.println("ขออภัยคุณใส่รหัสผิด  " + i + " ครั้ง");
			}
		} while (i < 3);
		// System.out.println("จบการทำงาน");
	}
}
//การตั้งชื่อตัวแปรมีผล
