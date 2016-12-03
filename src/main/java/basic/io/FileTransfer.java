package basic.io;

import java.io.File;
import java.util.List;

/**
 * 文件上传下载接口
 * 
 * @author XiangZhuRui
 *
 */
public interface FileTransfer {
    /**
     * 上传文件
     * 
     * @param file
     *            本地文件
     * @return
     */

    public boolean uploadFile(File file);

    /**
     * 上传文件
     * 
     * @param fileName
     *            文件名
     * @return
     */
    public boolean uploadFile(String fileName);

    /**
     * 下载文件file
     * 
     * @param file
     *            远程文件
     * @return
     */
    public boolean downloadFile(File file);

    /**
     * 下载文件夹下的所有文件
     * 
     * @param file
     *            远程文件目录
     * @return
     */
    public boolean downloadDir();

    /**
     * 列出目录下得所有文件
     * 
     * @param file
     *            远程文件目录
     * @return
     */
    public List<File> lsFiles(File file);
}
