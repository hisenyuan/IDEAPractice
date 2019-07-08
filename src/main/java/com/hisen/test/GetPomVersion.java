package com.hisen.test;

import org.junit.Test;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * @Author hisenyuan
 * @Description .java$
 * @Date 2019-05-16 16:40
 */
public class GetPomVersion {
    private static String appVersion;

    public static void main(String[] args) {
        getAppVersion();
    }

    @Test
    public void testGetVersion(){
        getAppVersion();
        System.out.println(appVersion);
    }

    public static String getAppVersion() {
        if (null == appVersion) {
            try {
//                Properties properties = PropertiesLoaderUtils.loadAllProperties("app.properties");
                Properties properties = PropertiesLoaderUtils.loadAllProperties("db.properties");
                if (!properties.isEmpty()) {
                    appVersion = properties.getProperty("app.version");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return appVersion;
    }
}
