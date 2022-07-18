package io.sarc.bomboot.util;

import io.sarc.sarcutil.ListStack.Node;

public class ListStack {
	private Node top;
	private int size = 0;

	private class Node {
		private Object data;
		private Node nextNode;

		Node(Object data) {
			this.data = data;
			this.nextNode = null;
		}
	}

	public ListStack() {
		this.top = null;
	}

	public boolean empty() {
		return (top == null);
	}

	public void push(Object item) {
		Node newNode = new Node(item);
		newNode.nextNode = top;
		top = newNode;
		size++;
	}

	public Object peek() {
		if (empty())
			throw new ArrayIndexOutOfBoundsException();
		return top.data;
	}
	
	public String peekAll() {
		StringBuffer sb = new StringBuffer();
		
		Node peekNode = top;
		
		while ( peekNode.data != null ) {
			sb.append(peekNode.data);
			sb.append("|");
			peekNode.data = peekNode.nextNode;
		}
		return sb.toString();
	}
	
	public Object pop() {
		Object item = peek();
		top = top.nextNode;
		size--;
		return item;
	}
	
	public int size() {
		return size;
	}
}