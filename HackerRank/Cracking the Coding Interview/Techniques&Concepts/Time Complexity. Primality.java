
// Task Source: https://www.hackerrank.com/challenges/ctci-big-o/problem

import java.util.Scanner;

public class Solution {
	
    public static boolean isPrime(int n) {
        if (n == 2) return true;
        if (n % 2 == 0 || n == 1) return false;
        
        for (int i = 3; i*i <= n; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int p = in.nextInt();
        for(int a0 = 0; a0 < p; a0++){
            int n = in.nextInt();
            System.out.println(isPrime(n) ? "Prime" : "Not prime");
        }
    }
}
