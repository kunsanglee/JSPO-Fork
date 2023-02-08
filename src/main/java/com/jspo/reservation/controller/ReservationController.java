package com.jspo.reservation.controller;

import com.jspo.hotel.dao.HotelDao;
import com.jspo.hotel.dto.HotelDto;
import com.jspo.member.dao.MemberDao;
import com.jspo.member.dto.MemberDto;
import com.jspo.reservation.dao.ReservationDao;
import com.jspo.reservation.dto.ReservationDto;
import com.jspo.room.dao.RoomDao;
import com.jspo.room.dto.RoomDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    private static ReservationDto newReservation;

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

        System.out.println("roomDto.getrCheckin() = " + roomDto.getrCheckin());
        reservationDto.setResPrice(roomDto.getrPrice());
        reservationDto.setMemberMId(memberDto.getId());

        long inTime = roomDto.getrCheckin().getTime();
        long outTime = roomDto.getrCheckout().getTime();
        long diff = (outTime - inTime)/1000/60/60/24;

        reservationDto.setRoomRCheckin(new Date(inTime+(1000*60*60*24)));
        reservationDto.setRoomRCheckout(new Date(outTime+(1000*60*60*24)));
        System.out.println("reservationDto.getRoomRCheckin() = " + reservationDto.getRoomRCheckin());
        reservationDto.setRoomHotelHtId(hotelHtId);
        reservationDto.setRoomRId(rId);

        Date now = new Date();
        reservationDto.setResDate(now);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String res = simpleDateFormat.format(now)+hotelHtId+rId+memberDto.getId();
        System.out.println("res = " + res);

        reservationDto.setResId(res);
        newReservation = reservationDto;

        System.out.println("reservationDto = " + reservationDto);

        System.out.println("POST reservation~~");


        m.addAttribute(hotelDto);
        m.addAttribute(roomDto);
        m.addAttribute(memberDto);
        m.addAttribute("diff", diff);
        m.addAttribute(reservationDto);


        return "reservation";
    }

    @PostMapping("/reservation/complete")
    @CrossOrigin(origins = "*")
    @ResponseBody
    public void complete(@RequestBody Map<String, String> data) {
        // imp_uid, merchant_uid, amount
        // 결제 성공 reservation DB에 정보 입력.
        System.out.println("complete~~");
        System.out.println(data);
        System.out.println("reservationDto = " + reservationDto);
        System.out.println("newReservation = " + newReservation);

//        int resId = Integer.parseInt(data.get("merchant_uid"));
//        reservationDto = reservationDao.selectReservationByResId(resId);
//        System.out.println("reservationDto = " + reservationDto);
        reservationDao.insertReservation(newReservation);
    }

    @PostMapping("/reservation/cancel")
    @CrossOrigin(origins = "*")
    @ResponseBody
    public void cancel(@RequestBody Map<String, String> data) {

        // 결제 실패 reservation DB에 만들어두었던 정보 삭제.
        System.out.println("cancel~~");
        System.out.println("data = " + data);
        System.out.println("reservationDto = " + reservationDto);
        System.out.println("newReservation = " + newReservation);
        if (data.isEmpty()) {
            return;
        } else {
            String resId = data.get("merchant_uid");
            System.out.println("reservationDao.deleteReservationByResId(resId) = " + reservationDao.deleteReservationByResId(resId));
        }


    }

    @PostMapping("/reservation/cancel/{resId}")
    @CrossOrigin
    @ResponseBody
    public boolean cancel(@RequestBody Map<String, String> data, @PathVariable String resId) {

        if (data.isEmpty()) return false;

        String merchant_uid = data.get("merchant_uid");
        String resPrice = data.get("cancel_request.amount");
        String reason = data.get("reason");
        String memberName = data.get("refund_holder");

        System.out.println("주문번호 : " + merchant_uid);
        System.out.println("예약금액 : " + resPrice);
        System.out.println("취소사유 : " + reason);
        System.out.println("회원명  : " + memberName);
        System.out.println("주문번호 일치여부 : " + resId.equals(merchant_uid));
        System.out.println("삭제처리 : " + reservationDao.deleteReservationByResId(resId));

        return true;
    }

    @GetMapping("/reserved")
    public String reserved(HttpSession session, Model m) throws Exception {
        if (session.getAttribute("email") == null) {
            return "login";
        }

        String email = (String) session.getAttribute("email");
        memberDto = memberDao.selectMemberByEmail(email);
        reservationDto = reservationDao.selectLastReservationById(memberDto.getId());
        hotelDto = hotelDao.selectHotelByHtId(reservationDto.getRoomHotelHtId());
        roomDto = roomDao.selectRoomByRId(reservationDto.getRoomRId());
        reservationDao.updateReservation();
        List<ReservationDto> reservation = reservationDao.selectAllReservationById(memberDto.getId());
        m.addAttribute("reservation", reservation);
        m.addAttribute(memberDto);
        m.addAttribute(hotelDto);
        m.addAttribute(roomDto);


        return "reserved";
    }
}
