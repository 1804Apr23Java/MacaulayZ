package com.revature.codechallengeone;

import java.util.LinkedList;

public class PCTest {

	public static void main(String[] args) throws InterruptedException {
		LinkedList<Integer> basketList = new LinkedList<>();
		
		//Create and start the Producer thread
		Thread t1 = new Thread(new Producer(basketList));
		t1.start();
		
		//Create and start the Consumer thread
		Thread t2 = new Thread(new Consumer(basketList));
		t2.start();
		
		//Ensure that Producer finishes before Consumer
		t1.join();
		t2.join();
	}

}
