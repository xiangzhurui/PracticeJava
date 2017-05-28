package commons.csv;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class CsvBuilder {
	private static final String LINE_SEPARATOR = "\n";

	public static void main(String[] args) {
	}

	/**
	 * 写入csv文件
	 *
	 * @param file 传入文件的路径，包含文件名
	 */
	private <T> void csvWriter(File file, String[] File_HEAND, List<T> dataLines) {
		FileWriter fileWriter;
		CSVPrinter csvPrinter = null;
		CSVFormat csvFormat = CSVFormat.DEFAULT.withRecordSeparator(LINE_SEPARATOR);
		try {
			fileWriter = new FileWriter(file);
			csvPrinter = new CSVPrinter(fileWriter, csvFormat);
			csvPrinter.printRecord((Object[]) File_HEAND);
			for (T t : dataLines) {
				Class<? extends Object> clazz = t.getClass();
				List<String> cityPriceRecord = new ArrayList<String>();
				csvPrinter.printRecord(cityPriceRecord);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (csvPrinter != null) {
					csvPrinter.flush();
					csvPrinter.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static String captureName(String s) {
		char[] cs = s.toCharArray();
		cs[0] -= 32;
		return String.valueOf(cs);

	}
}
