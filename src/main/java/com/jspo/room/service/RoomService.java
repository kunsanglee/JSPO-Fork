package com.jspo.room.service;

import com.jspo.reservation.dao.ReservationDao;
import com.jspo.reservation.dao.ReservedDao;
import com.jspo.reservation.dto.ReservationDto;
import com.jspo.reservation.dto.ReservedDto;
import com.jspo.room.dao.RoomDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    @Autowired
    ReservedDao reservedDao;
    @Autowired
    ReservationDao reservationDao;
    @Autowired
    RoomDao roomDao;

    private ReservationDto reservationDto = ReservationDto.getInstance();
    private ReservedDto reservedDto = ReservedDto.getInstance();

    // 모든 예약내역을 가져와서
//    public void updateRoomCnt() {
//        List<ReservedDto> reservedDtoList = reservedDao.selectReservedAll();
//        for (ReservedDto list : reservedDtoList) {
//            roomDao.roomCntUp(list);
//            roomDao.roomCntDown(list);
//        }
//    }
}
