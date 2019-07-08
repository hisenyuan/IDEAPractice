package com.hisen.test;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author hisenyuan
 * @Description $end$
 * @Date 2018/9/23 08:41
 */
public class SortStr {
    public static void main(String[] args) {
        String listStr = "1. 王志平\n" +
                "2. 王平\n" +
                "3. 游和兵（白鹿总负责)\n" +
                "4. 张叔农\n" +
                "5. 王文亮\n" +
                "6. 李菁颉\n" +
                "7. 卢海\n" +
                "8. 郭振威\n" +
                "9. 陈颖\n" +
                "10. 刘亭\n" +
                "11. 王国梁\n" +
                "12. 杨赫\n" +
                "13. 陈辉\n" +
                "14. 邓洪波\n" +
                "15. \n" +
                "16. 袁泽根\n" +
                "17. 袁海幸\n" +
                "18. 潘嘉仪\n" +
                "19. 袁智诚\n" +
                "20. 袁振龙\n" +
                "21. 郭观林\n" +
                "22. 邓飞帆（预报名）\n" +
                "23. 李斌\n" +
                "24. 陈璐\n" +
                "25. \n" +
                "26. 魏联峰\n" +
                "27. 王力（暂定）\n" +
                "28. 韩友\n" +
                "29. 汪普秀\n" +
                "30. 陈沛戎\n" +
                "31. 彭羽\n" +
                "32. 韩玉晴\n" +
                "33. 叶畅\n" +
                "34. 陈晓燕\n" +
                "35. 彭慧诲\n" +
                "36. 辛梅\n" +
                "37. 周艳\n" +
                "38. \n" +
                "39. 袁丽敏\n" +
                "40. 林宾华 2\n" +
                "41. 周清耀\n" +
                "42. 王汉传\n" +
                "43. 周沁怡\n" +
                "44. 杨招庚\n" +
                "45. 王开\n" +
                "46. 杨愉\n" +
                "47. 龙军(龙瑞荣）\n" +
                "48. 曹欢\n" +
                "49. 李章梅\n" +
                "50. 王程远\n" +
                "51. 韩辉\n" +
                "52. 欧阳建（会晚到一会儿）\n" +
                "53. 涂婷";
        String[] all = listStr.split("\n");
        AtomicInteger count = new AtomicInteger(1);
        List<String> sortList = new LinkedList<>();
        for (String one : all) {
            String[] oneSplit = one.split("\\. ");
            if (oneSplit.length>1){
                sortList.add(count.getAndIncrement() + ". " + oneSplit[1]);
            }
        }
        for (int i = 0; i < sortList.size(); i++) {
            System.out.println(sortList.get(i));
        }
    }
}
