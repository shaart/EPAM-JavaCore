
// Task Source: https://www.hackerrank.com/challenges/ctci-contacts/problem

import java.util.Scanner;
import java.util.HashMap;

class Trie {
	TrieNode root = new TrieNode();

	public void add(String s) {
		TrieNode curr = root;
		for (int i = 0; i < s.length(); i++) {
			Character c = s.charAt(i);
			curr.add(c);
			curr = curr.getChild(c);
			curr.size++;
		}
	}

	public int count(String part) {
		TrieNode curr = root;

		for (int i = 0; i < part.length(); i++) {
			Character c = part.charAt(i);
			curr = curr.getChild(c);
			if (curr == null) return 0;
		}
		return curr.size;
	}
}

class TrieNode {
	private HashMap<Character, TrieNode> children = new HashMap<>();
	public int size;

	public void add(char ch) {
		children.putIfAbsent(ch, new TrieNode());
	}

	public TrieNode getChild(char ch) {
		return children.get(ch);
	}

}

public class Solution {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		Trie trie = new Trie();
		for (int a0 = 0; a0 < n; a0++) {
			String op = in.next();
			String contact = in.next();

			if (op.equals("add")) {
				trie.add(contact);
			} else if (op.equals("find")) {
				System.out.println(trie.count(contact));
			}
		}
	}
}
