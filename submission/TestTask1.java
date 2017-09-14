//---------------------------------------------------------------
//File TestTask1.java
//-----------------------------------------------------------------

import java.io.*;
import java.util.Scanner;

public class TestTask1 {
	static public void main(String[] args) {
		Boolean failed = false;

		if (args.length != 1) {
			System.out.println("Usage: TestTask1 file_name");
			System.exit(1); // error			
		}

		try{
			String file_path = args[0];

			File file = new File(file_path);
			if (!file.exists()) {
				System.out.println("File " + file_path + " does not exist.");
				System.out.println("Check if you have removed any file by mistake.");
				System.exit(1);
			}

			
			Integer countA = 0, countB = 0;
			Scanner input = new Scanner(file);

			while(input.hasNextLine()) {
				String line = input.nextLine();

				if (line.charAt(0) == 'A')
					countA++;
				else if (line.charAt(0) == 'B')
					countB++;
				
				if(countA - countB > 1) {
					failed = true;
					break;	
				}
			}

			if (countA == 0) // nothing in the output
				failed = true;

			if (failed) {
				System.exit(1);
			}
		} catch (Exception e) {
			System.out.print("Exception: ");
			System.out.println(">" + e.toString() + "<");
		}
	}
}