package com.jspo.hotel.controller;

import com.jspo.hotel.dao.HotelDao;
import com.jspo.hotel.dto.HotelDto;
import com.jspo.room.dao.RoomDao;
import com.jspo.upload.UploadFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class HotelController {

    @Autowired
    private HotelDao hotelDao;

    @Value("${file.dir}")
    private String uploadPath;
    @Autowired
    private RoomDao roomDao;

    @GetMapping("/hotel")
    public String hotel(){
        return "hotelPage";
    }

    @GetMapping("/hotelsearch")
    public String hotel(@RequestParam(required = false) String htName,HotelDto hotelDto , Model model,@RequestParam(required = false) String leftvalue,@RequestParam(required = false) String rightvalue){

             List<HotelDto> list = null;
        try {
            leftvalue = leftvalue.replace("원","");
            leftvalue = leftvalue.replace(",","");
            int lvalue = Integer.parseInt(leftvalue);
            rightvalue = rightvalue.replace("원","");
            rightvalue = rightvalue.replace(",","");
            int rvalue = Integer.parseInt(rightvalue);


            Map<String, Integer> map = new HashMap<>();
            if(!("".equals(htName))) {
                list = hotelDao.selectHotelByName(htName);
            } else if("".equals(htName) && lvalue == 0 && rvalue == 500000) {
                list = hotelDao.selectHotelByName(htName);
            }  else if("".equals(htName) && lvalue != 0 || rvalue != 500000) {
                map.put("lvalue", lvalue);
                map.put("rvalue", rvalue);
                list = roomDao.selectHotelBetween(map);
            }
        } catch(Exception e) {
            list = hotelDao.selectHotelByName(htName);

        }
         //숙소명만 검색했을땐 숙소명만
        //가격 조정했을땐 가격 조정한 숙소명만
        //가격 조정, 숙소명 검색했을때 조건에 만족하는 숙소명만

        model.addAttribute("list",list);
        List pricelist = new ArrayList<>(); // 가격 부분
        try {
            leftvalue = leftvalue.replace("원","");
            leftvalue = leftvalue.replace(",","");
            int lvalue = Integer.parseInt(leftvalue);
            rightvalue = rightvalue.replace("원","");
            rightvalue = rightvalue.replace(",","");
            int rvalue = Integer.parseInt(rightvalue);
            for(int i=0; i<list.size() ; i++) {
                pricelist.add(roomDao.selectmainPrice(list.get(i).getHtId(),lvalue,rvalue));
                System.out.println(list.get(i).getHtId());
            }

        } catch(Exception e) {
            for(int i=0; i<list.size() ; i++) {
                pricelist.add(roomDao.selectPrice(list.get(i).getHtId()));
            }
        }


        System.out.println(pricelist);
        model.addAttribute("pricelist",pricelist);
        return "hotelPage";
    }
    @GetMapping("/hotel/reg")
    public String insertHotel(HttpSession session) {
        if (!"admin@jspo.com".equals(session.getAttribute("email"))) {
            return "redirect:/";
        }
        return "adminReg";
    }

    @PostMapping("/hotel/reg")
    public String insert(HotelDto hotelDto, MultipartFile file, HttpSession session) throws Exception {
        if (!"admin@jspo.com".equals(session.getAttribute("email"))) {
            return "redirect:/";
        }

        String imgUploadPath = uploadPath + "imgUpload";
        System.out.println("1. imgUploadPath"+imgUploadPath);

        String ymdPath = UploadFileUtils.calcPath(imgUploadPath);

        String fileName = null;
        System.out.println(file);
        System.out.println(file.getOriginalFilename());
        if (file.getOriginalFilename() != null && (!file.getOriginalFilename().equals(""))) {
            fileName = UploadFileUtils.fileUpload(imgUploadPath, file.getOriginalFilename(), file.getBytes(), ymdPath);
            System.out.println("fileName = "+fileName);
        } else {
            fileName = "none.png";
        }
//        hotelDto.setHtImg(File.separator+"imgs"+File.separator+ "imgUpload" + ymdPath + File.separator+ fileName);
        hotelDto.setHtImg("http://localhost:8080/static/"+fileName);
       hotelDao.insertHotel(hotelDto);
        return "redirect:/admin/hotellist";
    }
    @GetMapping("/hotel/list")
    public String select(Model model) throws Exception {

        List<HotelDto> list = hotelDao.selectHotel();
       model.addAttribute("list",list);

        List pricelist = new ArrayList<>();

           for(int i=1; i<=list.size() ; i++) {
            pricelist.add(roomDao.selectPrice(i));

        }
        model.addAttribute("pricelist",pricelist);

        return "hotelList";
    }
    @PostMapping("/hotel/updateView")
    public String updateView(Model model,int htId, HttpSession session) throws Exception {
        if (!"admin@jspo.com".equals(session.getAttribute("email"))) {
            return "redirect:/";
        }

        model.addAttribute("hotelupdate", hotelDao.selectHotelByHtId(htId));
        return "adminupdateView";
    }
    @PostMapping("/hotel/update")
    public String update(HotelDto hotelDto,MultipartFile file, HttpSession session) throws Exception {
        if (!"admin@jspo.com".equals(session.getAttribute("email"))) {
            return "redirect:/";
        }

        String imgUploadPath = uploadPath + "imgUpload";
        System.out.println("1. imgUploadPath"+imgUploadPath);

        String ymdPath = UploadFileUtils.calcPath(imgUploadPath);

        String fileName = null;
        System.out.println(file);
        System.out.println(file.getOriginalFilename());
        if (file.getOriginalFilename() != null && (!file.getOriginalFilename().equals(""))) {
            fileName = UploadFileUtils.fileUpload(imgUploadPath, file.getOriginalFilename(), file.getBytes(), ymdPath);
            System.out.println("fileName = "+fileName);
        } else {
            fileName = "none.png";
        }
//        hotelDto.setHtImg(File.separator+"imgs"+File.separator+ "imgUpload" + ymdPath + File.separator+ fileName);
        hotelDto.setHtImg("http://localhost:8080/static/"+fileName);
        hotelDao.updateHotel(hotelDto);
        return "redirect:/admin/hotellist";
    }

    @PostMapping("/hotel/delete")
    public String delete(HotelDto hotelDto, HttpSession session) throws Exception {
        if (!"admin@jspo.com".equals(session.getAttribute("email"))) {
            return "redirect:/";
        }
        hotelDao.deleteHotel(hotelDto.getHtId());
        return "redirect:list";
    }


}