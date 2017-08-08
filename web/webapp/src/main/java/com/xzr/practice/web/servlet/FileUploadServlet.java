package com.xzr.practice.web.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "FileUploadServlet", urlPatterns = { "/upload" })
@MultipartConfig(location = "D:/", fileSizeThreshold = 1024)
public class FileUploadServlet extends javax.servlet.http.HttpServlet {
    private static final Logger log              = LoggerFactory.getLogger(FileUploadServlet.class);

    private static final long   serialVersionUID = 2184326292370145837L;
    private static final String SAVE_PATH        = "D:\\";

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("进入通过注解配置的servlet");
        request.setCharacterEncoding("utf-8");

        Part part = request.getPart("file"); // request.getParts();
        File f = new File(SAVE_PATH + File.separator);
        if (!f.exists()) {
            f.mkdirs();
        }

        String h = part.getHeader("content-disposition");
        String filename = h.substring(h.lastIndexOf("=") + 2, h.length() - 1);

        part.write(SAVE_PATH + File.separator + filename);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print("上传成功");
        out.flush();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("进入get方法");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print("通过get方法访问");
        out.flush();
    }
}