import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Maze {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner input = new Scanner(new File("maze.in"));

		final int ROW = 10;
		final int COL = 10;

		char[][] charMap = new char[ROW][COL];
		double[][] mat = new double[ROW][COL];

		int start_row = 0, start_col = 0, end_row = 0, end_col = 0;

		for (int i = 0; i < ROW; i++) {
			String line = input.nextLine();
			for (int j = 0; j < COL; j++) {
				charMap[i][j] = line.charAt(j);
				switch (line.charAt(j)) {
				case 's': mat[i][j] = Double.POSITIVE_INFINITY; start_row = i; start_col = j; break;
				case 'w': mat[i][j] = Double.NaN; break;
				case 'e': end_row = i; end_col = j;
				default: mat[i][j] = Double.POSITIVE_INFINITY;
				}
			}
		}
		input.close();
		
		numSteps(mat, start_row, start_col, 0);
		System.out.println("The solution requires " + mat[end_row][end_col] + " steps.");
		
		if (mat[end_row][end_col] != Double.POSITIVE_INFINITY) 
			showSolution(charMap, mat, end_row, end_col);
		
	}

	public static void numSteps (double[][] mat, int r, int c, int val) {
		if (mat[r][c] == Double.NaN) {
			return;
		}
		else {
			if (val < mat[r][c])  {
				mat[r][c] = val;
				if (r != 0)
					numSteps(mat, r - 1, c, val + 1);
				if (r != mat.length - 1)
					numSteps(mat, r + 1, c, val + 1);
				if (c != 0)
					numSteps(mat, r, c - 1, val + 1);
				if (c != mat[0].length - 1)
					numSteps(mat, r, c + 1, val + 1);
			}
		}
	}
	
	public static void showSolution (char[][] charMap, double[][] mat, int a, int b) {
		while (charMap[a][b] != 's') {
			if (a > 0 && mat[a-1][b] < mat[a][b])
				a = a - 1;
			else if (a < mat.length - 1 && mat[a+1][b] < mat[a][b])
				a = a + 1;
			else if (b > 0 && mat[a][b-1] < mat[a][b])
				b = b - 1;
			else if (b < mat[0].length - 1 && mat[a][b+1] < mat[a][b])
				b = b + 1;
			if (charMap[a][b] != 's') charMap[a][b] = '\u00b7';
		}

		for (char[] row: charMap) {
			for (char c: row) {
				System.out.print(c);
			}
			System.out.println();
		}
	}

}
