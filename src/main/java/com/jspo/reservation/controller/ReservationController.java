package com.jspo.reservation.controller;

import com.jspo.reservation.dto.ReservationDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ReservationController {

    @GetMapping("/reservation")
    public String reserve(HttpSession session) {

        // 로그인필터 있어야되는데..
        if (session.getAttribute("email") == null) {
            return "redirect:/login";
        }

        return "reservation";
    }

    @PostMapping("/reservation")
    public String reserve(HttpSession session, ReservationDto reservationDto) {

        if (session.getAttribute("email") == null) {
            return "redirect:/login";
        }

        return "reserved";
    }
}
