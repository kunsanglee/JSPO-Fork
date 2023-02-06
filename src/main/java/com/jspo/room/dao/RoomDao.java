package com.jspo.room.dao;

import com.jspo.room.dto.RoomDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoomDao {
    void insertRoom(RoomDto roomDto);

    RoomDto selectRoomByRId(int rId);
    List<RoomDto> selectRoom() throws Exception;

    Integer selectPrice(int hotelHtId);

    List<RoomDto> selectRoomByhtId(int htId);
}
