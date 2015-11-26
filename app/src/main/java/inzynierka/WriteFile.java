package inzynierka;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import rest.SettingConnections;

/**
 * Created by Agnieszka on 2015-11-21.
 */
public class WriteFile {

    Context context = SettingConnections.context;

    private void writeStringInFile(String fileName,
                                   String stringToWrite)
            throws IOException {
        FileOutputStream fileOutputStream = context.openFileOutput( fileName,
                                                                    Context.MODE_PRIVATE);
        fileOutputStream.write(stringToWrite.getBytes());
        fileOutputStream.close();
    }

    private void writeFileInNewDirectory() throws IOException {
        File newDir = context.getDir("Nowy_Folder", Context.MODE_PRIVATE);
        File newFile = new File (newDir.getAbsolutePath() + File.separator, "nazwaPliku.txt"); //nowy plik
        newFile.createNewFile(); //stworzenie pliku
        FileOutputStream fos = new FileOutputStream(newFile.getAbsolutePath());
    }

    public static boolean isSDCardWritable(){
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }
    public static boolean isSDCardReadable(){
        return Environment.getExternalStorageState().
                equals(Environment.MEDIA_MOUNTED_READ_ONLY) || isSDCardWritable();
    }

}
