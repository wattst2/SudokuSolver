//Kiersten Dillon
//Ta'Quawn Watts

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class SudokuSolver {

	public static boolean Tester(int[][] puzzle, int N) {
		int row = -1;
		int col = -1;
		boolean Missing = true;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (puzzle[i][j] == 0) {
					row = i;
					col = j;

					Missing = false;
					break;
				}
			}
			if (!Missing) {
				break;
			}
		}

		if (Missing) {
			return true;
		}

		for (int num = 1; num <= N; num++) {
			if (Checker(puzzle, row, col, num)) {
				puzzle[row][col] = num;
				if (Tester(puzzle, N)) {
					return true;
				} else {
					puzzle[row][col] = 0;
				}
			}
		}
		return false;
	}
	
	public static boolean Checker(int[][] puzzle, int row, int col, int num) {
		for (int b = 0; b < puzzle.length; b++) {
			if (puzzle[row][b] == num) {
				return false;
			}
		}

		for (int a = 0; a < puzzle.length; a++) {

			if (puzzle[a][col] == num) {
				return false;
			}
		}

		int sqrt = (int) Math.sqrt(puzzle.length);
		int boxRowStart = row - row % sqrt;
		int boxColStart = col - col % sqrt;

		for (int a = boxRowStart; a < boxRowStart + sqrt; a++) {
			for (int b = boxColStart; b < boxColStart + sqrt; b++) {
				if (puzzle[a][b] == num) {
					return false;
				}
			}
		}

		return true;
	}

	public static void print(int[][] puzzle, int N) {
		for (int a = 0; a < N; a++) {
			for (int b = 0; b < N; b++) {
				System.out.print(puzzle[a][b]);
				System.out.print(" ");
			}
			System.out.print("\n");

			if ((a + 1) % (int) Math.sqrt(N) == 0) {
				System.out.print("");
			}
		}
	}

	public static void main(String args[]) {
		try {
			Scanner user = new Scanner(System.in);
			System.out.println("Enter name of Sudoku file: ");
			String fileName = user.nextLine();
			FileInputStream fis = new FileInputStream(fileName);
			Scanner sc = new Scanner(fis); 

			int[][] puzzle = new int[9][9]; 
			int x = 0;
			while (x < 9) { 
				String line = sc.nextLine();
				String[] nums = line.split(" "); 

				for (int y = 0; y <= 8; y++) 
				{
					puzzle[x][y] = Integer.parseInt(nums[y]); 
				}

				x++; 
			}

			sc.close(); 

			Tester(puzzle, 9);
			print(puzzle, 9);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}






