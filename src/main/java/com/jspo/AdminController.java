package com.jspo;

import com.jspo.hotel.dao.HotelDao;
import com.jspo.hotel.dto.HotelDto;
import com.jspo.image.dao.ImageDao;
import com.jspo.image.dto.ImageDto;
import com.jspo.room.dao.RoomDao;
import com.jspo.room.dto.RoomDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    HotelDao hotelDao;

    @Autowired
    RoomDao roomDao;

    @Autowired
    ImageDao imageDao;

    @GetMapping("")
    public String admin(HttpSession session){
        if (!"admin@jspo.com".equals(session.getAttribute("email"))) {
            return "redirect:/";
        }
        return "redirect:/admin/hotellist";
    }

    @GetMapping("/hotellist")
    public String adminHotel(Model model, HttpSession session) throws Exception {
        if (!"admin@jspo.com".equals(session.getAttribute("email"))) {
            return "redirect:/";
        }

        List<HotelDto> hlist = hotelDao.selectHotel();
        model.addAttribute("hlist",hlist);
        System.out.println("hlist =" +hlist);
        List pricelist = new ArrayList<>(); // 가격 부분
        for(int i=1; i<=hlist.size() ; i++) {
            pricelist.add(roomDao.selectPrice(i));
        }
        System.out.println(pricelist);
        model.addAttribute("pricelist",pricelist);

        return "admin";
    }

    @GetMapping("/roomlist")
    public String adminRoom(Model model, HttpSession session) throws Exception {
        if (!"admin@jspo.com".equals(session.getAttribute("email"))) {
            return "redirect:/";
        }

        List<RoomDto> rlist = roomDao.selectRoom();
        model.addAttribute("rlist",rlist);
        return "admin";
    }

    @GetMapping("/image") // 사진 관리 클릭시 들어옴
    public String adminImage(Model model, HttpSession session) {
        if (!"admin@jspo.com".equals(session.getAttribute("email"))) {
            return "redirect:/";
        }

        List<ImageDto> ilist = imageDao.selectImage();
        System.out.println("ilist = "+ilist);
        model.addAttribute("ilist",ilist);

        return "admin"; // admin 으로 몰아버릴까
    }
}
