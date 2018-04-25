package com.buildingexercise.revature;

public abstract class Room implements Structure {
	private String name;
	private int area;
	protected int cost;
	
	public Room(int area) {
		this.area = area;
		this.cost = area*10;
	}
	
	public Room(int area, String name) {
		this.area = area;
		this.cost = area*10;
		this.name = name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}


	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public int getCost() {
		return cost;
	}

	
}