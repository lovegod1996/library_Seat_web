package com.xoqao.web.bean.user;


import java.io.Serializable;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/5/9.
 */

public class User implements Serializable {
    private Integer uid;
    private String username;
    private String password;
    private String gender;
    private String phone;
    private String headerpic;
    private String id;
    private String name;
    private String registtime;


    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHeaderpic() {
        return headerpic;
    }

    public void setHeaderpic(String headerpic) {
        this.headerpic = headerpic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegisttime() {
        return registtime;
    }

    public void setRegisttime(String registtime) {
        this.registtime = registtime;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", headerpic='" + headerpic + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", registtime='" + registtime + '\'' +
                '}';
    }
}
