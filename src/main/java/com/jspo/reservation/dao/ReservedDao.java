package com.jspo.reservation.dao;

import com.jspo.reservation.dto.ReservationDto;
import com.jspo.reservation.dto.ReservedDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReservedDao {
    void insertReserved(ReservationDto reservationDto);

    void deleteReserved(ReservationDto reservationDto);

    List<ReservedDto> selectReservedAll();
}
