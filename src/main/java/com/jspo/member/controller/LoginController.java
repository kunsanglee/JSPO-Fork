package com.jspo.member.controller;

import com.jspo.member.dao.MemberDao;
import com.jspo.member.dto.MemberDto;
import com.jspo.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static String referer = "";

    @GetMapping("/login")
    public String login(@CookieValue(value = "email", required = false) String email, Model m, HttpServletRequest request) {
        if (request.getHeader("referer").equals("http://localhost:8080/login?logout")) {
            referer = "";
        } else {
            referer = request.getHeader("referer");
        }

        System.out.println("get login = " + request.getHeader("referer"));

        HttpSession session = request.getSession();
        if (session.getAttribute("email") != null) {
            if (("http://localhost:8080/login").equals(referer)) {
                return "mainPage";
            }
            return "redirect:"+referer;
        }

        m.addAttribute("email", email);


        return "login";
    }

    @PostMapping("/login")
    public String login(MemberDto loginMember, Model m, HttpServletRequest request, HttpServletResponse response, boolean remember) throws Exception {
        System.out.println("post login = " + request.getHeader("referer"));

        if (loginMember.getEmail() != null && loginMember.getPwd() != null) {
            memberDto = memberDao.selectMemberByEmail(loginMember.getEmail());
            boolean matches = passwordEncoder.matches(loginMember.getPwd(), memberDto.getPwd());

            if (matches) {
                HttpSession session = request.getSession();
                Cookie cookie;

                if (remember) {
                    cookie = new Cookie("email", memberDto.getEmail());
                    cookie.setMaxAge(60 * 60 * 24);
                } else {
                    cookie = new Cookie("email", null);
                    cookie.setMaxAge(0);
                }

                response.addCookie(cookie);
                session.setAttribute("email", memberDto.getEmail());
                m.addAttribute("memberDto", memberDto);
                m.addAttribute("encPwd", memberService.getEncPwd(memberDto));
                return "redirect:" +referer;
            }
        }
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {

        HttpSession session = request.getSession();
        session.invalidate();

        return "redirect:/";
    }
}