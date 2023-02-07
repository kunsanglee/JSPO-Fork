package com.jspo.hotel.controller;

import com.jspo.hotel.dao.HotelDao;
import com.jspo.hotel.dto.HotelDto;
import com.jspo.upload.UploadFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

@Controller
public class HotelController {

    @Autowired
    private HotelDao hotelDao;

    @Value("${file.dir}")
    private String uploadPath;

    private HotelDto hotelDto = HotelDto.getInstance();

    @GetMapping("/hotel/reg")
    public String insert(HttpSession session) {

//        if (!"admin@jspo.com".equals(session.getAttribute("email"))) {
//            return "redirect:/";
//        }

        return "HotelReg";
    }


    @PostMapping("/hotel/reg")
    public String insert(HotelDto hotelDto,  MultipartFile file) throws Exception {

        String imgUploadPath = uploadPath + "imgUpload";
        System.out.println("1. imgUploadPath"+imgUploadPath);

        String ymdPath = UploadFileUtils.calcPath(imgUploadPath);

        String fileName = null;
        System.out.println(file);
        if (file.getOriginalFilename() != null && (!file.getOriginalFilename().equals(""))) {
            fileName = UploadFileUtils.fileUpload(imgUploadPath, file.getOriginalFilename(), file.getBytes(), ymdPath);
            System.out.println("fileName = "+fileName);
        } else {
            fileName = uploadPath + File.separator+ "images" + File.separator+ "none.png";
        }

        hotelDto.setHtImg(File.separator+"imgs"+File.separator+ "imgUpload" + ymdPath + File.separator+ fileName);
        hotelDao.insertHotel(hotelDto);

        return "redirect:/hotel/list";
    }

    @GetMapping("/hotel/list")
    public String select(Model model) throws Exception {

       List<HotelDto> list = hotelDao.selectHotel();
       model.addAttribute("list",list);
        return "HotelList";
    }

//    @PostMapping("/upload")
//    public String Register(HotelDto hotelDto, MultipartFile file) throws Exception {
//
//
//        String imgUploadPath = uploadPath + "imgUpload";
//        System.out.println("1. imgUploadPath"+imgUploadPath);
//
//        String ymdPath = UploadFileUtils.calcPath(imgUploadPath);
//
//        String fileName = null;
//
//        if (file.getOriginalFilename() != null && (!file.getOriginalFilename().equals(""))) {
//            fileName = UploadFileUtils.fileUpload(imgUploadPath, file.getOriginalFilename(), file.getBytes(), ymdPath);
//            System.out.println("fileName = "+fileName);
//        } else {
//            fileName = uploadPath + File.separator+ "images" + File.separator+ "none.png";
//        }
//
//        hotelDto.setHtImg(File.separator+"image"+File.separator+ "imgUpload" + ymdPath + File.separator+ fileName);
//        hotelDao.insertHotel(hotelDto);
//
//        return "redirect:/list";
//    }
}