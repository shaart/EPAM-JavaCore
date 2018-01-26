
// Task Source: https://www.hackerrank.com/challenges/ctci-lonely-integer/problem

import java.util.Scanner;
import java.util.HashSet;

public class Solution {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int res = 0;
		for (int a_i = 0; a_i < n; a_i++) {
			res ^= in.nextInt();
		}
		System.out.println(res);
	}
}
