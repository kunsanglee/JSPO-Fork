package com.jspo.image.controller;

import com.jspo.hotel.dao.HotelDao;
import com.jspo.hotel.dto.HotelDto;
import com.jspo.image.dao.ImageDao;
import com.jspo.image.dto.ImageDto;
import com.jspo.upload.UploadFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class ImageController {

    @Autowired
    HotelDao hotelDao;

    @Autowired
    ImageDao imageDao;

    @Value("${file.dir}")
    private String uploadPath;

    @GetMapping("/image/reg")
    public String insertImage(Model model) throws Exception {

        List<HotelDto> list  = hotelDao.selectHotel();
        System.out.println(list);
        model.addAttribute("list",list);

        return "adminImageReg";
    }
    @PostMapping("/image/reg")
    public String insertImage(ImageDto imageDto, MultipartFile file) throws Exception {

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

        imageDto.setImg("http://localhost:8080/static/"+fileName);
        imageDao.insertImage(imageDto);
        System.out.println(imageDto);

        return "redirect:/admin/image";
    }
    @PostMapping("/image/updateView")
    public String updateView(Model model,int imgId) throws Exception {

        model.addAttribute("imageupdate", imageDao.selectImageByimgId(imgId));

        return "adminupdateView";
    }

    @PostMapping("/image/update")
    public String update(ImageDto imageDto,MultipartFile file) throws Exception {

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

        imageDto.setImg("http://localhost:8080/static/"+fileName);
        imageDao.updateImage(imageDto);

        return "redirect:/admin/image";
    }
    @PostMapping("/image/delete")
    public String delete(ImageDto imageDto) throws Exception {
        imageDao.deleteImage(imageDto.getImgId());
        return "redirect:/admin/image";
    }
}
