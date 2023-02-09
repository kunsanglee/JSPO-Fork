package com.jspo.hotel.controller;

import com.jspo.hotel.dao.HotelDao;
import com.jspo.hotel.dto.HotelDto;
import com.jspo.room.dao.RoomDao;
import com.jspo.room.dto.RoomDto;
import com.jspo.upload.UploadFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

@Controller
public class HotelController {

    @Autowired
    private HotelDao hotelDao;

    @Value("${file.dir}")
    private String uploadPath;

    private HotelDto hotelDto = HotelDto.getInstance();

    @Autowired
    private RoomDao roomDao;


    private RoomDto roomDto = RoomDto.getInstance();

    @GetMapping("/hotel/reg")
    public String insert(HttpSession session, Model model)  throws Exception{

//        if (!"admin@jspo.com".equals(session.getAttribute("email"))) {
//            return "redirect:/";
//        }
        return "hotelReg";

    }
    @PostMapping("/hotel/reg")
    public String insert(HotelDto hotelDto, MultipartFile file) throws Exception {

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
        hotelDto.setHtImg(File.separator+"imgs"+File.separator+ "imgUpload" + ymdPath + File.separator+ fileName);
       hotelDao.insertHotel(hotelDto);

        return "/hotel/list";
    }
    @GetMapping("/hotel/list")
    public String select(Model model,String fileName) throws Exception {

        System.out.println(fileName);
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
    public String updateView(Model model,int htId) throws Exception {

        model.addAttribute("hotelupdate", hotelDao.selectHotelByHtId(htId));
        return "adminupdateView";
    }
    @PostMapping("/hotel/update")
    public String update(HotelDto hotelDto,MultipartFile file) throws Exception {
        System.out.println(hotelDto);
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
        hotelDto.setHtImg(File.separator+"imgs"+File.separator+ "imgUpload" + ymdPath + File.separator+ fileName);
        hotelDao.updateHotel(hotelDto);
        return "redirect:adminlist";
    }

    @PostMapping("/hotel/delete")
    public String delete(HotelDto hotelDto) throws Exception {
        hotelDao.deleteHotel(hotelDto.getHtId());
        return "redirect:list";
    }



}