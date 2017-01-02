package com.kagami.filesystem;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Created by kagami on 2017/1/2.
 */
public class MainTest {
    public static void main(String[] args) throws IOException {
        ZipFile zip=new ZipFile(new File("C:\\Users\\kagami\\Desktop\\bk\\(成年コミック) [いくさりゅうじ] 今ドキのドキドキ + 4Pリーフレット.zip"));
        Enumeration<ZipArchiveEntry>  enty=zip.getEntries();
        String tag=null;
        while (enty.hasMoreElements()){
            ZipArchiveEntry en=enty.nextElement();
            if(tag==null)
                tag=en.getName();
            else{
                if(tag.compareTo(en.getName())>0)
                    tag=en.getName();
            }
        }
        System.out.print(tag);
//        val zipFile = ZipFile(file)
//        val entries=zipFile.entries
//        entries.iterator().forEach {
//            if(it.name.endsWith(".png") || it.name.endsWith(".jpg")){
//                return zipFile.getInputStream(it)
//            }
//        }
    }
}
