package com.jspo.image.dto;

import java.util.Objects;

public class ImageDto {

    private int imgId;
    private int roomHotelHtId;
    private String img;
    private String imgExplain;
    private String htName;

    public String getHtName() {
        return htName;
    }

    public void setHtName(String htName) {
        this.htName = htName;
    }

    public ImageDto() {}

    public ImageDto(int imgId, int roomHotelHtId, String img, String imgExplain) {
        this.imgId = imgId;
        this.roomHotelHtId = roomHotelHtId;
        this.img = img;
        this.imgExplain = imgExplain;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public int getRoomHotelHtId() {
        return roomHotelHtId;
    }

    public void setRoomHotelHtId(int roomHotelHtId) {
        this.roomHotelHtId = roomHotelHtId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImgExplain() {
        return imgExplain;
    }

    public void setImgExplain(String imgExplain) {
        this.imgExplain = imgExplain;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageDto imageDto = (ImageDto) o;
        return imgId == imageDto.imgId && roomHotelHtId == imageDto.roomHotelHtId && Objects.equals(img, imageDto.img) && Objects.equals(imgExplain, imageDto.imgExplain);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imgId, roomHotelHtId, img, imgExplain);
    }

    @Override
    public String toString() {
        return "ImageDto{" +
                "imgId=" + imgId +
                ", roomHotelHtId=" + roomHotelHtId +
                ", img='" + img + '\'' +
                ", imgExplain='" + imgExplain + '\'' +
                '}';
    }

    // DB조회용 user객체 싱글톤패턴으로 재사용
    private static final ImageDto instance = new ImageDto();

    public static ImageDto getInstance() {
        return instance;
    }
}
