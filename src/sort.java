import java.util.ArrayList;
import java.util.Collections;

/*
 * (c) Japan Financial Engineering, Ltd
 */

public class sort {
	public static void main(String[] args) {
		ArrayList<Integer> aaa = new ArrayList<Integer>();
		aaa.add(3);
		aaa.add(5);
		aaa.add(10);
		aaa.add(20);
		aaa.add(25);
		aaa.add(30);
		Collections.sort(aaa);

		int a = 0;
		int b = aaa.size();
		int target = 20;

		while (a < b) {
			int m = (a + b) / 2;
			if (aaa.get(m) == target) {
				System.out.println(m + "番目にありました");
				break;
			} else if (aaa.get(m) > target) {
				a = m + 1;
			} else if (aaa.get(m) < target) {
				b = m;
			}
		}
	}
}
