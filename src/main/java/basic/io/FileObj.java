package basic.io;

import java.io.File;

public class FileObj {

	public static void main(String[] args) {
		File file = new File("D:" + File.separator + "text.txt");
		System.out.println(file.getParent());
		System.out.println(file.getName());
		System.out.println(File.listRoots());
	}
}
