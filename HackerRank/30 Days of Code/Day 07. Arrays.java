
// Task Source: https://www.hackerrank.com/challenges/30-arrays/problem

import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = in.nextInt();
		}
		in.close();

		printArrayReversed(arr);
	}

	public static void printArrayReversed(int[] arr) {
		for (int i = arr.length - 1; i >= 0; i--) {
			System.out.print(arr[i] + (i == 0 ? "" : " "));
		}
	}
}
