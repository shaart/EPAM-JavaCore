
// Task Source: https://www.hackerrank.com/challenges/ctci-ice-cream-parlor/problem

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Solution {   
	
    static String solve(int[] arr, int money) {
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int i = 1; i <= arr.length; i++) {
            int addition = money - arr[i-1];
            if (map.containsKey(addition)) {
                return String.format("%d %d", map.get(addition), i);
            } else {
                map.put(arr[i-1], i);
            }
        }
        return "not found";
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            int money = in.nextInt();
            int n = in.nextInt();
            int[] arr = new int[n];
            for(int arr_i = 0; arr_i < n; arr_i++){
                arr[arr_i] = in.nextInt();
            }
            String s = solve(arr, money);
            System.out.println(s);
        }
        in.close();
    }
}
