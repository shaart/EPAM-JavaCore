
// Task Source: https://www.hackerrank.com/challenges/30-nested-logic/problem

import java.io.*;
import java.util.*;

public class Solution {
	public static int fine(int actDay, int actMonth, int actYear, int expDay, int expMonth, int expYear) {
		int fine = 0;

		if (actYear > expYear) {
			fine = 10000;
		} else if (actYear == expYear) {
			if (actMonth > expMonth) {
				fine = 500 * (actMonth - expMonth);
			} else if (actMonth == expMonth && actDay > expDay) {
				fine = 15 * (actDay - expDay);
			}
		}

		return fine;
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int actDay = input.nextInt();
		int actMonth = input.nextInt();
		int actYear = input.nextInt();
		int expDay = input.nextInt();
		int expMonth = input.nextInt();
		int expYear = input.nextInt();

		System.out.println(fine(actDay, actMonth, actYear, expDay, expMonth, expYear));
	}
}