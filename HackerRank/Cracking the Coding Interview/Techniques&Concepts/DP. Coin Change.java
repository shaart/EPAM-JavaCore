
// Task Source: https://www.hackerrank.com/challenges/ctci-coin-change/problem

import java.util.HashMap;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;

public class Solution {
	
	public static void print(Integer[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}

	private static boolean isSorted(Integer[] coins) {
		for (int i = 1; i < coins.length; i++) {
			if (coins[i] < coins[i - 1])
				return false;
		}
		return true;
	}

	public static long exchangesNumber(Integer[] coins, int sum) {
		if (!isSorted(coins)) {
			// Save order of original array, but sort this one
			Integer[] t = new Integer[coins.length];
			System.arraycopy(coins, 0, t, 0, coins.length);
			coins = t;
			t = null;
			Arrays.sort(coins);
		}

		long[] changes = new long[sum + 1]; // 1 space for 0
		changes[0] = 1;

		for (int coin : coins) {
			for (int i = coin; i < changes.length; i++) {
				// Update 'change' all sums, where we can use this coin
				// changes[i] - nums of changes of sum without coin
				// changes[i - coin] - is num of changes of sum using this coin
				changes[i] += changes[i - coin];

				/*
                    coins = 1, 2, 3
                    sum = 4 <-- length = 4+1 = 5
                    
                    coin = 1
                    i = 1
                    ch[1] += ch[1 - 1] -> ch[0] is 1 -> 0 + 1 = 1
                    i = 2
                    ch[2] += ch[2 - 1] -> ch[1] is 1 -> 0 + 1 = 1
                    i = 3
                    ch[3] += ch[3 - 1] -> ch[2] is 1 -> 0 + 1 = 1
                    i = 4
                    ch[4] += ch[4 - 1] -> ch[3] is 1 -> 0 + 1 = 1
                    
                    coin = 2
                    i = 2
                    ch[2] += ch[2 - 2] -> ch[0] is 1 -> 1 + 1 = 2
                    i = 3
                    ch[3] += ch[3 - 2] -> ch[1] is 1 -> 1 + 1 = 2
                    i = 4
                    ch[4] += ch[4 - 2] -> ch[2] is 2 -> 1 + 2 = 3
                    
                    coin = 3
                    i = 3
                    ch[3] += ch[3 - 3] -> ch[0] is 2 -> 2 + 2 = 4
                    i = 4
                    ch[4] += ch[4 - 3] -> ch[1] is 1 -> 3 + 1 = 4;  
				 */
			}
		}

		return changes[sum];
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		Integer coins[] = new Integer[m];
		for (int coins_i = 0; coins_i < m; coins_i++) {
			coins[coins_i] = in.nextInt();
		}
		in.close();

		System.out.println(exchangesNumber(coins, n));
	}
}
