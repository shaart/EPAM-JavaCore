
// Task Source: https://www.hackerrank.com/challenges/ctci-making-anagrams/problem

import java.util.Scanner;

public class Solution {
	public static int numberNeeded(String first, String second) {
		final int ALPHABET_LENGTH = 26;
		int[] chars = new int[ALPHABET_LENGTH];
		for (int i = 0; i < first.length(); i++) {
			chars[first.charAt(i) - 'a']++;
		}
		for (int i = 0; i < second.length(); i++) {
			chars[second.charAt(i) - 'a']--;
		}
		int diff = 0;
		for (int n : chars) {
			diff += Math.abs(n);
		}

		return diff;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String a = in.next();
		String b = in.next();
		System.out.println(numberNeeded(a, b));
	}
}
