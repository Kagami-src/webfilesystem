package com.kagami.filesystem.ser

import com.kagami.filesystem.utils.DBManager
import com.kagami.filesystem.utils.FileManager
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by sinceredeveloper on 16/12/24.
 */
@WebServlet(name = "FileListServlet", urlPatterns = arrayOf("filelist"))
class FileListServlet : HttpServlet() {
    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        val array=JSONArray()
        FileManager.fileMap.keys.iterator().forEach {
            val file=FileManager.getZipFile(it)
            file ?: return
            val json=toJson(file)
            json.put("md5",it)
            array.put(json)
        }
        resp.characterEncoding="utf-8"
        resp.contentType="text/json;charset=utf-8"
        resp.addHeader("Access-Control-Allow-Origin", "*")
        resp.writer.print(array.toString())
    }

    fun toJson(file: File):JSONObject{
        val json=JSONObject()
        json.put("size",file.length())
        json.put("filename",file.name)
        json.put("date",file.lastModified())
        json.put("url",DBManager.setting.optString("dlurl")+file.name)
        return json
    }
}