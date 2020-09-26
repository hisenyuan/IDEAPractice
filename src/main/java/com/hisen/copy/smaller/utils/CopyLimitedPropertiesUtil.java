package com.hisen.copy.smaller.utils;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.hisen.copy.smaller.bean.Address;
import com.hisen.copy.smaller.bean.Order;
import com.hisen.copy.smaller.bean.ProductInfo;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

import java.math.BigDecimal;
import java.util.List;

/**
 * copy a smaller bean
 *
 * @author hisenyuan
 * @date 2020-09-26 14:49
 */
public class CopyLimitedPropertiesUtil {
    public static void main(String[] args) {
        Order order = getOrder();
        Order smallerOrder = new Order();
        List<String> keepFields = Lists.newArrayList();
        keepFields.add("orderId");
        keepFields.add("address.province");
        // i want get all names, not only index 1, how to do ?
        keepFields.add("productInfos[1].name");
        copyLimitedProperties(order, smallerOrder, keepFields);
        Gson gson = new Gson();
        System.out.println("src:" + gson.toJson(order));
        System.out.println("des" + gson.toJson(smallerOrder));
//        src:{"address":{"province":"B","city":"A","county":"C"},"productInfos":[{"productId":0,"name":"name0","price":1,"num":0,"imgUrl":"url1"},{"productId":0,"name":"name1","price":2,"num":0,"imgUrl":"url1"},{"productId":0,"name":"name2","price":3,"num":0,"imgUrl":"url1"}]}
//        des{"address":{"province":"B"},"productInfos":[{"productId":0,"num":0},{"productId":0,"name":"name1","num":0}]}
    }


    /**
     * only copy fields you want
     * @param src
     * @param dst
     * @param fields
     */
    private static void copyLimitedProperties(Order src, Order dst, List<String> fields) {
        BeanWrapper srcWarp = PropertyAccessorFactory.forBeanPropertyAccess(src);
        BeanWrapper dstWarp = PropertyAccessorFactory.forBeanPropertyAccess(dst);
        dstWarp.setAutoGrowCollectionLimit(10);
        dstWarp.setAutoGrowNestedPaths(true);
        fields.forEach(f -> dstWarp.setPropertyValue(f, srcWarp.getPropertyValue(f)));
    }

    /**
     * getOrder
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
            productInfo.setName("name" + i);
            productInfo.setImgUrl("url" + 1);
            productInfo.setPrice(new BigDecimal(i + 1));
            productInfos.add(productInfo);
        }
        order.setProductInfos(productInfos);
        return order;
    }
}
