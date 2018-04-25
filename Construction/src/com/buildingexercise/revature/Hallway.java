package com.buildingexercise.revature;

public class Hallway extends Room {
	private int numDoors;
	
	public Hallway(int numDoors, int area, String name){
		super(area, name);
		this.numDoors = numDoors;
	}

	public int getNumDoors() {
		return numDoors;
	}

	
	
}
