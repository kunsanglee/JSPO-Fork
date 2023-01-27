package com.jspo.member.controller;

import com.jspo.member.dao.MemberDao;
import com.jspo.member.dto.MemberDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private MemberDao memberDao;

    private MemberDto memberDto = MemberDto.getInstance();

    @GetMapping("/")
    public String login() {

        return "login";
    }

    @PostMapping("/login")
    public String login(MemberDto loginMember, Model m) throws Exception {
        System.out.println(memberDto);
         memberDto.setEmail(loginMember.getEmail());
         memberDto.setPwd(loginMember.getPwd());
        System.out.println(memberDto);

        MemberDto result = memberDao.login(memberDto);
        System.out.println("result "+result); // ServiceImpl

        System.out.println(loginMember.getEmail());
        System.out.println(loginMember.getPwd());

        m.addAttribute(memberDto);


        // 로그인 (Post)를 눌렀을때
        // DB에 아이디와 비밀번호를 비교한 값이 있을때 test페이지로 가겠끔
        // 모델에 담아가지고 DB랑 비교?

        // 로그인 성공시


        // 로그인 실패시
        return "test";
    }
}
