package com.hisen.emvco.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @Author hisenyuan
 * @Description $end$
 * @Date 2019/3/6 21:13
 */
public class EMVCoParser {
    private static final int DEFAULT_LENGTH = 2;
    private static final String DEFAULT_START_TAG = "00";

    /**
     * @param subTlv
     * @return
     */
    public static Map<Integer, Map<Integer, EmvcoTlvBean>> parseSubTlv(Map<Integer, EmvcoTlvBean> subTlv) {
        Map<Integer, Map<Integer, EmvcoTlvBean>> result = new HashMap<>();
        for (Map.Entry<Integer, EmvcoTlvBean> emvcoTlvBeanEntry : subTlv.entrySet()) {
            final EmvcoTlvBean emvcoTlvBean = emvcoTlvBeanEntry.getValue();
            final String value = emvcoTlvBean.getValue();
            if (value.length() > 4) {
                final String tag = value.substring(0, DEFAULT_LENGTH);
                final String lengthStr = value.substring(DEFAULT_LENGTH, DEFAULT_LENGTH * 2);
                if (isInteger(tag) && isInteger(lengthStr)) {
                    final int length = Integer.parseInt(lengthStr);
                    if (tag.equals(DEFAULT_START_TAG) && length > 0) {
                        final Map<Integer, EmvcoTlvBean> parse = parse(value);
                        result.put(emvcoTlvBeanEntry.getKey(), parse);
                    }
                }
            }
        }
        return result;

    }

    public static Map<Integer, EmvcoTlvBean> parse(String emvcoTlvStr) {
        Map<Integer, EmvcoTlvBean> result = new HashMap<>();
        int i = 0;
        while (i < emvcoTlvStr.length()) {
            int tagInd = i + DEFAULT_LENGTH;
            int tag = Integer.parseInt(emvcoTlvStr.substring(i, tagInd));
            int lengthValue = Integer.parseInt(emvcoTlvStr.substring(tagInd, tagInd + DEFAULT_LENGTH));
            EmvcoTlvBean emvcoTlvBean = new EmvcoTlvBean();
            emvcoTlvBean.setTag(String.valueOf(tag));
            emvcoTlvBean.setLength(lengthValue);
            emvcoTlvBean.setValue(emvcoTlvStr.substring(tagInd + DEFAULT_LENGTH, tagInd + DEFAULT_LENGTH + lengthValue));

            result.put(tag, emvcoTlvBean);

            i = tagInd + DEFAULT_LENGTH + lengthValue;
        }

        return result;
    }

    public static void printTag(Map<Integer, EmvcoTlvBean> emvcoTlvBeanMap) {
        for (Map.Entry<Integer, EmvcoTlvBean> emvcoTlvBeanEntry : emvcoTlvBeanMap.entrySet()) {
            final EmvcoTlvBean emv = emvcoTlvBeanEntry.getValue();
            final Integer key = emvcoTlvBeanEntry.getKey();
            printOneTlv(emv, key);
        }
    }

    public static void printOneTlv(EmvcoTlvBean emv, Integer key) {
        System.out.println("Tag:" + (key < 9 ? "0" + emv.getTag() : emv.getTag()) + "\t Length:" + emv.getLength() + "\t Value:" + emv.getValue());
    }

    private static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
}
