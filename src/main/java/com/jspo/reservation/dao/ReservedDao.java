package com.jspo.reservation.dao;

import com.jspo.reservation.dto.ReservationDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReservedDao {
    void insertReserved(ReservationDto reservationDto);
}
