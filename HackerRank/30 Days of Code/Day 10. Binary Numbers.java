
// Task Source: https://www.hackerrank.com/challenges/30-binary-numbers/problem

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Solution {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int m = maxSequence(Integer.toBinaryString(n));
		System.out.println(m);
	}

	public static int maxSequence(String bin) {
		int max = 0;
		int curr = 0;
		for (int i = 0; i < bin.length(); i++) {
			if (bin.charAt(i) == '1') {
				curr++;
			} else {
				if (curr > max) {
					max = curr;
				}
				curr = 0;
			}
		}
		if (curr > max) {
			max = curr;
		}

		return max;
	}
}