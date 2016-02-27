package com.qiwei.algs;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 用链表实现的背包（不支持删除，迭代顺序不确定）
 * @author cqw
 */
public class Bag<Item> implements Iterable<Item> {
	private static class Node<Item> { //定义结点的嵌套类
		private Item item;
		private Node<Item> next;
	}
	private Node<Item> first; //表头指针
	private int N; //背包中元素的个数
	
	public Bag() {
		first = null;
		N = 0;
	}
	
	/**
	 * 判断背包是否为空
	 * @return 若背包为空，返回True
	 */
	public boolean isEmpty() {
		return first == null;
	}
	
	/**
	 * 返回背包的大小
	 * @return 背包的大小
	 */
	public int size() {
		return N;
	}
	
	/**
	 * 向背包中添加元素
	 * @param item 添加的元素
	 */
	public void add(Item item) {
		Node<Item> oldfirst = first;
		first = new Node<Item>();
		first.item = item;
		first.next = oldfirst;
		N++;
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

		@Override
		public Item next() {
			if (!hasNext()){
				throw new NoSuchElementException();
			}
			Item item = current.item;
			current = current.next;
			return item;
		}

		@Override
		public void remove() {
			throw new NoSuchElementException();
		}
	}
	
	public static void main(String[] args) {
		Bag<String> b = new Bag<String>();
		String[] arr = {"Hello", "World"};
		b.add(arr[0]);
		b.add(arr[1]);
		for (String s : b){
			System.out.println(s);
		}
	}

}
