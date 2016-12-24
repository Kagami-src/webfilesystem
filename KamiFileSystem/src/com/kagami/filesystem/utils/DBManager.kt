package com.kagami.filesystem.utils

import org.apache.commons.io.FileUtils
import org.json.JSONObject
import java.io.File
import java.nio.charset.Charset


/**
 * Created by sinceredeveloper on 16/12/24.
 */
object DBManager{
    val setting:JSONObject
    init {
        val settingstr= load("setting")
        setting = toJson(settingstr) ?: JSONObject()
    }
    fun putSetting(key:String,value:String):DBManager{
         setting.put(key,value)
         return this
     }
    fun saveSetting():Boolean{
        return save("setting", setting.toString())
    }

    private fun save(fileName:String,text:String):Boolean{
        try {
            FileUtils.writeStringToFile(File(fileName), text, Charset.forName("utf8"), false)
            return true
        }catch (ex:Exception){
            ex.printStackTrace()
            return false
        }
    }
    private fun load(fileName: String):String?{
        try {
            return FileUtils.readFileToString(File(fileName),Charset.forName("utf8"))
        }catch (ex:Exception){
            ex.printStackTrace()
            return null
        }
    }
    private fun toJson(str:String?):JSONObject?{
        try {
            return JSONObject(str)
        }catch (ex:Exception){
            return null
        }
    }


}