
// Task Source: https://www.hackerrank.com/challenges/ctci-find-the-running-median/problem

import java.util.Scanner;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Comparator;

public class Solution {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		Queue<Integer> left = new PriorityQueue<>(n, Comparator.reverseOrder());
		Queue<Integer> right = new PriorityQueue<>(n);
		for (int a_i = 0; a_i < n; a_i++) {
			int t = in.nextInt();
			Queue<Integer> target = left.size() <= right.size() ? left : right;
			target.add(t);
			balance(left, right);
			System.out.println(median(left, right));
		}
	}

	public static void balance(Queue<Integer> left, Queue<Integer> right) {
		while (!left.isEmpty() && !right.isEmpty() && left.peek() > right.peek()) {
			Integer l = left.poll();
			Integer r = right.poll();
			left.add(r);
			right.add(l);
		}
	}

	public static double median(Queue<Integer> left, Queue<Integer> right) {
		if (left.isEmpty() && right.isEmpty())
			return 0.0;

		return left.size() == right.size() ? 
				(left.peek() + right.peek()) / 2.0 
				: left.peek();
	}
}
