package com.jspo.hotel.dto;

import java.util.Objects;

public class HotelDto {

    private int htId;
    private String htName;
    private String htAddress;
    private String htImg;
    private String htPhone;

    public HotelDto() {}
    public HotelDto(int htId, String htName, String htAddress, String htImg, String htPhone) {
        this.htId = htId;
        this.htName = htName;
        this.htAddress = htAddress;
        this.htImg = htImg;
        this.htPhone = htPhone;
    }

    public int getHtId() {
        return htId;
    }

    public void setHtId(int htId) {
        this.htId = htId;
    }

    public String getHtName() {
        return htName;
    }

    public void setHtName(String htName) {
        this.htName = htName;
    }

    public String getHtAddress() {
        return htAddress;
    }

    public void setHtAddress(String htAddress) {
        this.htAddress = htAddress;
    }

    public String getHtImg() {
        return htImg;
    }

    public void setHtImg(String htImg) {
        this.htImg = htImg;
    }

    public String getHtPhone() {
        return htPhone;
    }

    public void setHtPhone(String htPhone) {
        this.htPhone = htPhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HotelDto hotelDto = (HotelDto) o;
        return htId == hotelDto.htId && Objects.equals(htName, hotelDto.htName) && Objects.equals(htAddress, hotelDto.htAddress) && Objects.equals(htImg, hotelDto.htImg) && Objects.equals(htPhone, hotelDto.htPhone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(htId, htName, htAddress, htImg, htPhone);
    }

    @Override
    public String toString() {
        return "HotelDto{" +
                "htId=" + htId +
                ", htName='" + htName + '\'' +
                ", htAddress='" + htAddress + '\'' +
                ", htImg='" + htImg + '\'' +
                ", htPhone='" + htPhone + '\'' +
                '}';
    }

    // DB조회용 user객체 싱글톤패턴으로 재사용
    private static final HotelDto instance = new HotelDto();

    public static HotelDto getInstance() {
        return instance;
    }
}
