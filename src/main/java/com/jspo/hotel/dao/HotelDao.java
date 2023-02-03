package com.jspo.hotel.dao;

import com.jspo.hotel.dto.HotelDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HotelDao {

    // 호텔 추가
    void insertHotel(HotelDto hotelDto) throws Exception;
}
