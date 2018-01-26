
// Task Source: https://www.hackerrank.com/challenges/ctci-merge-sort/problem

import java.util.Scanner;

public class Solution {

    static long countInversions(int[] arr) {           
        return countInversions(arr, new int[arr.length], 0, arr.length - 1);
    }
    
    static long countInversions(int[] arr, int[] temp, int start, int end) {
        if (start >= end) return 0;
        
        int mid = start + (end - start)/2;
        
        long inv = 0;
        inv += countInversions(arr, temp, start, mid);
        inv += countInversions(arr, temp, mid+1, end);
        inv += merge(arr, temp, start, mid, end);        
        
        return inv;
    }
    
    static long merge(int[] arr, int[] temp, int start, int mid, int end) {
        for (int i = start; i <= end; i++) {
            temp[i] = arr[i];
        }
        
        long inv = 0;
        
        int i = start;
        int j = mid+1;
        int k = start;
        
        while (i <= mid && j <= end) {
            if (temp[i] <= temp[j]) {
                arr[k] = temp[i];
                i++;                
            } else {
                arr[k] = temp[j];
                j++;
                /*
                For example:  1[start] 3 5 6[mid] | 2[mid+1] 4 7[end]
                1 -> not needed inversions
                2 -> need inversions for 3 elements: 3, 5 and 6 -> mid+1 - i
                */
                inv += mid - i + 1;
            }
            k++;
        }
        while(i <= mid) {
            arr[k] = temp[i];
            k++;
            i++;
        }  
        while(j <= end) {
            arr[k] = temp[j];
            k++;
            j++;
        }              
        
        return inv;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            int n = in.nextInt();
            int[] arr = new int[n];
            for(int arr_i = 0; arr_i < n; arr_i++){
                arr[arr_i] = in.nextInt();
            }
            long result = countInversions(arr);
            System.out.println(result);
        }
        in.close();
    }
}
