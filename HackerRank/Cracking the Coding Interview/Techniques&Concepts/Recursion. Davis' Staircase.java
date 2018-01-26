
// Task Source: https://www.hackerrank.com/challenges/ctci-recursive-staircase/problem

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Solution {
	static Map<Integer, Integer> cache = new HashMap<>();

	public static int getValue(int key) {
		int value;
		if (cache.containsKey(key)) {
			value = cache.get(key);
		} else {
			value = numberOfWays(key);
			cache.put(key, value);
		}
		return value;
	}

	public static int numberOfWays(int n) {
		if (n < 0) return 0;
		if (n <= 1) return 1;

		int n1 = getValue(n - 1);
		int n2 = getValue(n - 2);
		int n3 = getValue(n - 3);

		return n1 + n2 + n3;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int s = in.nextInt();
		for (int a0 = 0; a0 < s; a0++) {
			int n = in.nextInt();
			System.out.println(numberOfWays(n));
		}
	}
}
