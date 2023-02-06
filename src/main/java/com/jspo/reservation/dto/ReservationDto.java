package com.jspo.reservation.dto;

import com.jspo.room.dto.RoomDto;

import java.sql.Date;
import java.util.Objects;

public class ReservationDto {
    private int resId;
    private int resPrice;
    private Date resDate;
    private int memberMId;
    private int roomRId;
    private int roomHotelHtId;

    public ReservationDto() {}

    public ReservationDto(int resId, int resPrice, Date resDate, int memberMId, int roomRId, int roomHotelHtId) {
        this.resId = resId;
        this.resPrice = resPrice;
        this.resDate = resDate;
        this.memberMId = memberMId;
        this.roomRId = roomRId;
        this.roomHotelHtId = roomHotelHtId;
    }

    private static final ReservationDto instance = new ReservationDto();

    public static ReservationDto getInstance() { return instance; }

    private static final RoomDto instnace = new RoomDto();

    public static RoomDto getInstnace() { return instnace; }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
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

    public int getMemberMId() {
        return memberMId;
    }

    public void setMemberMId(int memberMId) {
        this.memberMId = memberMId;
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
        return resId == that.resId && resPrice == that.resPrice && memberMId == that.memberMId && roomRId == that.roomRId && roomHotelHtId == that.roomHotelHtId && Objects.equals(resDate, that.resDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resId, resPrice, resDate, memberMId, roomRId, roomHotelHtId);
    }

    @Override
    public String toString() {
        return "ReservationDto{" +
                "resId=" + resId +
                ", resPrice=" + resPrice +
                ", resDate=" + resDate +
                ", memberMId=" + memberMId +
                ", roomRId=" + roomRId +
                ", roomHotelHtId=" + roomHotelHtId +
                '}';
    }
}
