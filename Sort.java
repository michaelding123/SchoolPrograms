import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Sort {

	public static void main(String[] args) {

		Scanner input, scanner;
		String[] words; 
		final String FILE = "src/unsorted.txt";
		File file;
		int x = 0;

		try {
			
			file = new File(FILE);
			input = new Scanner(file);
			
			int wordCount = 0;
			while(input.hasNextLine()) {
				input.nextLine();
				wordCount++;
				if(input.hasNextLine()) {
					continue;
				}
			}
			
			words = new String[wordCount];		
			scanner = new Scanner(file);
			
			System.out.println("There are " + wordCount + " words in this file.");
			
			while (scanner.hasNextLine()) {
				//System.out.println("In first while() loop");
				String line = scanner.nextLine();
				//System.out.println(line);
				words[x] = line;
				x++;
			}			
			//System.out.println(x);		
			for(int i = 0; i < words.length - 1; i++)	{                                               
				for(int j = 0; j < words.length - 1; j++) {                                           
					if(words[j].compareTo(words[j + 1]) > 0) {                                       
						String temp = words[j];                    
						words[j] = words[j + 1];              
						words[j + 1] = temp;                  
					}                                       
				}
			}	 
			//Arrays.sort(words);	
			for(int i = 0; i < words.length; i++) {
				System.out.println(words[i]);
			}

			final String FILE2 = "src/sorted.txt";
			File dstFile;
			PrintWriter outFile;
			dstFile = new File(FILE2); // create the File object

			try {
				outFile = new PrintWriter(dstFile);
				System.out.println("Writing line to file...");
				for(int a = 0; a < words.length; a++) {
					outFile.println(words[a]);
				}
				System.out.println("Done writing line to file.");
				outFile.close();
			} catch (Exception ex) {				
				ex.printStackTrace();
			}
			input.close();
			scanner.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}