package com.jspo.room.controller;


import com.jspo.hotel.dao.HotelDao;
import com.jspo.hotel.dto.HotelDto;
import com.jspo.room.dao.RoomDao;
import com.jspo.room.dto.RoomDto;
import com.jspo.upload.UploadFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

@Controller
public class RoomController {

    @Autowired
    private RoomDao roomDao;

    @Autowired
    private HotelDao hotelDao;

    @Value("${file.dir}")
    private String uploadPath;

    private RoomDto roomDto = RoomDto.getInstance();

    private HotelDto hotelDto = HotelDto.getInstance();

    @GetMapping("/room/list/{htId}")
    public String room(@PathVariable int htId, java.sql.Date checkin, java.sql.Date checkout, Model model) {

        // room list를 회원이 선택한 체크인 체크아웃 기간을 reserved 테이블에서 조회하여
        // 해당 기간에 겹치지 않는 r_id를 가진 객실들만 페이지에 보여줌.
        System.out.println("htId = " + htId);
        System.out.println("checkin = " + checkin);
        System.out.println("checkout = " + checkout);
        if (checkin == null || checkout == null) {
            checkin = new java.sql.Date(System.currentTimeMillis());
            checkout = new java.sql.Date(System.currentTimeMillis()+1000*60*60*24);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("htId", htId);
        map.put("checkin", checkin);
        map.put("checkout", checkout);
        List<RoomDto> list = roomDao.selectRoomByInOut(map);
        model.addAttribute("list",list);

        model.addAttribute("checkin", checkin);
        model.addAttribute("checkout", checkout);

        hotelDto = hotelDao.selectHotelByHtId(htId);
        model.addAttribute("hotelDto",hotelDto);

        return "roomList"; // 객실 html
    }

    @GetMapping("/room/reg")
    public String insert(Model model,int htId) {
        HotelDto hotelDto = hotelDao.selectHotelByHtId(htId);
        model.addAttribute("hotelDto",hotelDto); // 호텔 정보를 보여주기위한
        return "hotelReg";
    }
    @PostMapping("/room/reg")
    public String insert(RoomDto roomDto, MultipartFile file, @RequestParam String hotelHtId) throws Exception {

        System.out.println("들어온 호텔코드 : " + hotelHtId);
        String imgUploadPath = uploadPath + "roomimgUpload";
        System.out.println("위치 : Room Post insert = imgUploadPath :"+imgUploadPath);

        String ymdPath = UploadFileUtils.calcPath(imgUploadPath);

        String fileName = null;
        System.out.println(file);
        if (file.getOriginalFilename() != null && (!file.getOriginalFilename().equals(""))) {
            fileName = UploadFileUtils.fileUpload(imgUploadPath, file.getOriginalFilename(), file.getBytes(), ymdPath);
            System.out.println("fileName = "+fileName);
        } else {
            fileName = "none.png";
        }

        roomDto.setrImg(File.separator+"imgs"+File.separator+ "roomimgUpload" + ymdPath + File.separator+ fileName);
        roomDao.insertRoom(roomDto);

        // DB에 저장하면 -1일 처리돼서 +1 처리
//        roomDto.setrCheckin(new Date(roomDto.getrCheckin().getTime()+(1000*60*60*24)));

        return "redirect:/admin/roomlist";
    }

    @PostMapping("/room/updateView")
    public String updateView(Model model,int rId) throws Exception {

        model.addAttribute("roomupdate", roomDao.selectRoomByRId(rId));

        return "adminupdateView";
    }

    @PostMapping("/room/update")
    public String update(RoomDto roomDto, MultipartFile file) throws Exception {

        System.out.println(roomDto);
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
        roomDto.setrImg(File.separator+"imgs"+File.separator+ "imgUpload" + ymdPath + File.separator+ fileName);
        roomDao.updateRoom(roomDto);
        return "redirect:/admin/roomlist";
    }

    @PostMapping("/room/delete")
    public String delete(int rId) {
        roomDao.deleteRoom(rId);
        return "admin";
    }
}
