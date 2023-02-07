package com.jspo.room.dto;

import com.jspo.hotel.dto.HotelDto;

import java.util.Date;
import java.util.Objects;

public class RoomDto {
    private int rId;
    private String rName;
    private int rPrice;
    private int rState;
    private int rCnt;
    private String rImg;
    private Date rCheckin;
    private Date rCheckout;
    private int hotelHtId;

    private HotelDto hotelDto;
    public RoomDto() {}

    public RoomDto(int rId, String rName, int rPrice, int rState, int rCnt, String rImg, Date rCheckin, Date rCheckout, int hotelHtId) {
        this.rId = rId;
        this.rName = rName;
        this.rPrice = rPrice;
        this.rState = rState;
        this.rCnt = rCnt;
        this.rImg = rImg;
        this.rCheckin = rCheckin;
        this.rCheckout = rCheckout;
        this.hotelHtId = hotelHtId;
    }

    private static final RoomDto instance = new RoomDto();

    public static RoomDto getInstance() {
        return instance;
    }

    public int getrId() {
        return rId;
    }

    public void setrId(int rId) {
        this.rId = rId;
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }

    public int getrPrice() {
        return rPrice;
    }

    public void setrPrice(int rPrice) {
        this.rPrice = rPrice;
    }

    public int getrState() {
        return rState;
    }

    public void setrState(int rState) {
        this.rState = rState;
    }

    public int getrCnt() {
        return rCnt;
    }

    public void setrCnt(int rCnt) {
        this.rCnt = rCnt;
    }

    public String getrImg() {
        return rImg;
    }

    public void setrImg(String rImg) {
        this.rImg = rImg;
    }

    public Date getrCheckin() {
        return rCheckin;
    }

    public void setrCheckin(Date rCheckin) {
        this.rCheckin = rCheckin;
    }

    public Date getrCheckout() {
        return rCheckout;
    }

    public void setrCheckout(Date rCheckout) {
        this.rCheckout = rCheckout;
    }

    public int getHotelHtId() {
        return hotelHtId;
    }

    public void setHotelHtId(int hotelHtId) {
        this.hotelHtId = hotelHtId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomDto roomDto = (RoomDto) o;
        return rId == roomDto.rId && rPrice == roomDto.rPrice && rState == roomDto.rState && rCnt == roomDto.rCnt && hotelHtId == roomDto.hotelHtId && Objects.equals(rName, roomDto.rName) && Objects.equals(rImg, roomDto.rImg) && Objects.equals(rCheckin, roomDto.rCheckin) && Objects.equals(rCheckout, roomDto.rCheckout);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rId, rName, rPrice, rState, rCnt, rImg, rCheckin, rCheckout, hotelHtId);
    }

    @Override
    public String toString() {
        return "RoomDto{" +
                "rId=" + rId +
                ", rName='" + rName + '\'' +
                ", rPrice=" + rPrice +
                ", rState=" + rState +
                ", rCnt=" + rCnt +
                ", rImg='" + rImg + '\'' +
                ", rCheckin=" + rCheckin +
                ", rCheckout=" + rCheckout +
                ", hotelHtId=" + hotelHtId +
                '}';
    }
}
