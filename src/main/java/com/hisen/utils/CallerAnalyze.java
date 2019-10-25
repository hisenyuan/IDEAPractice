package com.hisen.utils;


import com.google.common.collect.Lists;
import com.google.common.io.Files;
import com.google.common.io.LineProcessor;
import org.joda.time.DateTime;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @author hisenyuan
 * @date 2019-10-25 23:20
 */
public class CallerAnalyze {
    public static void main(String[] args) throws IOException {
        String filePath = "/Users/hisenyuan/yw/yw.log";
        ArrayList<CallerTimeVo> callerTimeVos = getCallerTimeVos(filePath);

        System.out.println("size:" + callerTimeVos.size());
        System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s", "yw", "count", "min(ms)", "max(ms)", "tp50(ms)", "tp90(ms)", "tp99(ms)", "tp999(ms)");
        System.out.println();


        Map<String, List<CallerTimeVo>> callerMap = callerTimeVos.parallelStream().collect(Collectors.groupingBy(CallerTimeVo::getYw));

        callerMap.entrySet()
                .stream()
                .sorted(Comparator.comparingInt(value -> value.getValue().size()))
                .forEach(stringListEntry -> {
                    String yw = stringListEntry.getKey();
                    System.out.printf("%-20s", yw);
                    List<Long> sorted = stringListEntry.getValue()
                            .parallelStream()
                            .map(CallerTimeVo::getDuration)
                            .sorted(Long::compareTo)
                            .collect(Collectors.toList());
                    calTime(sorted);
                });
    }

    private static ArrayList<CallerTimeVo> getCallerTimeVos(String filePath) throws IOException {
        ArrayList<CallerTimeVo> callerTimeVos = Lists.newArrayList();
        Files.asCharSource(new File(filePath), Charset.forName("UTF-8")).readLines(new LineProcessor<String>() {
            @Override
            public boolean processLine(String line) {
                // 处理每一行
                CallerTimeVo vo = getCreateTimeVo(line);
                callerTimeVos.add(vo);
                // false 会中断操作
                return true;
            }

            @Override
            public String getResult() {
                return null;
            }
        });
        return callerTimeVos;
    }


    private static CallerTimeVo getCreateTimeVo(String line) {
        String[] arrLine = line.split(" ");

        String[] queryTimeArray = ("20" + arrLine[0]).split("\\.");

        String queryTime = queryTimeArray[0] + "T" + queryTimeArray[1] + "." + queryTimeArray[2];
        String caller = arrLine[14].split(":")[1].replace(",", "");
        String orderId = arrLine[15].split(":")[1].replace(",", "");
        String orderTime = arrLine[16].split("time:")[1];

        CallerTimeVo vo = new CallerTimeVo();
        vo.setYw(caller);
        vo.setOrderId(orderId);
        vo.setDuration(getUnixTime(queryTime) - getUnixTime(orderTime));
        return vo;
    }

    private static long getUnixTime(String time) {
        DateTime dt = new DateTime(time);
        return dt.getMillis();
    }

    private static void calTime(List<Long> timeDurations) {
        int size = timeDurations.size();
        double tp50 = size * 0.5 - 1;
        double tp90 = size * 0.9 - 1;
        double tp99 = size * 0.99 - 1;
        double tp999 = size * 0.999 - 1;

        // 基础统计，包含max、min、count、avg信息;
        DoubleSummaryStatistics doubleSummaryStatistics = timeDurations.parallelStream().mapToDouble(Long::longValue).summaryStatistics();

        System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s",
                doubleSummaryStatistics.getCount(),
                (int) doubleSummaryStatistics.getMin(),
                (int) doubleSummaryStatistics.getMax(),
                (tp50 >= size ? "" : timeDurations.get((int) Math.ceil(tp50))),
                (tp90 >= size ? "" : timeDurations.get((int) Math.ceil(tp90))),
                (tp99 >= size ? "" : timeDurations.get((int) Math.ceil(tp99))),
                (tp999 >= size ? "" : timeDurations.get((int) Math.ceil(tp999))));
        System.out.println();
    }


    public static class CallerTimeVo {
        private String orderTime;
        private String queryTime;
        private long unixOrderTime;
        private long unixQueryTime;
        private long duration;
        private String orderId;
        private String yw;

        public String getOrderTime() {
            return orderTime;
        }

        public void setOrderTime(String orderTime) {
            this.orderTime = orderTime;
        }

        public String getQueryTime() {
            return queryTime;
        }

        public void setQueryTime(String queryTime) {
            this.queryTime = queryTime;
        }

        public long getUnixOrderTime() {
            return unixOrderTime;
        }

        public void setUnixOrderTime(long unixOrderTime) {
            this.unixOrderTime = unixOrderTime;
        }

        public long getUnixQueryTime() {
            return unixQueryTime;
        }

        public void setUnixQueryTime(long unixQueryTime) {
            this.unixQueryTime = unixQueryTime;
        }

        public long getDuration() {
            return duration;
        }

        public void setDuration(long duration) {
            this.duration = duration;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getYw() {
            return yw;
        }

        public void setYw(String yw) {
            this.yw = yw;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", CallerTimeVo.class.getSimpleName() + "[", "]")
                    .add("orderTime='" + orderTime + "'")
                    .add("queryTime='" + queryTime + "'")
                    .add("unixOrderTime=" + unixOrderTime)
                    .add("unixQueryTime=" + unixQueryTime)
                    .add("duration=" + duration)
                    .add("orderId='" + orderId + "'")
                    .add("yw='" + yw + "'")
                    .toString();
        }
    }
}
