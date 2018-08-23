import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author xiangzhurui
 * @version 2018/8/23 10:46
 */
public class FileUtilsTest {
    public static void main(String[] args) throws IOException {
        String s = System.getProperty("user.home");
        System.out.println(s);

        String dir = s + File.separator + "Downloads" + File.separator + "未命名文件夹";
        System.out.println(dir);

        try {
            FileUtils.forceDelete(new File(dir));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
