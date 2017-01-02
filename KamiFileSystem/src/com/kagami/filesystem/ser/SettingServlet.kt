package com.kagami.filesystem.ser

import com.kagami.filesystem.utils.DBManager
import com.kagami.filesystem.utils.FileManager
import java.io.File
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by sinceredeveloper on 16/12/30.
 */
@WebServlet(name = "SettingServlet", urlPatterns = arrayOf("setting"))
class SettingServlet: HttpServlet(){
    override fun doGet(req: HttpServletRequest?, resp: HttpServletResponse) {
        resp.addHeader("Access-Control-Allow-Origin", "*")
        resp.getWriter().write(DBManager.setting.toString())
    }

    override fun doPost(req: HttpServletRequest, resp: HttpServletResponse?) {
        val roodir=req.getParameter("rootdir")
        val dlurl=req.getParameter("dlurl")
        DBManager.putSetting("rootdir",roodir)
                .putSetting("dlurl",dlurl)
                .saveSetting()
        FileManager.setupWithDic(File(roodir))
    }
}
