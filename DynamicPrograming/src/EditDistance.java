import java.util.Stack;

public class EditDistance {
	String s1,s2;
	private int[][] d;
	public EditDistance(String s1, String s2) {
		super();
		this.s1 = s1;
		this.s2 = s2;
		this.d = new int[s1.length()+1][s2.length()+1];
	}
	public void computeDistance() {
		for (int i = 0; i <= s1.length(); i++) d[i][0] = i;
		for (int i = 0; i <= s2.length(); i++) d[0][i] = i;
		for (int i = 1; i <= s1.length(); i++) {
			for (int j = 1; j <= s2.length(); j++) {
				char c1 = s1.charAt(i-1);
				char c2 = s2.charAt(j-1);
				if(c1==c2)
					d[i][j] = d[i-1][j-1];
				else {
					int add = d[i][j-1];
					int sub = d[i-1][j-1];
					int del = d[i-1][j];
					d[i][j] = 1+min3(add,del,sub);
				}
			}
		}
	}
	private int min3(int add, int del, int sub) {
		int min = add;
		if(min>del)
			min = del;
		if(min>sub)
			min = sub;
		return min;
	}
	
	public void getExplanation() {
		Stack<String> s = new Stack<>();
		int i = s1.length();
		int j = s2.length();
		int[] di = {-1,-1,0};
		int[] dj = {-1,0,-1};
		while(d[i][j]>0) {
			int min = Integer.MAX_VALUE;
			int minIndex = -1;
			for (int k = 0; k < dj.length; k++) {
				if(i+di[k]>=0 && j+dj[k]>=0) {
					if(d[i+di[k]][j+dj[k]]<min) {
						min = d[i+di[k]][j+dj[k]];
						minIndex = k;
					}
				}
			}
			if(minIndex==0) {
				if(d[i][j]!=min) {
					s.push(s1.charAt(i-1) + " --> " + s2.charAt(j-1));
					i--;j--;
				}
			} else if(minIndex == 1) {
				i--; s.push(s1.charAt(i) + " deleted");
			} else {
				j--; s.push(s2.charAt(j) + " inserted");
			}
		}
		if(s.isEmpty())
			System.out.println("Strings are identical");
		else {
			while(!s.isEmpty())
				System.out.println(s.pop());
		}
	}
}


















