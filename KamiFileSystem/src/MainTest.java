import com.kagami.filesystem.utils.FileManager;

import java.io.File;

/**
 * Created by sinceredeveloper on 16/12/21.
 */
public class MainTest {
    public static void main(String[] args) throws Exception{
        FileManager.INSTANCE.setupWithDic(new File("/Users/admin/Desktop/tmp"));
        for(String name:FileManager.INSTANCE.getFileMap().keySet()){
            System.out.println(name);
        }
    }
}
