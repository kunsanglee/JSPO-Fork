package com.jspo.member.controller;


import com.jspo.email.EmailService;
import com.jspo.hotel.dao.HotelDao;
import com.jspo.hotel.dto.HotelDto;
import com.jspo.member.dao.MemberDao;
import com.jspo.member.dto.MemberDto;
import com.jspo.reservation.dao.ReservationDao;
import com.jspo.reservation.dto.ReservationDto;
import com.jspo.room.dao.RoomDao;
import com.jspo.room.dto.RoomDto;
import com.jspo.sms.Naver_Sens_V2_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.commons.lang3.RandomStringUtils;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class MemberController {

    static String auth;

    public MemberController(EmailService emailService) {
        this.emailService = emailService;
    }
    @Autowired
    private final EmailService emailService;

    @Autowired
    private Naver_Sens_V2_Service naver_sens_v2_service;

    static String code;

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private HotelDao hotelDao;

    @Autowired
    private RoomDao roomDao;

    @Autowired
    private ReservationDao reservationDao;

    private MemberDto memberDto = MemberDto.getInstance();

    private ReservationDto reservationDto = ReservationDto.getInstance();

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/my")
    public String myPage(HttpSession session, Model m) throws Exception {
        if (session.getAttribute("email") == null) {
            return "login";
        }

        String email = (String) session.getAttribute("email");
        memberDto = memberDao.selectMemberByEmail(email);

        try {

            List<ReservationDto> reservation = reservationDao.selectAllReservationById(memberDto.getId());
            List<HotelDto> hotelDto = new ArrayList<>();
            List<RoomDto> roomDto = new ArrayList<>();

            for (ReservationDto reservationDto : reservation) {
                hotelDto.add(hotelDao.selectHotelByHtId(reservationDto.getRoomHotelHtId()));
                roomDto.add(roomDao.selectRoomByRId(reservationDto.getRoomRId()));
            }
            m.addAttribute("reservation", reservation);
            m.addAttribute(memberDto);
            m.addAttribute("hotelDto", hotelDto);
            m.addAttribute("roomDto", roomDto);

        } catch (Exception e) {

        }
        return "myPage";
    }


    @PostMapping("/modifyPhone")
    @ResponseBody
    public boolean modifyPhone(String phone, HttpSession session) throws Exception {

        String email = (String) session.getAttribute("email");

        Map<String, String> map = new HashMap<>();
        map.put("phone", phone);
        map.put("email", email);

        memberDao.updatePhone(map);

        return true;
    }

    @PostMapping("/emailCheck")
    @ResponseBody
    public int emailCheck(String email) throws Exception {
        if (memberDao.emailCheck(email) == null) {
            return 0;
        }
        return memberDao.emailCheck(email);
    }

    @PostMapping("/phoneCheck")
    @ResponseBody
    public int memberPhoneCount(String phone) throws Exception {
        if (memberDao.memberPhoneCount(phone) == null) {
            return 0;
        }
        return memberDao.memberPhoneCount(phone);
    }

    @PostMapping("/modifyPwd")
    @ResponseBody
    public boolean modifyPwd(String pwd, String chgPwd, HttpSession session) throws Exception {

        if (null == pwd || null == chgPwd) return false;

        String email = (String) session.getAttribute("email");
        String DBPwd = memberDao.selectMemberByEmail(email).getPwd();

        if (passwordEncoder.matches(pwd, DBPwd)) {
            Map<String, String> map = new HashMap<>();
            chgPwd = passwordEncoder.encode(chgPwd);
            map.put("pwd", chgPwd);
            map.put("email", email);
            memberDao.updatePwd(map);
            return true;
        }

        return false;
    }

    @PostMapping("/secession")
    @ResponseBody
    public boolean secession(String pwd, HttpSession session) throws Exception {
        if (null == pwd) return false;
        String email = (String) session.getAttribute("email");
        String DBPwd = memberDao.selectMemberByEmail(email).getPwd();

        if (DBPwd.equals(pwd)) {
            memberDao.deleteMember(email);
            session.invalidate();
            return true;
        }

        return false;
    }

    @PostMapping("/my/emailAuth")
    @ResponseBody
    public boolean findEmail(String phone) throws Exception {
        memberDto = memberDao.selectMemberByPhone(phone);
        if (memberDto != null) {
            if (memberDto.getPhone().equals(phone)) {
                // phone 번호로 인증문자 보내기
                System.out.println("phone = " + phone);
                code = naver_sens_v2_service.sendRandomMessage(phone);
                return true;
            }
        }
        return false;
    }

    @PostMapping("/my/checkEmailInput")
    @ResponseBody
    public String phoneAuth(String phone, String input) throws Exception {
        System.out.println("input = " + input);

        if (!input.equals("") && code.equals(input)) {
            memberDto = memberDao.selectMemberByPhone(phone);
            String result = memberDto.getEmail();
            System.out.println("result = " + result);
            return result;
        }

        throw new Exception("인증번호 불일치 에러");
    }

    @PostMapping("/my/PwdAuth")
    @ResponseBody
    public boolean mailConfirm(String email) throws Exception {
        if (email != null && memberDao.selectMemberByEmail(email) != null) {
            String code = emailService.sendSimpleMessage(email);
            System.out.println("인증코드 : " + code);
            auth = code;
            return true;
        }
        return false;
    }

    // 인증번호 확인시 사용자가 입력한 값과 서버에 저장된 인증번호가 일치하는지 확인하여 true false 반환.
    @PostMapping("/my/checkPwdInput")
    @ResponseBody
    public boolean authConfirm(String email, String input) throws Exception {
        if (auth.equals(input)) {
            // 인증번호가 일치하면 회원의 비밀번호를 새로 초기화하여 메일로 보내줌.
            String random = getRamdomPassword();
            System.out.println("회원 이메일 = " + email);
            System.out.println("랜덤 비밀번호 = " + random);
            String result = passwordEncoder.encode(random);
            Map<String, String> map = new HashMap<>();
            map.put("pwd", result);
            map.put("email", email);
            System.out.println("변경전 : "+memberDao.selectMemberByEmail(email).getPwd());
            memberDao.updatePwd(map);
            System.out.println("변경후 : "+memberDao.selectMemberByEmail(email).getPwd());
            System.out.println("memberDto.getPwd() = " + memberDao.selectMemberByEmail(email).getPwd());
            emailService.sendPwd(email, result);
            return true;
        }
        return false;
    }

    public String getRamdomPassword() {
        String random = RandomStringUtils.randomAlphanumeric(8);
        return random;
    }
}