
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Dictionary extends JPanel implements ActionListener {

	private boolean found = false;
	private String text = null;
	private final String FILE = "src/words.txt";
	private File file = new File(FILE);
	private Scanner input;
	private int wordCount = 0;
	private String[] dictionary;
	private JFrame frame = new JFrame("Dictionary");
	private JLabel label = new JLabel();
	private JTextField field = new JTextField(25);
	private JButton button = new JButton("Submit");

	public void setupWindow(Dictionary d) {

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

		//System.out.println("Just entered askForWord()");
		label.setText("Please enter a word.");

	} 

	public static void main (String[] args) {

		Dictionary d = new Dictionary();
		d.setupWindow(d);
		d.readIntoArray();
		d.askForWord();

	}

	public void actionPerformed(ActionEvent e) {

		try {
			//System.out.println("Just entered askForWord()");
			String word; 
			text = field.getText();
			field.setText("");

			for(int i = 0 ; i < dictionary.length ; i++) {
				//System.out.println("In for loop. i: " + i + ", word: " + text);
				word = dictionary[i];

				if (text.equalsIgnoreCase(word)) {
					//System.out.println("word found");
					found = true;
					break;
				} else {
					//System.out.println("not found");
					found = false;					
				}
			}

			text = null;

			if (found) {
				int reply = JOptionPane.showConfirmDialog(null, "Word found. Would you like to enter again?", 
						"Choice", JOptionPane.YES_NO_OPTION);

				if (reply == JOptionPane.YES_OPTION) {
				} else if (reply == JOptionPane.NO_OPTION) {
					System.exit(0);
				}
			} else {					
				int reply = JOptionPane.showConfirmDialog(null, 
						"Word not found. Would you like to enter again?", 
						"Choice", 
						JOptionPane.YES_NO_OPTION);

				if (reply == JOptionPane.YES_OPTION) {
				} else if (reply == JOptionPane.NO_OPTION) {
					System.exit(0);
				}					
			}

			//System.out.println("Past.");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		text = field.getText();
		field.setText("");
		//System.out.println("In actionPerformed(). The word is " + text);

	}

}















