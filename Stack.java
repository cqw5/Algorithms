package com.qiwei.algs;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 用链表实现的先进后出的栈
 * @author cqw
 */
public class Stack<Item> implements Iterable<Item> {
	private static class Node<Item> { //定义结点的嵌套类
		private Item item;
		private Node<Item> next;
	}
	private Node<Item> first;	//栈顶指针
	private int N;		//元素的个数
	
	public Stack() {
		first = null;
		N = 0;
	}
	
	/**
	 * 判断栈是否为空
	 * @return 若栈为空，返回True
	 */
	public boolean isEmpty() {
		return first == null;
	}
	
	/**
	 * 返回栈的大小
	 * @return 栈的大小
	 */
	public int size() {
		return N;
	}
	
	/**
	 * 进栈
	 * @param item 进栈的元素
	 */
	public void push(Item item) {
		Node<Item> oldfirst = first;
		first = new Node<Item>();
		first.item = item;
		first.next = oldfirst;
		N++;
	}
	
	/**
	 * 出栈
	 * @return 返回栈顶元素
	 */
	public Item pop() {
		if (isEmpty()) {
			throw new NoSuchElementException("Stack underflow");
		}
		Item item = first.item;
		first = first.next;
		N--;
		return item;
	}
	
	/**
	 * 获取栈顶元素
	 * @return 返回栈顶元素
	 */
	public Item peek() {
		if (isEmpty()) {
			throw new NoSuchElementException("Stack underflow");
		}
		return first.item;
	}
	
	/**
	 * 迭代器
	 */
	@Override
	public Iterator<Item> iterator() {
		return new ListIterator<Item>(first);
	}

	private class ListIterator<Item> implements Iterator<Item> {
		private Node<Item> current;
		
		public ListIterator(Node<Item> first) {
			current = first;
		}

		/**
		 * 从头到尾进行迭代
		 */
		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public Item next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			Item item = current.item;
			current = current.next;
			return item;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	
	public static void main(String[] args) {
		Stack<String> s = new Stack<String>();
		String[] arr = {"Hello", "World"};
		s.push(arr[0]);
		s.push(arr[1]);
		System.out.println(s.pop());
		System.out.println(s.pop());
	}
}
