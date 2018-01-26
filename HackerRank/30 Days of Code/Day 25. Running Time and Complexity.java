
// Task Source: https://www.hackerrank.com/challenges/30-running-time-and-complexity/problem

import java.util.Scanner;

public class Solution {
	public static boolean isPrime(int n) {
		if (n <= 1)
			return false;

		double sqrtN = Math.sqrt(n);
		for (int i = 2; i <= sqrtN; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int t = scan.nextInt();
		for (int i = 0; i < t; i++) {
			int n = scan.nextInt();
			System.out.println(isPrime(n) ? "Prime" : "Not prime");
		}
	}
}