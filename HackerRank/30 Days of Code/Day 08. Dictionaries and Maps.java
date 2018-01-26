
// Task Source: https://www.hackerrank.com/challenges/30-dictionaries-and-maps/problem

import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

class Solution {
	
	public static void main(String[] argh) {
		Map<String, Integer> map = new HashMap<>();
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		for (int i = 0; i < n; i++) {
			String name = in.next();
			int phone = in.nextInt();
			map.put(name, phone);
		}
		while (in.hasNext()) {
			String s = in.next();
			if (map.containsKey(s)) {
				System.out.format("%s=%d\n", s, map.get(s));
			} else {
				System.out.println("Not found");
			}
		}
		in.close();
	}
}
