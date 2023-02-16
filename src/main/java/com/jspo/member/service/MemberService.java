package com.jspo.member.service;

import com.jspo.member.dto.MemberDto;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    public static String getEncPwd(MemberDto memberDto) {
        // 회원정보페이지로 보내는 암호화 비밀번호
        String encPwd = "";
        // 회원의 비밀번호 길이만큼 ●●●● 처리
        for (int i = 0; i < memberDto.getPwd().length(); i++) {
            encPwd += "●";
        }
        return encPwd;
    }

}
