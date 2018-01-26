
// Task Source: https://www.hackerrank.com/challenges/ctci-bfs-shortest-reach/problem

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.Scanner;

class Graph {
	private HashMap<Integer, Node> nodeLookup = new HashMap<Integer, Node>();
	public static final int EDGE_LENGTH = 6;
	private int numOfNodes = 0;

	Graph(int nodesCount) {
		numOfNodes = nodesCount;
	}

	private class Node {
		private int id;
		LinkedList<Node> adjacent = new LinkedList<Node>();

		Node(int id) {
			this.id = id;
		}
	}

	private Node getNode(int id) {
		if (nodeLookup.containsKey(id))
			return nodeLookup.get(id);

		Node n = new Node(id);
		nodeLookup.put(id, n);
		return n;
	}

	public void addEdge(int source, int destination) {
		Node s = getNode(source);
		Node d = getNode(destination);
		s.adjacent.add(d);
		d.adjacent.add(s);
	}

	public int[] distancesFromBFS(Node source) {
		int[] dist = new int[numOfNodes];
		Arrays.fill(dist, -1);
		dist[source.id - 1] = 0; // all id's starts from '1' and ends by 'n' inclusive

		LinkedList<Node> nextToVisit = new LinkedList<>();
		HashSet<Integer> visited = new HashSet<>();

		nextToVisit.add(source);
		while (!nextToVisit.isEmpty()) {
			Node node = nextToVisit.remove();
			for (Node child : node.adjacent) {
				if (!visited.contains(child.id)) {
					if (dist[child.id - 1] == -1) {
						dist[child.id - 1] = dist[node.id - 1] + Graph.EDGE_LENGTH;
					}
					nextToVisit.add(child);
					visited.add(child.id);
				}
			}
		}
		return dist;
	}

	public String shortestDistances(int s) {
		return shortestDistances(getNode(s));
	}

	private String shortestDistances(Node s) {
		StringBuilder sb = new StringBuilder();

		int[] dist = distancesFromBFS(s);

		for (int i = 0; i < dist.length; i++) {
			if (dist[i] == 0)
				continue;
			sb.append(dist[i] + " ");
		}

		return sb.toString().trim();
	}
}

public class Solution {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int q = scan.nextInt();
		for (int qi = 0; qi < q; qi++) {
			int n = scan.nextInt();
			Graph g = new Graph(n);
			int m = scan.nextInt();
			for (int mi = 0; mi < m; mi++) {
				int u = scan.nextInt();
				int v = scan.nextInt();
				g.addEdge(u, v);
			}
			int s = scan.nextInt();
			String distances = g.shortestDistances(s);
			System.out.println(distances);
		}

	}
}