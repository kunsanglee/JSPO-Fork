package com.jspo.image.dto;

import java.util.Objects;

public class ImageDto {

    private int imgId;
    private int roomRId;
    private int roomHotelHtId;
    private String img;
    private String imgExplain;

    public ImageDto() {}
    public ImageDto(int imgId, int roomRId, int roomHotelHtId, String img, String imgExplain) {
        this.imgId = imgId;
        this.roomRId = roomRId;
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

    public int getRoomRId() {
        return roomRId;
    }

    public void setRoomRId(int roomRId) {
        this.roomRId = roomRId;
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
        return imgId == imageDto.imgId && roomRId == imageDto.roomRId && roomHotelHtId == imageDto.roomHotelHtId && Objects.equals(img, imageDto.img) && Objects.equals(imgExplain, imageDto.imgExplain);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imgId, roomRId, roomHotelHtId, img, imgExplain);
    }

    @Override
    public String toString() {
        return "ImageDto{" +
                "imgId=" + imgId +
                ", roomRId=" + roomRId +
                ", roomHotelHtId=" + roomHotelHtId +
                ", img='" + img + '\'' +
                ", imgExplain='" + imgExplain + '\'' +
                '}';
    }
}
