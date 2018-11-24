/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hussam.hospital.tools;

import com.jfoenix.controls.JFXTextField;

/**
 *
 * @author HA
 */
public class Funcs {
    public static void reset(JFXTextField ... textFailds){
        for(JFXTextField textFaild : textFailds ){
            textFaild.setText("");
        }
    }
    
    public static void textFaildsStyle(String  value,JFXTextField ... textFailds){
        for(JFXTextField textFaild : textFailds ){
            textFaild.setStyle(value);
        }
    }
}
