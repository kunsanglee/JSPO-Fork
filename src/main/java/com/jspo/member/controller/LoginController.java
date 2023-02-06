package com.jspo.member.controller;

import com.jspo.member.dao.MemberDao;
import com.jspo.member.dto.MemberDto;
import com.jspo.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.net.http.HttpRequest;

@Controller
public class LoginController {

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private MemberService memberService;

    private MemberDto memberDto = MemberDto.getInstance();

    @GetMapping("/login")
    public String login(@CookieValue (value = "email", required = false) String email, Model m) {

        m.addAttribute("email", email);

        return "login";
    }

    @PostMapping("/login")
    public String login(MemberDto loginMember , Model m, HttpServletRequest request,
                        HttpServletResponse response, boolean remember) throws Exception {

        memberDto = memberDao.login(loginMember);
        HttpSession session = request.getSession();
        if(memberDto != null ) {
            Cookie cookie;
            if(remember) {
                cookie = new Cookie("email", memberDto.getEmail());
                cookie.setMaxAge(60*60*24);
            } else {
                cookie = new Cookie("email", null);
                cookie.setMaxAge(0);
            }
            response.addCookie(cookie);

            session.setAttribute("email",memberDto.getEmail());
//            String referer = request.getHeader("Referer");
//            System.out.println(referer) +referer;

            m.addAttribute("memberDto", memberDto);
            m.addAttribute("encPwd", memberService.getEncPwd(memberDto));

            return "redirect:/my";
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
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {

        HttpSession session = request.getSession();
        session.invalidate();

        return "redirect:/";
    }
}