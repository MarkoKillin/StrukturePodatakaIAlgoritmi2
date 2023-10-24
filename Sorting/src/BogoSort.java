
public class BogoSort {
	public static <T extends Comparable<T>> void sort(T[] arr) {
		while(!sorted(arr))
			shuffle(arr);
	}

	private static <T extends Comparable<T>> void shuffle(T[] arr) {
		for (int i = arr.length-1; i > 0; i--) {
			int r = (int)(Math.random()*i);
			T tmp = arr[r];
			arr[r] = arr[i];
			arr[i] = tmp;
		}
	}

	private static <T extends Comparable<T>> boolean sorted(T[] arr) {
		for (int i = 0; i < arr.length-1; i++) {
			if(arr[i].compareTo(arr[i+1]) > 0)
				return false;
		}
		return true;
	}
	
}
