package com.jspo.image.dao;

import com.jspo.image.dto.ImageDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ImageDao {

    void insertImage(ImageDto imageDto);

    List<ImageDto> selectImage();
}
