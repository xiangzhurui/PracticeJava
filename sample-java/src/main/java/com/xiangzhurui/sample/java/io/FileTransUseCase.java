package com.xiangzhurui.sample.java.io;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileTransUseCase {

	public static void main(String[] args) {
		File f = new File("D:\\Downloads\\");
		List<File> files = getAllFileInDir(f);
		for (File file : files) {
			System.out.println(file);
		}
		System.out.println(files.size());
	}

	public static List<File> getAllFileInDir(File dir) {
		if (!dir.isDirectory()) {
			return null;
		}

		File[] files = dir.listFiles();
		if (files.length == 0) {
			return null;
		}
		List<File> fileList = new ArrayList<File>(Arrays.asList(files));
		for (File childDir : files) {
			if (childDir.isDirectory()) {
				List<File> childFileList = getAllFileInDir(childDir);
				if(childFileList!=null){
					fileList.addAll(childFileList);
				}
			}
		}
		return fileList;
	}

}
