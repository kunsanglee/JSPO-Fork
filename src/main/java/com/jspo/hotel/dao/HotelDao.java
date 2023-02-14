package com.jspo.hotel.dao;

import com.jspo.hotel.dto.HotelDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HotelDao {

    // 호텔 추가
    void insertHotel(HotelDto hotelDto) throws Exception;

    List<HotelDto> selectHotel() throws Exception;

    HotelDto selectHotelByHtId(int htId);

       void updateHotel(HotelDto hotelDto) throws Exception;

       void deleteHotel(int htId) throws Exception;

       List<HotelDto> selectHotelByName(String htName);

}
