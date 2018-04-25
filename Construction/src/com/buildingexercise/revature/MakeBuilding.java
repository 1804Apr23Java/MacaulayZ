package com.buildingexercise.revature;

public class MakeBuilding {
	public static void main(String[] args) {
		Level floor1 = new Level("Floor 1", 500);
		Hallway hall1 = new Hallway(4, 100, "Hall 1");
		floor1.add(hall1);
		System.out.println(floor1.getCost());
		Bathroom bath1 = new Bathroom(5, 250, "Bath 1");
		floor1.add(bath1);
		System.out.println(floor1.getCost());
		Office office1 = new Office(250, "Office 1");
		floor1.add(office1);
		System.out.println(floor1.getCost());
	}
}
