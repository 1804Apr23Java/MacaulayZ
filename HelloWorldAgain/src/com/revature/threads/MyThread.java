package com.revature.threads;

public class MyThread {
	@Override
	public void run() {
		System.out.println("Hello from " + this.getName());
	}
}
