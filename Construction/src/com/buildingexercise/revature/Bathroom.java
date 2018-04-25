package com.buildingexercise.revature;

public class Bathroom extends Room {
	private int numStalls;
	public Bathroom(int numStalls, int area, String name) {
		super(area, name);
		this.numStalls = numStalls;
	}

	public int getNumStalls() {
		return numStalls;
	}

	public int getCost() {
		return cost+(numStalls*10);
	}
	
	
}
