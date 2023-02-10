package com.jspo.member.controller;

import com.jspo.member.dao.MemberDao;
import com.jspo.member.dto.MemberDto;
import com.jspo.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
    public String login(@CookieValue (value = "email", required = false) String email, Model m,HttpServletRequest request) {

        m.addAttribute("email", email);
        String referer = request.getHeader("referer");
        System.out.println(referer);
        m.addAttribute("referer",referer);
        return "login";
    }

    @PostMapping("/login")
    public String login(MemberDto loginMember , Model m, HttpServletRequest request,
                        HttpServletResponse response, boolean remember,@RequestParam String referer) throws Exception {

        String result = referer.substring(22); // referer가 헤더를 다가져오기때문에

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

            m.addAttribute("memberDto", memberDto);
            m.addAttribute("encPwd", memberService.getEncPwd(memberDto));

            return "redirect:/" + result;
        }
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {

        HttpSession session = request.getSession();
        session.invalidate();

        return "redirect:/";
    }
}