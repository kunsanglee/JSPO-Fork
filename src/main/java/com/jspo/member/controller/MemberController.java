package com.jspo.member.controller;


import com.jspo.member.dao.MemberDao;
import com.jspo.member.dto.MemberDto;
import com.jspo.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MemberController {

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private MemberService memberService;

    private MemberDto memberDto = MemberDto.getInstance();

    @GetMapping("/my")
    public String myPage(HttpSession session, Model m) throws Exception {
        String email = (String) session.getAttribute("email");
        memberDto = memberDao.selectMemberByEmail(email);
        m.addAttribute("memberDto", memberDto);
        m.addAttribute("encPwd", memberService.getEncPwd(memberDto));
        return "myPage";
    }


    @PostMapping("/modifyPhone")
    public boolean modifyPhone(String pwd, HttpSession session) throws Exception {

        String email = (String) session.getAttribute("email");
        memberDto = memberDao.selectMemberByEmail(email);

        // 세션에서 얻어온 아이디로 DB조회해서 나온 회원정보와 입력받은 pwd가 일치하지 않으면
        if (!memberDto.getPwd().equals(pwd)) {
            return false;
        }

        Map<String, String> map = new HashMap<>();

        map.put("pwd", pwd);
        map.put("email", email);

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
    public boolean modifyPwd(String pwd, String chgPwd, HttpSession session, Model m) throws Exception {
        System.out.println("pwd = " + pwd);
        System.out.println("chgPwd = " + chgPwd);

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


}