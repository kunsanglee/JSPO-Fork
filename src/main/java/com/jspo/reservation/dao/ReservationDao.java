package com.jspo.reservation.dao;

import com.jspo.reservation.dto.ReservationDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReservationDao {
    void insertReservation(ReservationDto reservationDto);
    boolean deleteReservationByResId(int resId);
    boolean deleteReservationByMemberMId(int memberMId);
    ReservationDto selectReservationByResId(int resId);
    ReservationDto selectReservationByMemberMId(int memberMId);
}
