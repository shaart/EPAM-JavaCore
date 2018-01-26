
// Task Source: https://www.hackerrank.com/challenges/30-hello-world

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String inputString = scan.nextLine();
		scan.close();

		System.out.println("Hello, World.\n" + inputString);
	}
}