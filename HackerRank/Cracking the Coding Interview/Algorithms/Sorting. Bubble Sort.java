
// Task Source: https://www.hackerrank.com/challenges/ctci-bubble-sort/problem

import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = scan.nextInt();
		}

		int swaps = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n - 1; j++) {
				if (a[j] > a[j + 1]) {
					int t = a[j];
					a[j] = a[j + 1];
					a[j + 1] = t;
					swaps++;
				}
			}
		}

		System.out.println("Array is sorted in " + swaps + " swaps.\nFirst Element: " + a[0] + "\nLast Element: "
				+ a[a.length - 1]);
	}
}