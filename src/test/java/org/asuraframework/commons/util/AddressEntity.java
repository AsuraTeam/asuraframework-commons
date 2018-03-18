/**
 * Copyright(c) 2018 asura
 */
package org.asuraframework.commons.util;

/**
 * <p></p>
 *
 *
 * @author liusq23
 * @since 1.0
 * @version 1.0
 * @Date 2018/3/17 下午9:44
 */
public class AddressEntity {

    private String addressName;
    private Integer addreddId;
    private float longitude;

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public Integer getAddreddId() {
        return addreddId;
    }

    public void setAddreddId(Integer addreddId) {
        this.addreddId = addreddId;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
}
