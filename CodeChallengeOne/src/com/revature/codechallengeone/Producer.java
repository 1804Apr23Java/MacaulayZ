package com.revature.codechallengeone;

import java.util.LinkedList;

public class Producer {
	LinkedList<Integer> list = new LinkedList<>();
	int limit = 3;
	
	public void produce() throws InterruptedException {
		int someVal = 0;
		while (true) {
			synchronized(this) {
				while(list.size() == limit) {
					wait();
					System.out.println("Producer added: " + someVal);
					list.add(someVal++);
					notify();
				}
			}
		}
	}
}
