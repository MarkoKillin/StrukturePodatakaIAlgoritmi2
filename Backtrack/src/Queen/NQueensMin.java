package Queen;

import java.util.Iterator;

public class NQueensMin {
	private int n;
	private int[] table;
	private int[] firstAvailableCell;
	private int slCounter;
	public NQueensMin(int n) {
		this.n = n;
		this.table = new int[n];
		this.firstAvailableCell = new int[n];
		this.slCounter = 0;
	}
	
	public void find() {
		int currentRow=0;
		firstAvailableCell[currentRow]=0;
		while(currentRow>=0) {
			while(firstAvailableCell[currentRow]<n) {
				table[currentRow] = firstAvailableCell[currentRow];
				firstAvailableCell[currentRow]++;
				updateCells(currentRow);
				if(currentRow==n-1)
					printSolution();
				else {
					currentRow++;
					firstAvailableCell[currentRow]=0;
					updateCells(currentRow);
				}
			}
			currentRow--;
		}
	}
	
	private void updateCells(int currentRow) {
		for (int j = firstAvailableCell[currentRow]; j < n; j++) {
			boolean available = true;
			for (int i = 0; i < currentRow; i++) {
				if(j==table[i] || Math.abs(i-currentRow) == Math.abs(j-table[i])) {
					available = false;
					break;
				}
			}
			if(available) {
				firstAvailableCell[currentRow]=j;
				return;
			}
		}
		firstAvailableCell[currentRow]=n;
	}
	
	private void printSolution() {
		slCounter++;
		System.out.println("Solution:" + slCounter);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(j==table[i])
					System.out.print("X ");
				else
					System.out.print("- ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
}
