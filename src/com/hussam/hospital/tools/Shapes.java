/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hussam.hospital.tools;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author HA
 */
public class Shapes {
    public static void dialog(StackPane stackPane,String msg,String head){
        JFXDialogLayout ct   = new JFXDialogLayout();
        final VBox      root = new VBox(0);
        Text heading = new Text(head);
        Text txt = new Text(msg);
        JFXButton       btn  = new JFXButton("close");
        root.getChildren().addAll(txt,btn);
        
        //style
        root.setStyle("-fx-min-height:40;-fx-max-width:100");
        heading.setFont(Font.font("Tahoma",FontWeight.BOLD,15));
        heading.setFill(Color.RED);
        VBox.setMargin(btn, new Insets(20,0,0,180));
        txt.setFont(new Font(13));
        root.getChildren().get(1).setStyle("-fx-background-color:#3f51b5;-fx-text-fill:#fff;-fx-font-size:14;-fx-min-width:60");
        
        ct.setHeading(heading);
        ct.setBody(root);
        JFXDialog dialog = new JFXDialog(stackPane, ct, JFXDialog.DialogTransition.CENTER);
        btn.setOnAction((ActionEvent event1) -> {
        dialog.close();
        });
        dialog.show();
       // System.out.println(VBox.getMargin(ct));
    }
}
