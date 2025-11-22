// 21V1066 RALFS RAINERS Supe

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
public class Main  {



		static Scanner sc = new Scanner(System.in);
	    private static final String STUDENT_INFO = "21v1066 Ralfs Rainers Supe 16 ";

		public static void main(String[] args) {
			int choise;
			String choiseStr;

			loop: while (true) {

				System.out.println("\n1) Create");
				System.out.println("2) Calculate");
				System.out.println("3) View");
				System.out.println("4) About");
				System.out.println("5) Exit");
				System.out.println("\nInput number from 1 till 5: ");
				
				choiseStr = sc.nextLine();
				
				try {
					choise = Integer.parseInt(choiseStr);
					if (choise < 1 || choise > 5) {
						throw new Exception();
					}
				}
				catch (Exception ex) {
					System.out.println("please, input number from 1 till 5");
					continue;
				}
				

				switch (choise) {
				case 1:
					create();
					break;
				case 2:
					calculate();
					break;
				case 3:
					view();
					break;
				case 4:
					about();
					break;
				case 5:
					break loop;
				}
			}

			sc.close();

		}

                @SuppressWarnings("ConvertToTryWithResources")
		public static void create() {
			
			String filename;
			System.out.print("\nInput file name (with path): ");
			filename = sc.nextLine();
			
			int count;
			String inputStr;
			
			System.out.print("Input count of numbers: ");
			try {
				inputStr = sc.nextLine();
				count = Integer.parseInt(inputStr);
				if (count < 0) {
					System.out.println("Error: Count must be non-negative.");
					return;
				}
			}
			catch (NumberFormatException ex){
				System.out.println("Error: Input is not a valid integer.");
				return;
			}

			try {
				DataOutputStream out = new DataOutputStream(
						new FileOutputStream(filename));

				for (int i = 1; i <= count; i++) {
					System.out.printf("Input number %d/%d: ", i, count);
					try {
						inputStr = sc.nextLine();
						int number = Integer.parseInt(inputStr);
						out.writeInt(number); 
					}
					catch (NumberFormatException e) {
						System.out.println("Error: Input is not a valid integer. Skipping remaining numbers.");
						break;
					}
				}
				
				out.close();
				System.out.println("File created successfully.");
			}
			catch (IOException ex) {
				System.out.println("I/O Error: " + ex.getMessage());
			}

		}

		public static void calculate() {
		    String sourceFilename;
		    String targetFilename;
		    String numberToExcludeStr;
		    int numberToExclude;
		    
		    System.out.print("\nInput source file name (to read from): ");
		    sourceFilename = sc.nextLine();
		    
		    System.out.print("Input target file name (to write modified content): ");
		    targetFilename = sc.nextLine();

		    System.out.print("Input number to exclude (e.g., 5): ");
		    numberToExcludeStr = sc.nextLine();
		    
		    try {
		        numberToExclude = Integer.parseInt(numberToExcludeStr);
		    } catch (NumberFormatException ex) {
		        System.out.println("Error: The excluded number must be an integer.");
		        return;
		    }
		    
		    DataInputStream in = null;
		    DataOutputStream out = null;
		    
		    try {
		        in = new DataInputStream(new FileInputStream(sourceFilename));
		        out = new DataOutputStream(new FileOutputStream(targetFilename));
		        
		        int numbersWritten = 0;
		        int currentNumber;
		        boolean EOF = false;
		        
		        while (!EOF) {
		            try {
		                currentNumber = in.readInt();
		                
		                if (currentNumber != numberToExclude) {
		                    out.writeInt(currentNumber);
		                    numbersWritten++;
		                }
		            } catch (EOFException e) {
		                EOF = true;
		            } catch (IOException e) {
		                System.out.println("Error reading or writing data: " + e.getMessage());
		                break;
		            }
		        }
		        
		        System.out.println("\n--- Calculate Results ---");
		        System.out.println("Source file: " + sourceFilename);
		        System.out.println("Excluded number: " + numberToExclude);
		        System.out.println("Modified content saved to target file: " + targetFilename);
		        System.out.println("Total numbers written to target file: " + numbersWritten);
		        
		    } catch (FileNotFoundException ex) {
		        System.out.println("Error: Source file not found (" + ex.getMessage() + ")");
		    } catch (IOException ex) {
		        System.out.println("An I/O error occurred: " + ex.getMessage());
		    } finally {
		        try {
		            if (in != null) in.close();
		        } catch (IOException e) {
		            System.out.println("Warning: Error closing input stream: " + e.getMessage());
		        }
		        try {
		            if (out != null) out.close();
		        } catch (IOException e) {
		            System.out.println("Warning: Error closing output stream: " + e.getMessage());
		        }
		    }
		}

		public static void view() {
			String filename;
			DataInputStream in = null;
			int i = 0;

			System.out.print("\nInput file name: ");
			filename = sc.nextLine();
			
			try {
				in = new DataInputStream(new FileInputStream(filename));
			}
			catch (FileNotFoundException ex) {
				System.out.println(ex.getMessage());
				return;
			}

			System.out.print("\nContent of file: ");
			boolean EOF = false;
			while (!EOF) {
				try {
					i = in.readInt();
					System.out.print(i + " ");
				}
				catch (EOFException e) {
					EOF = true;
				}
				catch (IOException e) {
					System.out.println("\nError: Bad file format, not all numbers could be read successfully.");
					break;
				}
			}
			
			try {
				in.close();
			}
			catch (IOException e) {
				System.out.println(e.getMessage());
			}

			System.out.println();
		}

		public static void about() {
			System.out.println("\n--- Programmas Informācija ---");
			System.out.println("Laboratorijas darbs Nr. 11: Darbs ar bināriem failiem");
			System.out.println("Izstrādātājs: " + STUDENT_INFO);
			System.out.println("------------------------------------");
		}

	}
