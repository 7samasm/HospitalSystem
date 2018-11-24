/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hussam.hospital.db.type;

/**
 *
 * @author HA
 */
public enum UsersType {
    
    ADMIN(1,"admin"),DOCTOR(2,"doctor"),NURSE(3,"nurse");
    
    private int id;
    private String type;
    
    private UsersType(int id ,String type ){
        this.id = id;
        this.type = type;
    }
    
    public static UsersType getUserTypeById(int id){
        for(UsersType out : UsersType.values()){
            if(out.getId() == id){
                return out;
            }
        }
        return null;
    }
    
    public static UsersType getUserTypeByType(String type){
        for(UsersType out : UsersType.values()){
            if(out.getType().equals(type)){
                return out;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
