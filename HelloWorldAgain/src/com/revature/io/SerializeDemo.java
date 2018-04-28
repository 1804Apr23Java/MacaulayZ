package com.revature.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerializeDemo {

	public static void main(String[] args) {
		String filename = "src/serializedThings/Movies";
	}
	
	public static void serializedObject(String filename, Object o) {
		try {
			//use a FileOutputStream to write bytestream to a file
			FileOutputStream fileout = new FileOutputStream(filename);
			//use a ObjectOutputStream to represent an object as a bytestream
			ObjectOutputStream out = new ObjectOutputStream(fileout);
			//actually serialize the object
			out.writeObject(o);
			//close resources
			out.close();
		} catch (FileNotFoundException f) {
			f.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
