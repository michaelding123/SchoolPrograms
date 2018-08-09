import java.io.File;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Sudoku {

	Scanner scanner;
	final String FILE = "src/SudokuBoard2.txt";
	File file;
	int [][] board= new int [9][9] ;
	int x = 0;

	public static void main(String[] args){
		Sudoku s = new Sudoku();
		s.print();
		s.fill();
		s.validate();
	}

	public void print() {
		Scanner input;		
		try {
			file = new File(FILE);
			input = new Scanner(file);

			while (input.hasNextLine()) {
				String line = input.nextLine();
				System.out.println(line);
			}
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void fill() {
		try {

			file = new File(FILE);
			scanner = new Scanner(file);

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				StringTokenizer token = new StringTokenizer(line,",");
				int y=0;

				while (token.hasMoreTokens()) {
					board[x][y]=Integer.parseInt(token.nextToken());
					y++;
				}
				x++;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isDuplicate(int[] nums) {
		int mark[] = {0,0,0,0,0,0,0,0,0,0};
		for(int i = 0; i < nums.length; i++) {
			if(mark[nums[i]] == 1) {
				System.out.println("Incorrect.");
				return true;
			}
			else {
				mark[nums[i]] = 1;
			}
		}
		return false;	
	}

	public void validate () {
		int nums[] = new int[9];

		// check rows
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				nums[j] = board[i][j];
			}
			if(isDuplicate(nums)) {
				//System.out.println("Not correct");
				return;
			}
		}

		//check columns
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				nums[j] = board[j][i];
			}
			if(isDuplicate(nums)) {
				//System.out.println("Not correct");
				return;
			}
		}

		// check 3x3 boxes
		for(int i = 0; i < 9; i++) {
			switch(i) {
			case 0:
				int bNums[] = {
						board[0][0], board[1][0], board[2][0],
						board[0][1], board[1][1], board[2][1],
						board[0][2], board[1][2], board[2][2]
				};
				if(isDuplicate(bNums)) {
					//System.out.println("Not correct");
					return;
				}
			case 1:
				int bNums1[] = {
						board[3][0], board[4][0], board[5][0],
						board[3][1], board[4][1], board[5][1],
						board[3][2], board[4][2], board[5][2]
				};
				if(isDuplicate(bNums1)) {
					//System.out.println("Not correct");
					return;
				}
			case 2:
				int bNums2[] = {
						board[6][0], board[7][0], board[8][0],
						board[6][1], board[7][1], board[8][1],
						board[6][2], board[7][2], board[8][2]
				};
				if(isDuplicate(bNums2)) {
					//System.out.println("Not correct");
					return;
				}
			case 3:
				int bNums3[] = {
						board[0][3], board[1][3], board[2][3],
						board[0][4], board[1][4], board[2][4],
						board[0][5], board[1][5], board[2][5]
				};
				if(isDuplicate(bNums3)) {
					//System.out.println("Not correct");
					return;
				}
			case 4:
				int bNums4[] = {
						board[3][3], board[4][3], board[5][3],
						board[3][4], board[4][4], board[5][4],
						board[3][5], board[4][5], board[5][5]
				};
				if(isDuplicate(bNums4)) {
					//System.out.println("Not correct");
					return;
				}
			case 5:
				int bNums5[] = {
						board[6][3], board[7][3], board[8][3],
						board[6][4], board[7][4], board[8][4],
						board[6][5], board[7][5], board[8][5]
				};
				if(isDuplicate(bNums5)) {
					//System.out.println("Not correct");
					return;
				}
			case 6:
				int bNums6[] = {
						board[0][6], board[1][6], board[2][6],
						board[0][7], board[1][7], board[2][7],
						board[0][8], board[1][8], board[2][8]
				};
				if(isDuplicate(bNums6)) {
					//System.out.println("Not correct");
					return;
				}
			case 7:
				int bNums7[] = {
						board[3][6], board[4][6], board[5][6],
						board[3][7], board[4][7], board[5][7],
						board[3][8], board[4][8], board[5][8]
				};
				if(isDuplicate(bNums7)) {
					//System.out.println("Not correct");
					return;
				}
			case 8:
				int bNums8[] = {
						board[6][6], board[7][6], board[8][6],
						board[6][7], board[7][7], board[8][7],
						board[6][8], board[7][8], board[8][8]
				};
				if(isDuplicate(bNums8)) {
					//System.out.println("Not correct");
					return;
				}
			}
		}
		System.out.println("Correct");
		scanner.close();

	}

}