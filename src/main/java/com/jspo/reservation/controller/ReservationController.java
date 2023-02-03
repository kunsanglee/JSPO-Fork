package com.jspo.reservation.controller;

import com.jspo.hotel.dao.HotelDao;
import com.jspo.hotel.dto.HotelDto;
import com.jspo.member.dao.MemberDao;
import com.jspo.member.dto.MemberDto;
import com.jspo.reservation.dto.ReservationDto;
import com.jspo.room.dao.RoomDao;
import com.jspo.room.dto.RoomDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ReservationController {

    @Autowired
    private HotelDao hotelDao;
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private RoomDao roomDao;

    private HotelDto hotelDto = HotelDto.getInstance();
    private MemberDto memberDto = MemberDto.getInstance();
    private RoomDto roomDto = RoomDto.getInstance();

    @GetMapping("/reservation")
    public String reserve(HttpSession session, Model m) throws Exception {

        // 로그인필터 있어야되는데..
        if (session.getAttribute("email") == null) {
            return "redirect:/login";
        }

        hotelDto = hotelDao.selectHotelByHtId(1);
        memberDto = memberDao.selectMemberByEmail("admin@jspo.com");
        roomDto = roomDao.selectRoomByRId(4);
        m.addAttribute(hotelDto);
        m.addAttribute(memberDto);
        m.addAttribute(roomDto);

        return "reservation";
    }

    @PostMapping("/reservation")
    public String reserve(HttpSession session, ReservationDto reservationDto) {

        if (session.getAttribute("email") == null) {
            return "redirect:/login";
        }

        return "reserved";
    }
}
