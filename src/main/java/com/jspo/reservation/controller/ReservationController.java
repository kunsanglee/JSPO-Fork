package com.jspo.reservation.controller;

import com.jspo.hotel.dao.HotelDao;
import com.jspo.hotel.dto.HotelDto;
import com.jspo.member.dao.MemberDao;
import com.jspo.member.dto.MemberDto;
import com.jspo.reservation.dao.ReservationDao;
import com.jspo.reservation.dto.ReservationDto;
import com.jspo.room.dao.RoomDao;
import com.jspo.room.dto.RoomDto;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Controller
public class ReservationController {

    @Autowired
    private HotelDao hotelDao;
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private RoomDao roomDao;
    @Autowired
    private ReservationDao reservationDao;

    private HotelDto hotelDto = HotelDto.getInstance();
    private RoomDto roomDto = RoomDto.getInstance();
    private MemberDto memberDto = MemberDto.getInstance();
    private ReservationDto reservationDto = ReservationDto.getInstance();

    @PostMapping("/reservation")
    public String reserve(HttpSession session, Model m, int hotelHtId, int rId) throws Exception {

        // 로그인필터 있어야되는데..
        if (session.getAttribute("email") == null) {
            return "redirect:/login";
        }

        System.out.println("hotelHtId = " + hotelHtId);
        System.out.println("rId = " + rId);

        hotelDto = hotelDao.selectHotelByHtId(hotelHtId);
        memberDto = memberDao.selectMemberByEmail((String) session.getAttribute("email"));
        roomDto = roomDao.selectRoomByRId(rId);
        reservationDto.setResPrice(roomDto.getrPrice());
        reservationDto.setMemberMId(memberDto.getId());
        reservationDto.setRoomHotelHtId(hotelHtId);
        reservationDto.setRoomRId(rId);
        reservationDao.insertReservation(reservationDto);

        Integer diff = roomDao.diff(roomDto); // selectOne 나와야하는데 여러개나와서 에러

        reservationDto = reservationDao.selectLastReservation(memberDto.getId());
        System.out.println("reservationDto = " + reservationDto);

        System.out.println("POST reservation~~");


        m.addAttribute(hotelDto);
        m.addAttribute(roomDto);
        m.addAttribute(memberDto);
        m.addAttribute("diff", diff);
        m.addAttribute(reservationDto);


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
    public void complete(@RequestBody Map<String, String> data) {
        // imp_uid, merchant_uid, amount
        // 결제 성공 reservation DB에 정보 입력.
        System.out.println("complete~~");
        System.out.println(data);
        System.out.println("reservationDto = " + reservationDto);

        int resId = Integer.parseInt(data.get("merchant_uid"));
        reservationDto = reservationDao.selectReservationByResId(resId);
        System.out.println("reservationDto = " + reservationDto);
    }

    @PostMapping("/reservation/cancel")
    @CrossOrigin(origins = "*")
    @ResponseBody
    public void cancel(@RequestBody Map<String, String> data) {

        // 결제 실패 reservation DB에 만들어두었던 정보 삭제.
        System.out.println("cancel~~");
        System.out.println(data);
        System.out.println("reservationDto = " + reservationDto);

        int resId = Integer.parseInt(data.get("merchant_uid"));
        System.out.println("reservationDao.deleteReservationByResId(resId) = " + reservationDao.deleteReservationByResId(resId));

    }
}
