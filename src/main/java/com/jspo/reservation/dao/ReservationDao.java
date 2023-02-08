package com.jspo.reservation.dao;

import com.jspo.reservation.dto.ReservationDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReservationDao {
    void insertReservation(ReservationDto reservationDto);
    boolean deleteReservationByResId(String resId);
    boolean deleteReservationByMemberMId(int memberMId);
    ReservationDto selectReservationByResId(int resId);
    ReservationDto selectReservationByMemberMId(int memberMId);
    List<ReservationDto> selectAllReservationById(int memberMid);
    ReservationDto selectLastReservationById(int memberMid);
    ReservationDto selectLastReservation();

    void updateReservation();

    Integer getAutoincrement(ReservationDto reservationDto);
}
