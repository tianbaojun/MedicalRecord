package com.tabjin.feng.medicalrecord.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/2/13.
 */
public class Patient implements Serializable{
    private int id;//xutil中使用
    private String name;

    private boolean gender;

    private String date;
    private int age;
    private String mobil;
    private String id1;//身份证号
    private String jiuZhen_date;
    private String keshi;
    private String bianHao_type;
    private String biaoHao;
    private String Tel,email,address,job,contacter,interducer,note;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMobil() {
        return mobil;
    }

    public void setMobil(String mobil) {
        this.mobil = mobil;
    }

    public String getId1() {
        return id1;
    }

    public void setId1(String id1) {
        this.id1 = id1;
    }

    public String getJiuZhen_date() {
        return jiuZhen_date;
    }

    public void setJiuZhen_date(String jiuZhen_date) {
        this.jiuZhen_date = jiuZhen_date;
    }

    public String getKeshi() {
        return keshi;
    }

    public void setKeshi(String keshi) {
        this.keshi = keshi;
    }

    public String getBianHao_type() {
        return bianHao_type;
    }

    public void setBianHao_type(String bianHao_type) {
        this.bianHao_type = bianHao_type;
    }

    public String getBiaoHao() {
        return biaoHao;
    }

    public void setBiaoHao(String biaoHao) {
        this.biaoHao = biaoHao;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getContacter() {
        return contacter;
    }

    public void setContacter(String contacter) {
        this.contacter = contacter;
    }

    public String getInterducer() {
        return interducer;
    }

    public void setInterducer(String interducer) {
        this.interducer = interducer;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", date='" + date + '\'' +
                ", age=" + age +
                ", mobil='" + mobil + '\'' +
                ", id1='" + id1 + '\'' +
                ", jiuZhen_date='" + jiuZhen_date + '\'' +
                ", keshi='" + keshi + '\'' +
                ", bianHao_type='" + bianHao_type + '\'' +
                ", biaoHao='" + biaoHao + '\'' +
                ", Tel='" + Tel + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", job='" + job + '\'' +
                ", contacter='" + contacter + '\'' +
                ", interducer='" + interducer + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
