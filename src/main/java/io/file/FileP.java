package io.file;

import java.io.File;

public class FileP {

	public static void main(String[] args) {
		File file = new File("D:" + File.separator + "text.txt");
		System.out.println(file.getParent());
		System.out.println(file.getName());
		System.out.println(File.listRoots());
	}
}
