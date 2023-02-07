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
<<<<<<< HEAD
import org.springframework.web.bind.annotation.*;
=======
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
>>>>>>> 9ca0f1babb4a9514893042ce6cdbedec2f6b54f6

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
    private RoomDto roomDto = RoomDto.getInstance();
    private MemberDto memberDto = MemberDto.getInstance();

    @PostMapping("/reservation")

    public String reserve(HttpSession session, Model m,int hotelHtId, int rId) throws Exception {

        // 로그인필터 있어야되는데..
        if (session.getAttribute("email") == null) {
            return "redirect:/login";
        }

        hotelDto = hotelDao.selectHotelByHtId(hotelHtId);
        memberDto = memberDao.selectMemberByEmail((String) session.getAttribute("email"));
        roomDto = roomDao.selectRoomByRId(rId);
        Integer diff = roomDao.diff(roomDto);

        m.addAttribute(hotelDto);
        m.addAttribute(roomDto);
        m.addAttribute(memberDto);
        m.addAttribute("diff", diff);

        return "reservation";
    }

//    @PostMapping("/reservation")
//    public String reserve(HttpSession session, ReservationDto reservationDto) {
//
//        if (session.getAttribute("email") == null) {
//            return "redirect:/login";
//        }
//
//        return "reserved";
//    }

    @PostMapping("/reservation/complete")
    @CrossOrigin(origins = "*")
    @ResponseBody
    public void complete(@RequestBody String data) {

        System.out.println(data);

    }
}
