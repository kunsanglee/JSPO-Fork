package com.jspo.reservation.dto;

import com.jspo.room.dto.RoomDto;

import java.util.Date;
import java.util.Objects;

public class ReservationDto {
    private String resId;
    private int resPrice;
    private Date resDate;

    private int resState;
    private int memberMId;
    private Date roomRCheckin;
    private Date roomRCheckout;
    private int roomRId;
    private int roomHotelHtId;

    public ReservationDto() {}

    public ReservationDto(String resId, int resPrice, Date resDate, int resState, int memberMId, Date roomRCheckin, Date roomRCheckout, int roomRId, int roomHotelHtId) {
        this.resId = resId;
        this.resPrice = resPrice;
        this.resDate = resDate;
        this.resState = resState;
        this.memberMId = memberMId;
        this.roomRCheckin = roomRCheckin;
        this.roomRCheckout = roomRCheckout;
        this.roomRId = roomRId;
        this.roomHotelHtId = roomHotelHtId;
    }

    private static final ReservationDto instance = new ReservationDto();

    public static ReservationDto getInstance() { return instance; }

    private static final RoomDto instnace = new RoomDto();

    public static RoomDto getInstnace() { return instnace; }

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId;
    }

    public int getResPrice() {
        return resPrice;
    }

    public void setResPrice(int resPrice) {
        this.resPrice = resPrice;
    }

    public Date getResDate() {
        return resDate;
    }

    public void setResDate(Date resDate) {
        this.resDate = resDate;
    }

    public int getResState() {
        return resState;
    }

    public void setResState(int resState) {
        this.resState = resState;
    }

    public int getMemberMId() {
        return memberMId;
    }

    public void setMemberMId(int memberMId) {
        this.memberMId = memberMId;
    }

    public Date getRoomRCheckin() {
        return roomRCheckin;
    }

    public void setRoomRCheckin(Date roomRCheckin) {
        this.roomRCheckin = roomRCheckin;
    }

    public Date getRoomRCheckout() {
        return roomRCheckout;
    }

    public void setRoomRCheckout(Date roomRCheckout) {
        this.roomRCheckout = roomRCheckout;
    }

    public int getRoomRId() {
        return roomRId;
    }

    public void setRoomRId(int roomRId) {
        this.roomRId = roomRId;
    }

    public int getRoomHotelHtId() {
        return roomHotelHtId;
    }

    public void setRoomHotelHtId(int roomHotelHtId) {
        this.roomHotelHtId = roomHotelHtId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationDto that = (ReservationDto) o;
        return resPrice == that.resPrice && resState == that.resState && memberMId == that.memberMId && roomRId == that.roomRId && roomHotelHtId == that.roomHotelHtId && Objects.equals(resId, that.resId) && Objects.equals(resDate, that.resDate) && Objects.equals(roomRCheckin, that.roomRCheckin) && Objects.equals(roomRCheckout, that.roomRCheckout);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resId, resPrice, resDate, resState, memberMId, roomRCheckin, roomRCheckout, roomRId, roomHotelHtId);
    }

    @Override
    public String toString() {
        return "ReservationDto{" +
                "resId='" + resId + '\'' +
                ", resPrice=" + resPrice +
                ", resDate=" + resDate +
                ", resState=" + resState +
                ", memberMId=" + memberMId +
                ", roomRCheckin=" + roomRCheckin +
                ", roomRCheckout=" + roomRCheckout +
                ", roomRId=" + roomRId +
                ", roomHotelHtId=" + roomHotelHtId +
                '}';
    }
}
