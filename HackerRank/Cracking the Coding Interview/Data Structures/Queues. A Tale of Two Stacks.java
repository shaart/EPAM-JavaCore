
// Task Source: https://www.hackerrank.com/challenges/ctci-queue-using-two-stacks/problem

import java.util.Scanner;
import java.util.Stack;

public class Solution {
	public static void main(String[] args) {

		class MyQueue<T> {
			Stack<T> stack = new Stack<T>();
			Stack<T> rev = new Stack<T>();

			public void enqueue(T val) {
				stack.add(val);
			}

			private void updateRev() {
				if (rev.empty()) {
					while (!stack.empty()) {
						rev.add(stack.pop());
					}
				}
			}

			public T dequeue() {
				updateRev();
				if (rev.empty()) return null;

				return rev.pop();
			}

			public T peek() {
				updateRev();
				if (rev.empty()) return null;

				return rev.peek();
			}
		}

		MyQueue<Integer> queue = new MyQueue<Integer>();

		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();

		for (int i = 0; i < n; i++) {
			int operation = scan.nextInt();
			if (operation == 1) { // enqueue
				queue.enqueue(scan.nextInt());
			} else if (operation == 2) { // dequeue
				queue.dequeue();
			} else if (operation == 3) { // print/peek
				System.out.println(queue.peek());
			}
		}
		scan.close();
	}
}
