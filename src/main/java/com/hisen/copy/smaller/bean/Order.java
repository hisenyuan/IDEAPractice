package com.hisen.copy.smaller.bean;

import java.util.List;

/**
 * @author hisenyuan
 * @date 2020-09-26 14:44
 */
public class Order {
    private String orderId;
    private Address address;
    private List<ProductInfo> productInfos;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<ProductInfo> getProductInfos() {
        return productInfos;
    }

    public void setProductInfos(List<ProductInfo> productInfos) {
        this.productInfos = productInfos;
    }
}
