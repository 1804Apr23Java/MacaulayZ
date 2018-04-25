package com.buildingexercise.revature;

import java.util.ArrayList;

public class Level implements Structure{
	private String name;
	private int area;
	private ArrayList<Room> roomList;
	
	public Level(String name, int area) {
		this.name = name;
		this.area = area;
		roomList = new ArrayList<>();
		
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public int getArea() {
		// TODO Auto-generated method stub
		return area;
	}
	
	public void add(Room r) {
		roomList.add(r);
	}
	
	public int getCost( ) {
		int sum = 0;
		for(Room r : this.roomList) {
			sum += r.getCost();
	}
		return sum;
	}
}
