
// Task Source: https://www.hackerrank.com/challenges/ctci-balanced-brackets/problem

import java.util.Scanner;
import java.util.Stack;

public class Solution {

	public static boolean isBalanced(String expression) {
		final String OPEN_BR = "([{";
		final String CLOSE_BR = ")]}";

		Stack<Character> stack = new Stack<>();

		for (int i = 0; i < expression.length(); i++) {
			char t = expression.charAt(i);
			if (OPEN_BR.contains(Character.toString(t))) {
				stack.push(t);
			} else if (CLOSE_BR.contains(Character.toString(t))) {
				if (stack.empty()) return false;

				char s = stack.pop();
				if (t == ')' && s != '(' 
						|| t == ']' && s != '[' 
						|| t == '}' && s != '{') {
					return false;
				}
			}
		}
		if (!stack.empty()) return false;

		return true;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		for (int a0 = 0; a0 < t; a0++) {
			String expression = in.next();
			System.out.println((isBalanced(expression)) ? "YES" : "NO");
		}
	}
}
