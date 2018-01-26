
// Task Source: https://www.hackerrank.com/challenges/30-2d-arrays/problem

import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		final int M = 6;
		final int N = 6;

		Scanner in = new Scanner(System.in);
		int arr[][] = new int[M][N];
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				arr[i][j] = in.nextInt();
			}
		}

		int max = maxSum(arr);
		System.out.println(max);

	}

	public static int maxSum(int[][] a) {
		int max = Integer.MIN_VALUE;
		int curr;
		for (int i = 0; i < a.length - 2; i++) {
			for (int j = 0; j < a[i].length - 2; j++) {
				curr = 	  a[i][j]		+ a[i][j + 1]		+ a[i][j + 2] 
										+ a[i + 1][j + 1] 
						+ a[i + 2][j] 	+ a[i + 2][j + 1]	+ a[i + 2][j + 2];

				if (curr > max) {
					max = curr;
				}
			}

		}

		return max;
	}
}
