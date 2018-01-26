
// Task Source: https://www.hackerrank.com/challenges/30-testing/problem

import java.io.*;
import java.util.*;

class TestCase {
	final int n;
	final int k;
	final int[] a;

	TestCase(int n, int k, int... a) {
		this.n = n;
		this.k = k;
		if (a.length == n) {
			this.a = a;
		} else {
			int smaller = n < a.length ? n : a.length;
			this.a = new int[smaller];
			System.arraycopy(a, 0, this.a, 0, smaller);
		}
	}

	public void print() {
		System.out.format("%d %d\n", n, k);
		for (int i = 0; i < n; i++) {
			System.out.print(a[i] + (i == n - 1 ? "" : " "));
		}
		System.out.println();
	}

	public String result() {
		int intime = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[i] <= 0)
				intime++;
		}
		return intime >= k ? "NO" : "YES";
	}
}

public class Solution {

	public static void main(String[] args) {
		final int t = 5;

		System.out.println(t);
		new TestCase(5, 3, -1, 90, 999, 100, 0).print();
		new TestCase(4, 2, 0, -1, 2, 1).print();
		new TestCase(3, 3, -1, 0, 1).print();
		new TestCase(6, 1, -1, 0, 1, -1, 2, 3).print();
		new TestCase(7, 3, -1, 0, 1, 2, 3, 4, 5).print();
	}
}