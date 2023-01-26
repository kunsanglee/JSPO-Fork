package com.jspo.member.controller;

import com.jspo.member.dto.MemberDto;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

@Controller
public class JoinController {

    public String join(@Valid MemberDto memberDto) {

        return "join";
    }
}
