
// Task Source: https://www.hackerrank.com/challenges/30-bitwise-and/problem

import java.io.*;
import java.util.*;

public class Solution {
	public static int maxAB(int n, int k) {
		int maxAB = 0;
		for (int i = n; i > 0; i--) {
			for (int j = n - 1; j > 0; j--) {
				int curAB = i & j;
				if (i != j && curAB > maxAB && curAB < k) {
					maxAB = i & j;
				}
			}
		}
		return maxAB;
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int t = input.nextInt();
		for (int i = 0; i < t; i++) {
			int n = input.nextInt();
			int k = input.nextInt();
			System.out.println(maxAB(n, k));
		}
	}
}