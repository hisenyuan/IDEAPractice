package com.hisen.utils;

import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @Author hisenyuan
 * @Description $end$
 * @Date 2019/3/17 02:24
 */
public class FileUtils {
    public static File getFile(String path) throws IOException {
        final File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }

    public static void apendContext(File file, String context) throws IOException {
        Files.append(context, file, Charset.forName("UTF-8"));
    }
}
