
// Task Source: https://www.hackerrank.com/challenges/30-sorting/problem

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] a = new int[n];
		for (int a_i = 0; a_i < n; a_i++) {
			a[a_i] = in.nextInt();
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
			if (swaps == 0)
				break;
		}
		System.out.format("Array is sorted in %d swaps.\nFirst Element: %d\nLast Element: %d", swaps, a[0],
				a[a.length - 1]);
	}
}
