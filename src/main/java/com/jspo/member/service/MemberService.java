package com.jspo.member.service;

import com.jspo.member.dao.MemberDao;
import com.jspo.member.dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Service
public class MemberService {

    @Autowired
    private MemberDao memberDao;

    private MemberDto memberDto = MemberDto.getInstance();

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
}
