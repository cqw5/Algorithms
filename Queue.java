package com.qiwei.algs;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 用链表实现的先进先出的队列
 * @author cqw
 */
public class Queue<Item> implements Iterable<Item> {
	private static class Node<Item> { //定义结点的嵌套类
		private Item item;
		private Node<Item> next;
	}
	private Node<Item> first;	//队首指针
	private Node<Item> last;	//队尾指针
	private int N;	//队列中元素的个数
	
	public Queue() {
		first = null;
		last = null;
		N = 0;
	}
	
	/**
	 * 判断队列是否为空
	 * @return 若队列为空，返回True
	 */
	public boolean isEmpty() {
		return first == null;
	}
	
	/**
	 * 返回队列的大小
	 * @return 队列的大小
	 */
	public int size() {
		return N;
	}
	
	/**
	 * 获取队列头的元素
	 * @return 队头元素
	 */
	public Item peek() {
		if (isEmpty()) {
			throw new NoSuchElementException("Queue underflow");
		}
		return first.item;
	}
	
	/**
	 * 从队尾进队列
	 * @param item 进队列的元素
	 */
	public void enqueue(Item item) {
		Node<Item> oldlast = last;
		last = new Node<Item>();
		last.item = item;
		if (isEmpty()) { //如果之前队列为空
			first = last;
		} else {
			oldlast.next = last;
		}
		N++;
	}
	
	/**
	 * 从队列头出队列
	 * @return 出队列的元素
	 */
	public Item dequeue() {
		if (isEmpty()) {
			throw new NoSuchElementException("Queue underflow");
		}
		Item item = first.item;
		first = first.next;
		N--;
		if (isEmpty()) {
			last = null;
		}
		return item;
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

		@Override
		public boolean hasNext() {
			return current != null;
		}

		/**
		 * 从头到尾进行迭代
		 */
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
		Queue<String> q = new Queue<String>();
		String[] arr = {"Hello", "World"};
		q.enqueue(arr[0]);
		q.enqueue(arr[1]);
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
	}
}
