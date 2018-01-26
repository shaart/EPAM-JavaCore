
// Task Source: https://www.hackerrank.com/challenges/ctci-array-left-rotation/problem

import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int k = in.nextInt();
		int a[] = new int[n];
		for (int a_i = 0; a_i < n; a_i++) {
			a[a_i] = in.nextInt();
		}

		rotate(a, k);
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + (i == a.length - 1 ? "" : " "));
		}
	}

	public static void rotate(int[] a, int step) {
		if (step == 0 || step % a.length == 0)
			return;

		if (step < 0) {
			step = a.length + step;
		}
		if (step > a.length) {
			step = step % a.length;
		}

		int[] t = new int[step];
		for (int i = 0; i < a.length; i++) {
			if (i < step) {
				t[i] = a[i];
			}
			if (i < a.length - step) {
				a[i] = a[i + step];
			} else {
				a[i] = t[i - (a.length - step)];
			}
		}
	}
}
