package com.jspo.room.dao;

import com.jspo.hotel.dto.HotelDto;
import com.jspo.reservation.dto.ReservedDto;
import com.jspo.room.dto.RoomDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface RoomDao {
    void insertRoom(RoomDto roomDto);

    RoomDto selectRoomByRId(int rId);

    List<RoomDto> selectRoom() throws Exception;

    Integer selectPrice(int hotelHtId);

    List<RoomDto> selectRoomByhtId(int htId);

    HotelDto selectRoomByinfo(int htId);

    void updateRoom(RoomDto roomDto);

    List<RoomDto> selectRoomByInOut(Map map);
//    void roomCntUp(ReservedDto reservedDto);
//    void roomCntDown(ReservedDto reservedDto);

    void deleteRoom(int rId);

    List<HotelDto> selectHotelBetween(int left, int right);
}
