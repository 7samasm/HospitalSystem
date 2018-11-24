/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import com.hussam.hospital.db.dao.UserDetailsDao;
import com.hussam.hospital.db.dao.UsersDao;
import com.hussam.hospital.db.vo.UsersVo;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author HA
 */
public class Login implements Initializable {
    @FXML
    private JFXTextField txtUserName;

    @FXML
    private JFXPasswordField txtPass;

    @FXML
    private StackPane sp;

    @FXML
    private JFXButton btnLogin;

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String userName = txtUserName.getText();
                String password = txtPass.getText();
                //set User Details Vo object[udvToInjectIntoDaolist] to inject into Dao list methods[getData,insert,delete]
                UsersVo udvToInjectIntoDaolist = new UsersVo();
                udvToInjectIntoDaolist.setUserName(userName);
                udvToInjectIntoDaolist.setPassword(password);
                 /*===test================================*/
                    try {
                        List list = UserDetailsDao.getInstance().loadAll();
                        Integer[] ids = new Integer[list.size()] ;
                        for(int i = 0; i < list.size(); i++){
                            ids[i] = UserDetailsDao.getInstance().loadAll().get(i).getUservo().getId();
                            System.out.print(ids[i] + "|");
                        }
                        System.out.println(Arrays.asList(ids).indexOf(2));
                    } catch (Exception ex) {
                        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                    }
                /*===test================================*/

                try {
                    UsersVo uv = UsersDao.getInstance().getData(udvToInjectIntoDaolist);
                    if (uv == null) {
                        JOptionPane.showMessageDialog(null, "error");
                        // System.out.print("error");
                    } else {
                        Parent  root = FXMLLoader.load(Login.this.getClass().getResource("view/Home.fxml"));
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.show();
                        //hide this window
                        ((Node)(event.getSource())).getScene().getWindow().hide();
                    }
                }catch (Exception ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
