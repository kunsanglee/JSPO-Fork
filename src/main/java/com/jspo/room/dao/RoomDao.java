package com.jspo.room.dao;

import com.jspo.room.dto.RoomDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoomDao {
    RoomDto insertRoom(RoomDto roomDto);

    RoomDto selectRoomByRId(int rId);
}
