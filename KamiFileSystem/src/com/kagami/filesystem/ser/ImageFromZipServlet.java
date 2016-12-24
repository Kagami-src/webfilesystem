package com.kagami.filesystem.ser;

import com.kagami.filesystem.utils.FileManager;
import org.apache.commons.compress.utils.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by sinceredeveloper on 16/12/21.
 */
@WebServlet(name = "ImageFromZipServlet",urlPatterns = "imagefromzip")
public class ImageFromZipServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String md5=request.getParameter("md5");
        InputStream is= FileManager.INSTANCE.getImageInputS(md5);
        if(is!=null){
            IOUtils.copy(is,response.getOutputStream());
            IOUtils.closeQuietly(is);
        }

    }
}
