package com.jspo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    // 메인페이지 생성되면 바꿔줘야함(01/26)
    @GetMapping("/")
    public String home() {
        return "login";
    }

    @GetMapping("/join")
    public String join() { return "regForm"; }
}
