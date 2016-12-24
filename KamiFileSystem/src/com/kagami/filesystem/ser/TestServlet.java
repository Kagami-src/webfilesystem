package com.kagami.filesystem.ser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * Created by sinceredeveloper on 16/12/20.
 */
@WebServlet(name = "TestServlet",urlPatterns = "test")
public class TestServlet extends HttpServlet {
    int count=0;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String str="";
        File[] files=listFile();
        for (File item : files) {
            str+=item.getName();
        }
        response.getWriter().write(str);
    }

    private File[] listFile(){
        File path=new File("/Users/admin/Desktop/tmp");
        return path.listFiles();
    }
}
