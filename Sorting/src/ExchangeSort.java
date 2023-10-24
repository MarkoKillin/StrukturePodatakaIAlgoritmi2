
public class ExchangeSort {
	public static <T extends Comparable<T>> void sort(T[] arr) {
		for (int i = arr.length-1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if(arr[j].compareTo(arr[j+1]) > 0) {
					T tmp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = tmp;
				}
			}
		}
	}
	
	public static <T extends Comparable<T>> void sortv1(T[] arr) {
		for (int i = 0; i < arr.length-1; i++) {
			for (int j = arr.length-1; j > i; j--) {
				if(arr[j-1].compareTo(arr[j]) > 0) {
					T tmp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = tmp;
				}
			}
		}
	}
	
	public static <T extends Comparable<T>> void sortv2(T[] arr) {
		for (int i = arr.length-1; i > 0; i--) {
			boolean ex = false;
			for (int j = 0; j < i; j++) {
				if(arr[j].compareTo(arr[j+1]) > 0) {
					T tmp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = tmp;
					ex = true;
				}
			}
			if(!ex)
				break;
		}
	}
	
}
