
// Task Source: https://www.hackerrank.com/challenges/30-review-loop/problem

import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		scanner.nextLine();
		String str = "";
		String odd = "";
		String even = "";
		for (int i = 0; i < n; i++) {
			str = scanner.nextLine();
			odd = "";
			even = "";
			for (int j = 0; j < str.length(); j++) {
				if (j % 2 == 0) {
					even += str.charAt(j);
				} else {
					odd += str.charAt(j);
				}
			}
			System.out.println(even + " " + odd);
		}
	}
}