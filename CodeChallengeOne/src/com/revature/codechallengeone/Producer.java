package com.revature.codechallengeone;

import java.util.List;

public class Producer implements Runnable{
	
	List<Integer> basketList;
	int limit = 3;
	
	public Producer(List<Integer> basketList) {
		this.basketList = basketList;
	}
	
	@Override
	public void run() {
		try {
			production();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void production() throws InterruptedException {
		int someVal = 0;
		while (true) {
			synchronized(this) {
				//Wait while basketList is full
				while(basketList.size() == limit)
					wait();
					
				System.out.println("Producer added: " + someVal);
				//Add a value to basketList
				basketList.add(someVal++);
				
				//Wake up the Consumer thread and put the Producer thread to sleep
				notify();
				Thread.sleep(1000);
			}
		}
	}
}
