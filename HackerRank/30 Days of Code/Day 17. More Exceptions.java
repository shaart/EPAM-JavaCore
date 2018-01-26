
// Task Source: https://www.hackerrank.com/challenges/30-more-exceptions/problem

import java.util.*;
import java.io.*;

class Calculator {
	int power(int n, int p) {
		if (n < 0 || p < 0) throw new IllegalArgumentException("n and p should be non-negative");
		if (n == 0 || n == 1) return n;
		if (p == 0) return 1;

		return n * power(n, p - 1);
	}
}

class Solution {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		while (t-- > 0) {

			int n = in.nextInt();
			int p = in.nextInt();
			Calculator myCalculator = new Calculator();
			try {
				int ans = myCalculator.power(n, p);
				System.out.println(ans);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		in.close();
	}
}
