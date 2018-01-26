
// Task Source: https://www.hackerrank.com/challenges/30-binary-trees/problem

import java.util.*;
import java.io.*;

class Node {
	Node left, right;
	int data;

	Node(int data) {
		this.data = data;
		left = right = null;
	}
}

class Solution23 {
	static void levelOrder(Node root) {
		if (root == null)
			return;

		Queue<Node> path = new LinkedList<Node>();
		LinkedList<Node> visited = new LinkedList<Node>();
		path.add(root);
		while (!path.isEmpty()) {
			Node curr = path.remove();
			if (!visited.contains(curr)) {
				System.out.print(curr.data + " ");

				if (curr.left != null)
					path.add(curr.left);
				if (curr.right != null && curr.right != curr.left)
					path.add(curr.right);

				visited.add(curr);
			}
		}
	}

	public static Node insert(Node root, int data) {
		if (root == null) {
			return new Node(data);
		} else {
			Node cur;
			if (data <= root.data) {
				cur = insert(root.left, data);
				root.left = cur;
			} else {
				cur = insert(root.right, data);
				root.right = cur;
			}
			return root;
		}
	}

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		Node root = null;
		while (T-- > 0) {
			int data = sc.nextInt();
			root = insert(root, data);
		}
		levelOrder(root);
	}
}