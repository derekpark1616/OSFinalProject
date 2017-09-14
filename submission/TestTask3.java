//---------------------------------------------------------------
//File TestTask3.java
//-----------------------------------------------------------------

import java.io.*;
import java.util.Scanner;

public class TestTask3 {
	static public void main(String[] args) {
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
				System.exit(1); // error			
			}

			
			Integer countA = 0, countB = 0, countC = 0;
			Scanner input = new Scanner(file);

			Boolean failed = false;
			while(input.hasNextLine()) {
				String line = input.nextLine();

				if (line.charAt(0) == 'A')
					countA++;
				else if (line.charAt(0) == 'B')
					countB++;
				else if (line.charAt(0) == 'C')
					countC++;
				
				if( Math.abs(countA - countB) > 1 || Math.abs(countB - countC) > 1 
					|| Math.abs(countA - countC) > 1) {
					failed = true;
					break;	
				}
			}

			if (countA == 0) // nothing in the output
				failed = true; 

			if (failed)
				System.exit(1); // error			
		} catch (Exception e) {
			System.out.print("Exception: ");
			System.out.println(">" + e.toString() + "<");
		}
	}
}