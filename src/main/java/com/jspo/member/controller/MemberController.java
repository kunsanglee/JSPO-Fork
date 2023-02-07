package com.jspo.member.controller;


import com.jspo.member.dao.MemberDao;
import com.jspo.member.dto.MemberDto;
import com.jspo.member.service.MemberService;
import com.jspo.reservation.dao.ReservationDao;
import com.jspo.reservation.dto.ReservationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MemberController {

    @Autowired
    private MemberDao memberDao;
    @Autowired
    private MemberService memberService;

    @Autowired
    private ReservationDao reservationDao;

    private MemberDto memberDto = MemberDto.getInstance();

    private ReservationDto reservationDto = ReservationDto.getInstance();

    @GetMapping("/my")
    public String myPage(HttpSession session, Model m) throws Exception {
        if (session.getAttribute("email") == null) {
            return "redirect:/login";
        }

        String email = (String) session.getAttribute("email");
        memberDto = memberDao.selectMemberByEmail(email);
        m.addAttribute("memberDto", memberDto);
        m.addAttribute("encPwd", memberService.getEncPwd(memberDto));
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
        System.out.println("email = " + email);
        if (memberDao.emailCheck(email) == null) {
            return 0;
        }
        return memberDao.emailCheck(email);
    }

    @PostMapping("/phoneCheck")
    @ResponseBody
    public int memberPhoneCount(String phone) throws Exception {
        System.out.println("phone = " + phone);
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

        if (DBPwd.equals(pwd)) {
            Map<String, String> map = new HashMap<>();
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

    @GetMapping("/reserved")
    public String reserved(HttpSession session, Model m) throws Exception {
        if (session.getAttribute("email") == null) {
            return "login";
        }

        String email = (String) session.getAttribute("email");
        memberDto = memberDao.selectMemberByEmail(email);
        List<ReservationDto> reservation = reservationDao.selectAllReservationById(memberDto.getId());
        m.addAttribute("reservation", reservation);

        return "reserved";
    }
}