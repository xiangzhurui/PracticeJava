package web.file.upload;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 使用Servlet 3.0特性上传文件
 * @author XiangZhuRui
 *
 */
@WebServlet(urlPatterns = "/data/upload")
public class FileUploadServlet extends HttpServlet {
    final static Logger       logger           = LoggerFactory.getLogger(FileUploadServlet.class);

    private static final long serialVersionUID = 481037926257070866L;

}
