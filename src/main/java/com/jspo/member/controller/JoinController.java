package com.jspo.member.controller;

import com.jspo.member.dao.MemberDao;
import com.jspo.member.dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class JoinController {

    @Autowired
    private MemberDao memberDao;

    private MemberDto memberDto = MemberDto.getInstance();


    @GetMapping("/join")
    public String join() {

        return "regForm";
    }

    @PostMapping("/join")
    public String join(@Valid MemberDto joinMember, BindingResult bindingResult, Model m) throws Exception {

        memberDto.setEmail(joinMember.getEmail());

        if (memberDao.selectMember(memberDto) != null) {
            System.out.println("이메일이 중복되었습니다.");
            return "regForm";
        }

        memberDto.setPwd(joinMember.getPwd());
        memberDto.setName(joinMember.getName());
        memberDto.setBirth(joinMember.getBirth());
        memberDto.setPhone(joinMember.getPhone());

        m.addAttribute(memberDto);

        return "test";
    }
}
