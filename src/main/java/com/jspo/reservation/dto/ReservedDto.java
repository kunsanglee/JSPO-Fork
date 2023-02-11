package com.jspo.reservation.dto;

import java.util.Date;

public class ReservedDto {
    private Date resDate;
    private int htId;
    private int rId;

    public ReservedDto() {
    }

    public ReservedDto(Date resDate, int htId, int rId) {
        this.resDate = resDate;
        this.htId = htId;
        this.rId = rId;
    }

    private static final ReservedDto instance = new ReservedDto();

    public static ReservedDto getInstance() { return instance; }

    public Date getResDate() {
        return resDate;
    }

    public void setResDate(Date resDate) {
        this.resDate = resDate;
    }

    public int getHtId() {
        return htId;
    }

    public void setHtId(int htId) {
        this.htId = htId;
    }

    public int getrId() {
        return rId;
    }

    public void setrId(int rId) {
        this.rId = rId;
    }
}
