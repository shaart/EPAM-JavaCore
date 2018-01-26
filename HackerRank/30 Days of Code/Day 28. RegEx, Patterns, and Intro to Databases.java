
// Task Source: https://www.hackerrank.com/challenges/30-regex-patterns/problem

import java.io.*;
import java.util.*;

class Database {
	static Hashtable<String, String> db = new Hashtable<>();

	public void add(String email, String name) {
		db.put(email, name);
	}

	public List<String> getNamesWithPattern(String pattern) {
		ArrayList<String> names = new ArrayList<>();
		db.forEach((k, v) -> {
			if (k.matches(pattern)) {
				names.add(v);
			}
		});

		Collections.sort(names);

		return names;
	}
}

public class Solution {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Database db = new Database();

		int n = input.nextInt();
		for (int i = 0; i < n; i++) {
			String name = input.next();
			String email = input.next();
			db.add(email, name);
		}

		String gmailPattern = "^[\\w|\\W]*@gmail.com";
		List<String> namesWithGmail = db.getNamesWithPattern(gmailPattern);
		for (String name : namesWithGmail) {
			System.out.println(name);
		}
	}
}