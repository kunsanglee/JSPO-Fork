package com.jspo.room.controller;


import com.jspo.hotel.dto.HotelDto;
import com.jspo.room.dao.RoomDao;
import com.jspo.room.dto.RoomDto;
import com.jspo.upload.UploadFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Controller
public class RoomController {

    @Autowired
    private RoomDao roomDao;

    @Value("${file.dir}")
    private String uploadPath;

    private RoomDto roomDto = RoomDto.getInstance();

    @GetMapping("/hotel/{rId}")
    public String room(@PathVariable String rId) {

        return "room"; // 객실 html
    }

    @PostMapping("/room/reg")
    public String insert(RoomDto roomDto, MultipartFile file) throws Exception {
        String imgUploadPath = uploadPath + "roomimgUpload";
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

        roomDto.setrImg(File.separator+"imgs"+File.separator+ "roomimgUpload" + ymdPath + File.separator+ fileName);
        roomDao.insertRoom(roomDto);

        return "redirect:/room/list";
    }
    @GetMapping("/room/list")
    public String select(Model model) throws Exception {

        List<RoomDto> list = roomDao.selectRoom();

        model.addAttribute("list",list);
        System.out.println(list);
        return "roomList";
    }
}
