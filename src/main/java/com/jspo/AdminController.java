package com.jspo;

import com.jspo.hotel.dao.HotelDao;
import com.jspo.hotel.dto.HotelDto;
import com.jspo.room.dao.RoomDao;
import com.jspo.room.dto.RoomDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    HotelDao hotelDao;

    HotelDto hotelDto = HotelDto.getInstance();

    @Autowired
    RoomDao roomDao;

    RoomDto roomDto = RoomDto.getInstance();

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/admin/hotellist")
    public String adminHotel(Model model) throws Exception {

        List<HotelDto> hlist = hotelDao.selectHotel();
        model.addAttribute("hlist",hlist);

        List pricelist = new ArrayList<>(); // 가격 부분

        for(int i=1; i<=hlist.size() ; i++) {
            pricelist.add(roomDao.selectPrice(i));
        }
        model.addAttribute("pricelist",pricelist);

        return "admin";
    }

    @GetMapping("/admin/roomlist")
    public String adminRoom(Model model) throws Exception {

        List<RoomDto> rlist = roomDao.selectRoom();
        model.addAttribute("rlist",rlist);
        return "admin";
    }



}
