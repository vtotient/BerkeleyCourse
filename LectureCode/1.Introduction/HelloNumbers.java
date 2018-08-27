public class HelloNumbers {
	public static int larger(int x, int y) {
		if(x > y) {
			return x;
		}
		return y;
	}

	public static void main(String[] args){
		System.out.println(larger(-5,10));
		int x = 0;
		int sum = 0;
		while (x < 10 ) {
			sum = sum + x;
			x++;
			System.out.print(sum + " ");
		}
	}
}