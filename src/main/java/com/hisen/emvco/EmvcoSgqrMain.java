package com.hisen.emvco;

import com.hisen.emvco.crccalc.CrcUtils;
import com.hisen.emvco.parser.EMVCoParser;
import com.hisen.emvco.parser.EmvcoTlvBean;

import java.util.Map;

/**
 * @Author hisenyuan
 * @Description 生成合法的SGQR - EMVco QrCode
 * @Date 2019/3/6 21:14
 */
public class EmvcoSgqrMain {
    public static void main(String[] args) {
        // 按格式拼接好待进行Crc计算的原文，所有的TLV都参与，除了Tag:63 Length:4 的Value(这时候还没有Value)
        String qrStr = "00020101021128650017COM.QQ.WEIXIN.PAY0110100000033702040000030400010404000099020129660018COM.ALIPAY.OPENAPI0110100000033702040000030400010404000099020151800007SG.SGQR01121809072DDA29020701.00010306058602040200050200060400000708201809155204000053037025802SG5901h6009Singapore6304";

        // 按Emvco的格式进行Crc校验码计算
        final String crcCode = CrcUtils.getCrc16CcittFalseCode(qrStr);
        System.out.println("crcCode:\t" + crcCode);

        // 二维码文本数据，拿着生成二维码即可(真实商户信息，请勿付款)
        final String tlv = qrStr + crcCode;
        System.out.println("emvcoStr:\t" + tlv);

        // 打印Tag
        final Map<Integer, EmvcoTlvBean> emvcoTlvBeanMap = EMVCoParser.parse(tlv);
        System.out.println("\nPrint\tTag");
        EMVCoParser.printTag(emvcoTlvBeanMap);

        // 打印SubTag
        final Map<Integer, Map<Integer, EmvcoTlvBean>> parseSubTlv = EMVCoParser.parseSubTlv(emvcoTlvBeanMap);
        for (Map.Entry<Integer, Map<Integer, EmvcoTlvBean>> subs : parseSubTlv.entrySet()) {
            System.out.println();
            EMVCoParser.printOneTlv(emvcoTlvBeanMap.get(subs.getKey()), subs.getKey());
            System.out.println("Print\tSubTag");
            EMVCoParser.printTag(subs.getValue());
        }

    }
}
