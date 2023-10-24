
public class CombSort {
	private static int nextGap(int k) {
		k/=1.3;
		if(k==10 || k==9) k=11;
		if(k<1) k=1;
		return k;
	}
	
	public static <T extends Comparable<T>> void sort(T[] arr) {
		int k = arr.length;
		boolean sorted = false;
		do {
			k=nextGap(k);
			boolean exchange = false;
			for (int i = 0; i < arr.length-k; i++) {
				if(arr[i].compareTo(arr[i+k]) > 0) {
					T tmp = arr[i+k];
					arr[i+k] = arr[i];
					arr[i] = tmp;
					exchange = true;
				}
			}
			sorted = k==1 && !exchange;
		} while (!sorted);
	}
}
