
public class InsertionSort {
	public static <T extends Comparable<T>> void sortv1(T[] arr) {
		for (int i = 1; i < arr.length; i++) {
			if(arr[i-1].compareTo(arr[i]) > 0) {
				T current = arr[i];
				int j = i-1;
				while(j>=0 && arr[j].compareTo(current) > 0) {
					arr[j+1] = arr[j];
					j--;
				}
				arr[j+1] = current;
			}
		}
	}
	
	public static <T extends Comparable<T>> void sortv2(T[] arr) {
		for (int i = 1; i < arr.length; i++) {
			T current = arr[i];
			int j = i-1;
			while(j>=0 && arr[j].compareTo(current) > 0) {
				arr[j+1] = arr[j];
				j--;
			}
			arr[j+1] = current;
		}
	}
	
	public static <T extends Comparable<T>> void sortv3(T[] arr) {
		int min = 0;
		for (int i = 1; i < arr.length; i++) {
			if(arr[min].compareTo(arr[i]) > 0)
				min = i;
		}
		if(min!=0) {
			T tmp = arr[min];
			arr[min] = arr[0];
			arr[0] = tmp;
		}
		
		for (int i = 2; i < arr.length; i++) {
			T current = arr[i];
			int j = i-1;
			while(arr[j].compareTo(current) > 0) {
				arr[j+1] = arr[j];
				j--;
			}
			arr[j+1] = current;
		}
	}
}
