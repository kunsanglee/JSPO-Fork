package com.jspo;

import com.jspo.hotel.dao.HotelDao;
import com.jspo.hotel.dto.HotelDto;
import com.jspo.member.dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private HotelDto hotelDto = HotelDto.getInstance();

    @Autowired
    private HotelDao hotelDao;

    @GetMapping("/")
    public String home(MemberDto memberDto, Model m) throws Exception {

        List<HotelDto> list =  hotelDao.selectHotelTop();
        m.addAttribute("list", list);
        List<HotelDto> listseoul = hotelDao.selectHotelSeoul();
        m.addAttribute("listseoul",listseoul);
        return "mainPage";
    }
}
