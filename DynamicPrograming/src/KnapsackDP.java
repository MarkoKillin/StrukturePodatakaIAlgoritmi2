import java.util.LinkedList;

public class KnapsackDP {
	private Item[] items;
	private int knapsackWeight;
	private int[][] mp;
	public KnapsackDP(Item[] items, int knapsackWeight) {
		super();
		this.items = items;
		this.knapsackWeight = knapsackWeight;
		this.mp = new int[items.length][knapsackWeight+1];
	}
	public int find() {
		for (int i = 0; i < items.length; i++) {
			mp[i][0] = 0;
		}
		for (int i = 0; i < knapsackWeight+1; i++) {
			if(items[0].getWeight()<=i)
				mp[0][i] = items[0].getProfit();
			else
				mp[0][i] = 0;
		}
		for (int i = 1; i < items.length; i++) {
			for (int j = 1; j < knapsackWeight+1; j++) {
				int profitAdd=0;
				if(items[i].getWeight()<=j) 
					profitAdd = items[i].getProfit() + mp[i-1][j-items[i].getWeight()];
				int profitSkip = mp[i-1][j];
				mp[i][j] = Math.max(profitAdd, profitSkip);
			}
		}
		return mp[items.length-1][knapsackWeight];
	}
	public LinkedList<Item> getAdded(){
		int i = items.length-1;
		int j = knapsackWeight;
		LinkedList<Item> l = new LinkedList<>();
		while(i>0) {
			if(mp[i][j] != mp[i-1][j]) {
				l.addFirst(items[i]);
				j-=items[i].getWeight();
			}
			i--;
		}
		if(j>0 && items[0].getWeight()<=j)
			l.addFirst(items[0]);
		return l;
	}
}










