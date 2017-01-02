package com.kagami.filesystem.utils

import org.apache.commons.codec.digest.DigestUtils
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry
import org.apache.commons.compress.archivers.zip.ZipFile
import org.apache.commons.io.IOUtils
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.util.*

/**
 * Created by sinceredeveloper on 16/12/24.
 */
object FileManager{
    val fileMap:MutableMap<String, File>
    val coverNameMap:MutableMap<String, String>

    init {
        fileMap=HashMap<String,File>()
        coverNameMap=HashMap<String,String>()
    }

    fun setupWithDic(dir:File){
        if( !dir.isDirectory )
            return
        fileMap.clear()
        dir.listFiles().iterator().forEach {
            if(it.name.endsWith(".zip"))
                putZipFile(it)
        }
    }

    fun putZipFile(file:File):Boolean{
        try {
            //val fis = FileInputStream(file)
            val md5 =DigestUtils.md5Hex(file.name)
            //val md5 = DigestUtils.md5Hex(fis)
            //IOUtils.closeQuietly(fis)
            fileMap.put(md5,file)
            findCover(file)?.let { coverNameMap.put(md5, it) }
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }

    }

    fun getZipFile(md5:String):File?{
        return fileMap.get(md5)
    }

    fun findCover(file:File):String?{
        val zipFile = ZipFile(file)
        val entries=zipFile.entries
        var tag:String?=null
        entries.iterator().forEach {
            if(it.name.endsWith(".png") || it.name.endsWith(".jpg")){
                if(tag==null){
                    tag=it.name
                }else{
                    if(tag!!.compareTo(it.name)>0){
                        tag=it.name
                    }
                }

            }
        }
        return tag
    }
    fun getImageInputStream(zipMD5:String?):InputStream?{
        zipMD5 ?: return null
        val file= fileMap.get(zipMD5)
        file ?: return null
        val zipFile = ZipFile(file)
        coverNameMap.get(zipMD5)?.let {
            return zipFile.getInputStream(zipFile.getEntry(it))
        }
        return null

    }
}
