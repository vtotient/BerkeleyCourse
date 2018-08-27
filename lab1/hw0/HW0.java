/** Homework assignment 0 */
public class HW0 {

	/** Exercise 1a/1b: Draw Triangle */
	public static void DrawTri(int NumLines) {
		int count = 0;
		int toprint;
		while(count < NumLines) {
			toprint = 0;
			while(toprint <= count) {
				System.out.print("*");
				toprint++;
			}
			System.out.println();
			count++;	
		}
		return;
	}

	/** Exercise 2: Return max of int array
		@preconditions size of array is always greater than or 
		equal to zero */
	public static int max(int[] m) {
		int max = m[0];
		int i = 0;
		int size = m.length;
		while(i < size){
			if(max < m[i]){
				max = m[i];
			}
			i++;
		}
		return max;
	}

	/** Exercise 3: Same as 2 just with for loop */
	public static int maxFL(int[] m){
		int max = m[0];
		for(int i = 0; i < m.length; i++){
			if(max < m[i]){
				max = m[i];
			}
		}
		return max;
	}

	/** Exercise 4: Replaces each element a[i] with the 
		sum of a[i] through a[i + n], but only if a[i] is 
		positive valued. If there are not enough values 
		because we reach the end of the array, we sum only 
		as many values as we have. 
		@preconditions zero is a positive number. */
	public static void windowPosSum(int[] a, int n){
		for(int i = 0; i < a.length; i++){
			if(a[i] < 0){
				continue;
			}
			for(int j = 1; j <= n; j++) {
				if(i + j >= a.length){
					break;
				}
				a[i] = a[i] + a[i+j];
			}
		}
	}

	public static void main(String[] args) {
		DrawTri(5);

		int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6}; 
		System.out.println(max(numbers));
		System.out.println(maxFL(numbers));

		int[] a = {1, 2, -3, 4, 5, 4};
	    int n = 3;
	    windowPosSum(a, n);
	    System.out.println(java.util.Arrays.toString(a));
	}

}