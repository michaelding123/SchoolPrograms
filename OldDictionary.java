/********
Michael Ding
5/8/18
Dictionary class
 ********/
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class OldDictionary extends JPanel implements ActionListener {

	boolean found = false;
	String text = null;
	final String FILE = "src/words.txt";
	File file = new File(FILE);
	Scanner input;
	int wordCount = 0;
	String[] dictionary;
	JFrame frame = new JFrame("Dictionary");
	JLabel label = new JLabel();
	JTextField field = new JTextField(25);
	JButton button = new JButton("Submit");
	Object monitor = new Object();

	public void setupWindow(OldDictionary d) {

		frame.setSize(600, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		d.add(label);
		d.add(field);
		d.add(button);

		frame.add(d);
		frame.setVisible(true);
		button.addActionListener(d);

	}

	public void readIntoArray() {
		try {

			file = new File(FILE);
			input = new Scanner(file);

			while(input.hasNextLine()) {
				input.nextLine();
				wordCount++;
				if(input.hasNextLine()) {
					continue;
				}
			}

			//file = new File(FILE);

			input = new Scanner(file);
			dictionary = new String[wordCount];

			for(int i = 0 ; i < wordCount; i++) {
				//System.out.println("Here");
				dictionary[i] = input.nextLine();
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public void askForWord() {
		while (true) {
			try {
				System.out.println("Just entered askForWord()");
				label.setText("Please enter a word.");

				synchronized(monitor) {
					while(text == null) {
						monitor.wait();
						System.out.println("Just notified with word: " + text);
					}
				}

				String word;

				if (text != null) {
					for(int i = 0 ; i < dictionary.length ; i++) {
						//System.out.println("In for loop. i: " + i + ", word: " + text);
						word = dictionary[i];

						if (text.equalsIgnoreCase(word)) {
							System.out.println("word found");
							found = true;
							break;
						} else {
							//System.out.println("not found");
							found = false;					
						}
					}
				}
				text = null;

				if (found) {

					label.setText("Word found. Would you like to enter again?");
					
					synchronized (monitor) {
						while (text == null) {
							monitor.wait();
							System.out.println("Just notified with choice: " + text);
						}
					}

					if (text.equalsIgnoreCase("yes")) {
						System.out.println("In word.equals ... yes, user entered " + text);
					} else if (text.equalsIgnoreCase("no")) {
						System.out.println("In word.equals ... no, user entered " + text);
						System.exit(0);
					}
					System.out.println("Past word found.");
				}

				else if (!found) {
					label.setText("Word not found. Would you like to try again?");
					
					synchronized(monitor) {
						while(text == null) {
							monitor.wait();
							System.out.println("Just notified with choice: " + text);
						}
					}

					if (text.equalsIgnoreCase("yes")) {
						System.out.println("In word.equals ... yes");
					} else if (text.equalsIgnoreCase("no")) {
						System.out.println("In word.equals ... no");					
						System.exit(0);
					}
					System.out.println("Past word not found.");
				}
		
			System.out.println("Past.");
			text = null;
			
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		} 

	} 

	public static void main (String[] args) {

		OldDictionary d = new OldDictionary();
		d.setupWindow(d);
		d.readIntoArray();
		d.askForWord();

	}

	public void actionPerformed(ActionEvent e) {
		text = field.getText();
		System.out.println("In actionPerformed(). The word is " + text);

		synchronized(monitor) {
			System.out.println("Calling notifyAll() with word equals " + text);
			monitor.notifyAll();
		}

	}

}
















