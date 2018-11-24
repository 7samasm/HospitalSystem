/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hussam.hospital.db.vo;

/**
 *
 * @author HA
 */
public class UserDetailsVo {
    private int id;
    private UsersVo uservo;
    private String firstName;
    private String secondName;
    private String mobile;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UsersVo getUservo() {
        return uservo;
    }

    public void setUservo(UsersVo uservo) {
        this.uservo = uservo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
 
}
