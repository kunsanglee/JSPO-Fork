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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;

@Controller
public class RoomController {

    @Autowired
    private RoomDao roomDao;

    @Value("${file.dir}")
    private String uploadPath;

    private RoomDto roomDto = RoomDto.getInstance();

//    @GetMapping("/hotel/{rId}")
//    public String room(@PathVariable String rId) {
//
//        return "room"; // 객실 html
//    }
    @GetMapping("/room/list/{htId}")
    public String room(@PathVariable int htId, Model model) {

        List<RoomDto> list = roomDao.selectRoomByhtId(htId);
        model.addAttribute("list",list);

        return "roomlist"; // 객실 html
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
        roomDto.setrCheckin(new Date(roomDto.getrCheckin().getTime()+(1000*60*60*24)));

        return "redirect:list";
    }
    @GetMapping("/room/list")
    public String select(Model model) throws Exception {

        List<RoomDto> list = roomDao.selectRoom();
        model.addAttribute("list",list);
        System.out.println(list);
        return "roomList";
    }


}
