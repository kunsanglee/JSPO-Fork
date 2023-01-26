package com.jspo.member.dto;


import java.sql.Date;
import java.util.Objects;

public class MemberDto {

    private int id;
    private String email;
    private String pwd;
    private String name;
    private Date birth;
    private String phone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "MemberDto{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", pwd='" + pwd + '\'' +
                ", name='" + name + '\'' +
                ", birth=" + birth +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberDto memberDto = (MemberDto) o;
        return id == memberDto.id && Objects.equals(email, memberDto.email) && Objects.equals(pwd, memberDto.pwd) && Objects.equals(name, memberDto.name) && Objects.equals(birth, memberDto.birth) && Objects.equals(phone, memberDto.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, pwd, name, birth, phone);
    }

    public MemberDto(int id, String email, String pwd, String name, Date birth, String phone) {
        this.id = id;
        this.email = email;
        this.pwd = pwd;
        this.name = name;
        this.birth = birth;
        this.phone = phone;
    }

    public MemberDto() { }

    // DB조회용 user객체 싱글톤패턴으로 재사용
    private static final MemberDto instance = new MemberDto();

    public static MemberDto getInstance() {
        return instance;
    }

}
