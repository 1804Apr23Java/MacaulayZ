package com.revature.codechallengeone;

import java.util.LinkedList;

public class Consumer implements Runnable {

	LinkedList<Integer> basketList;

	public Consumer(LinkedList<Integer> basketList) {
		this.basketList = basketList;
	}

	@Override
	public void run() {
		try {
			consumption();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void consumption() throws InterruptedException {
		while (true) {
			synchronized (this) {
				//Wait while basketList is empty
				while (basketList.size() == 0)
					wait();
				
				//Remove item from basketList
				int removedItem = basketList.removeFirst();

				System.out.println("Consumer removed: " + removedItem);

				//Wake up the Producer thread and put the Consumer thread to sleep
				notify();
				Thread.sleep(10);
			}
		}
	}
}
