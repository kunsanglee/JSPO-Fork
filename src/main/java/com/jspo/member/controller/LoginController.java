package com.jspo.member.controller;

import com.jspo.member.dao.MemberDao;
import com.jspo.member.dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private MemberDao memberDao;

    private MemberDto memberDto = MemberDto.getInstance();

    @GetMapping("/login")
    public String login() {

        return "login";
    }

    @PostMapping("/login")
    public String login(MemberDto loginMember , Model m) throws Exception {

        // 로그인 페이지에 적은 이메일과 패스워드를 memberDto에 set한다.
         memberDto.setEmail(loginMember.getEmail());
         memberDto.setPwd(loginMember.getPwd());

         // memberDto 엔 email값과 pwd 값이 있으며
        // 그 값을 sql문의 login의 조건으로 select 한다.
        MemberDto result = memberDao.login(memberDto);
        if(result != null) {
            // 결과값이 널이 아니면 즉 select가 정상적으로 되었음
            // 메인 홈으로 가기
            m.addAttribute("memberDto", result);
            return "myPage";
        } else {
            return "/login";
        }

//        둘다 데이터가 없을시
//        "아이디를 입력해주세요"
//
//        아이디만 있을시(틀려도)
//                "비밀번호를 입력해주세요"
//
//        비밀번호 잘못적었을시
//        "아이디 또는 비밀번호를 잘못 입력했습니다.
//        입력하신 내용을 다시 확인해주세요"
//
//        비밀번호는 초기화 되고
//        아이디쪽만 데이터가 남아있음

    }
}
