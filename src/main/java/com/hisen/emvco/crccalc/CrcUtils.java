package com.hisen.emvco.crccalc;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author hisenyuan
 * @Description 计算CrcCode
 * @Date 2019/3/6 21:14
 */
public class CrcUtils {

    /**
     * only need CRC-16/CCITT-FALSE
     *
     * @param data oriData
     * @return CRC-16/CCITT-FALSE crcCode
     */
    public static String getCrc16CcittFalseCode(String data) {
        CrcCalculator calculator = new CrcCalculator(Crc16.Crc16CcittFalse);
        long result = calculator.Calc(data.getBytes(), 0, data.getBytes().length);
        if (result != calculator.Parameters.Check) {
            return Long.toHexString(result).toUpperCase();
        }
        return "";
    }

    /**
     * get all crcCode
     *
     * @param params
     * @param data
     * @return Map<crcType , crcCode>
     */
    public static Map<String, String> getAllCrcCode(AlgoParams[] params, String data) {
        Map<String, String> res = new HashMap<>();
        for (AlgoParams param : params) {
            CrcCalculator calculator = new CrcCalculator(param);
            long result = calculator.Calc(data.getBytes(), 0, data.getBytes().length);
            if (result != calculator.Parameters.Check) {
                res.put(calculator.Parameters.Name, Long.toHexString(result).toUpperCase());
            }
        }
        return res;
    }
}