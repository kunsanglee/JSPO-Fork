package com.jspo.image.controller;

import com.jspo.hotel.dao.HotelDao;
import com.jspo.hotel.dto.HotelDto;
import com.jspo.image.dao.ImageDao;
import com.jspo.room.dao.RoomDao;
import com.jspo.room.dto.RoomDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ImageController {

    @Autowired
    HotelDao hotelDao;

    HotelDto hotelDto = HotelDto.getInstance();

    @Autowired
    ImageDao imageDao;

    @GetMapping("/image/reg")
    public String insertImage(Model model) throws Exception {

        List<HotelDto> list  = hotelDao.selectHotel();
        System.out.println(list);
        model.addAttribute("list",list);

        return "adminImageReg";
    }
    @PostMapping("/image/reg")
    public String insertImage() {



        return "admin";
    }
}
