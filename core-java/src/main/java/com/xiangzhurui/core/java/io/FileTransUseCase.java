package com.xiangzhurui.core.java.io;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileTransUseCase {

	private static final String USER_HOME_DIR = System.getProperty("user.home");
	private static final Path DOWNLOADS_DIR_PATH = Paths.get(USER_HOME_DIR,"Downloads");

	public static void main(String[] args) {
		File f = DOWNLOADS_DIR_PATH.toFile();
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
