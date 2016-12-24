package com.kagami.filesystem.ser;

import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by sinceredeveloper on 16/12/20.
 */
@WebServlet(name = "SettingServlet",urlPatterns = "setting")
public class SettingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject ret=new JSONObject();
        ret.put("path","/////");
        response.addHeader("Access-Control-Allow-Origin","*");
        response.getWriter().write(ret.toString());
    }
}
