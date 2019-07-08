package com.translate.google;


import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

/**
 * @Author hisenyuan
 * @Description $end$
 * @Date 2019/1/14 21:36
 */
public class QuickstartSample {
    public static void main(String... args) throws Exception {
        // Instantiates a client
        Translate translate = TranslateOptions.getDefaultInstance().getService();
//        Translate translate = TranslateOptions.newBuilder().setApiKey("AIzaSyAG_70jWZnF4nC8slbqMgIE09ef6vvggBw").build().getService();
        // The text to translate
        String text = "Hello, world!";

        // Translates some text into Russian
        Translation translation =
                translate.translate(
                        text,
                        Translate.TranslateOption.sourceLanguage("en"),
                        Translate.TranslateOption.targetLanguage("ru"));


        System.out.printf("Text: %s%n", text);
        System.out.printf("Translation: %s%n", translation.getTranslatedText());
    }
}