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
public class AddressDto {

    private String addressName;
    private Integer addreddId;
    private double longitude;
    private Double latitude;

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

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}
