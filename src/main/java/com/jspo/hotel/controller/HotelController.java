package com.jspo.hotel.controller;

import com.jspo.hotel.dao.HotelDao;
import com.jspo.hotel.dto.HotelDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HotelController {

    @Autowired
    private HotelDao hotelDao;

    private HotelDto hotelDto = HotelDto.getInstance();

    @GetMapping("/hotel/reg")
    public String insert() {

        return "HotelReg";
    }


    @PostMapping("/hotel/reg")
    public String insert(HotelDto hotelDto) throws Exception {
        System.out.println(hotelDto);
        hotelDao.insertHotel(hotelDto);
        return "HotelList";
    }

    @GetMapping("/hotel/list")
    public String select(Model model) throws Exception {

       List<HotelDto> list = hotelDao.selectHotel();
       model.addAttribute("list",list);
        return "HotelList";
    }
}