package com.jspo;

import com.jspo.member.dto.MemberDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(MemberDto memberDto) {

        return "mainPage";
    }
}
