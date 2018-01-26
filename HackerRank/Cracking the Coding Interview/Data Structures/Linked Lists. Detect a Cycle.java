
// Task Source: https://www.hackerrank.com/challenges/ctci-linked-list-cycle/problem

/*
Detect a cycle in a linked list. Note that the head pointer may be 'null' if the list is empty.

A Node is defined as: 
    class Node {
        int data;
        Node next;
    }
*/

boolean hasCycle(Node head) {
    HashSet<Node> set = new HashSet<Node>();
    
    while(head != null) {
        if (set.contains(head)) {
            return true;
        } 
        
        set.add(head);
        head = head.next;
    }
    
    return false;
}
