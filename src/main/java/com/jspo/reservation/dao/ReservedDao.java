package com.jspo.reservation.dao;

import com.jspo.reservation.dto.ReservationDto;
import com.jspo.reservation.dto.ReservedDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReservedDao {
    void insertReserved(Map<String, Object> map);

    void deleteReserved(ReservationDto reservationDto);

    List<ReservedDto> selectReservedAll();
}
