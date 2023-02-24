package com.jspo.member.controller;

import com.jspo.member.dao.MemberDao;
import com.jspo.member.dto.MemberDto;
import com.jspo.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

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

       String idError = (String) m.getAttribute("iderror");
       String pwdError = (String) m.getAttribute("pwderror");
       String totalError = (String) m.getAttribute("totalerror");

        if (request.getHeader("referer").equals("http://localhost:8080/login?logout")) {
            referer = "";
        } else {
            referer = request.getHeader("referer");
        }

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
    public String login(@Valid MemberDto loginMember, BindingResult bindingResult, Model m, HttpServletRequest request, HttpServletResponse response, boolean remember, RedirectAttributes redirectAttributes) throws Exception {

        try {
            if (loginMember.getEmail() != null && loginMember.getPwd() != null) {  // 이메일과 패스워드를 적고 로그인 클릭하면
                memberDto = memberDao.selectMemberByEmail(loginMember.getEmail()); // 이메일로 DB에 조회 해본다
                boolean matches = passwordEncoder.matches(loginMember.getPwd(), memberDto.getPwd()); // DB에서 조회된 아이디의 암호화된 패스워드와 로그인창에 적힌 패스워드가 같은 지 비교

                if (matches) { // 같다면 세션추가 및 쿠키추가
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
        } catch (Exception e) {
//            if(bindingResult.hasErrors()) {
//                Map<String, String> errorMap = new HashMap<>();
//
//                for(FieldError error : bindingResult.getFieldErrors()) {
//                    System.out.println("에러필드= " + error.getField());
//                    System.out.println("에러메시지= " + error.getDefaultMessage());
//                    errorMap.put("valid_"+error.getField(), error.getDefaultMessage());
//                }
//            }
            if("".equals(loginMember.getEmail()) && "".equals(loginMember.getPwd())) {
                // 이메일을 입력해주세요, 비밀번호를 입력해주세요
                redirectAttributes.addFlashAttribute("iderror","이메일을 입력해주세요");
                redirectAttributes.addFlashAttribute("pwderror","비밀번호를 입력해주세요");
            } else if("".equals(loginMember.getEmail()) && !("".equals(loginMember.getPwd()))) {
                // 이메일을 입력해주세요
                redirectAttributes.addFlashAttribute("iderror","이메일을 입력해주세요");
            } else if(!("".equals(loginMember.getEmail())) && "".equals(loginMember.getPwd())) {
                // 비밀번호를 입력해주세요
                redirectAttributes.addFlashAttribute("pwderror","비밀번호를 입력해주세요");
                redirectAttributes.addFlashAttribute("preemail",loginMember.getEmail());
            } else {
                redirectAttributes.addFlashAttribute("totalerror","이메일 또는 비밀번호를 다시 확인하세요");//
            }
            return "redirect:/login";
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