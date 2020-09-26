package com.hisen.copy.smaller.utils;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.hisen.copy.smaller.bean.Address;
import com.hisen.copy.smaller.bean.Order;
import com.hisen.copy.smaller.bean.ProductInfo;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * copy a smaller bean
 *
 * @author hisenyuan
 * @date 2020-09-26 14:49
 */
public class CopyLimitedPropertiesUtil {
    /**
     * all index
     */
    private final static String ALL_INDEX = "[-1]";

    public static void main(String[] args) {
        Order order = getOrder();
        Order smallerOrder = new Order();
        List<String> keepFields = Lists.newArrayList();
        keepFields.add("orderId");
        keepFields.add("address.province");
        keepFields.add("productInfos" + ALL_INDEX + ".name");
        keepFields.add("productInfos" + ALL_INDEX + ".imgUrl");
        copyLimitedProperties(order, smallerOrder, keepFields);
        Gson gson = new Gson();
        System.out.println("src:" + gson.toJson(order));
        System.out.println("dst:" + gson.toJson(smallerOrder));
    }


    /**
     * only copy limited fields which you want
     *
     * @param src
     * @param dst
     * @param fields
     */
    private static void copyLimitedProperties(Object src, Object dst, List<String> fields) {
        BeanWrapper srcWarp = PropertyAccessorFactory.forBeanPropertyAccess(src);
        BeanWrapper dstWarp = PropertyAccessorFactory.forBeanPropertyAccess(dst);
        // 处理集合 key
        handleCollection(fields, srcWarp);
        dstWarp.setAutoGrowNestedPaths(true);
        fields.forEach(f -> dstWarp.setPropertyValue(f, srcWarp.getPropertyValue(f)));

    }

    /**
     * fill all index for collection key
     *
     * @param fields
     * @param srcWarp
     */
    private static void handleCollection(List<String> fields, BeanWrapper srcWarp) {
        Iterator<String> iterator = fields.iterator();
        List<String> cl = Lists.newArrayList();
        while (iterator.hasNext()) {
            String field = iterator.next();
            if (field.contains(ALL_INDEX)) {
                int indexOf = field.indexOf(ALL_INDEX);
                String key = field.substring(0, indexOf);
                Object propertyValue = srcWarp.getPropertyValue(key);
                if (propertyValue instanceof Collection) {
                    Collection collection = (Collection) propertyValue;
                    for (int i = 0; i < collection.size(); i++) {
                        cl.add(key + "[" + i + "]" + field.substring(indexOf + ALL_INDEX.length()));
                    }
                    iterator.remove();
                }
            }
        }
        fields.addAll(cl);
    }

    /**
     * getOrder
     *
     * @return
     */
    private static Order getOrder() {
        Order order = new Order();
        Address address = new Address();
        address.setCity("A");
        address.setProvince("B");
        address.setCounty("C");
        order.setAddress(address);
        List<ProductInfo> productInfos = Lists.newArrayList();
        int times = 3;
        for (int i = 0; i < times; i++) {
            ProductInfo productInfo = new ProductInfo();
            productInfo.setProductId(i);
            productInfo.setName("name" + i);
            productInfo.setImgUrl("url" + 1);
            productInfo.setPrice(new BigDecimal(i + 1));
            productInfos.add(productInfo);
        }
        order.setProductInfos(productInfos);
        return order;
    }
}
