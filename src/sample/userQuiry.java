/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import com.hussam.hospital.db.dao.UserDetailsDao;
import com.hussam.hospital.db.vo.UserDetailsVo;
import com.hussam.hospital.tools.Funcs;
import com.hussam.hospital.validation.Validation;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList; 
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javax.swing.JOptionPane;

/**
 *
 * @author HA
 */
public class userQuiry implements Initializable {
    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtUserName;

    @FXML
    private JFXTextField txtPassword;
    
    @FXML
    private JFXComboBox<String> cUserType = new JFXComboBox<>();

    @FXML
    private JFXTextField txtFirstName;

    @FXML
    private JFXTextField txtSecondName;

    @FXML
    private JFXTextField txtMobile;
    
    
    @FXML
    private JFXButton btnPrev;

    @FXML
    private JFXButton btnNext;
    
    private int indexID = 0;
    
    @FXML
    void nextRowByClickIdFiald(ActionEvent event) {
        try {
            String id          = txtId.getText() ;
            boolean isTextEmpty = Validation.isEmpty(id);
            boolean isDigit     = Validation.isDigit(id);
            if (isTextEmpty == true || isDigit == false){
                JOptionPane.showMessageDialog(null, "please Insert valid data :-(");
                reset();
                return;
            }
            ArrayList<UserDetailsVo> list = UserDetailsDao.getInstance().loadAll();
            ArrayList<Integer> ides = new ArrayList<>();
            for(int i = 0; i < list.size(); i++){
                ides.add(list.get(i).getUservo().getId());
            }
            int indexOfPressedNumber = ides.indexOf(Integer.parseInt(id));
            if(indexOfPressedNumber != -1){
                showInfo(indexOfPressedNumber);
                indexID = indexOfPressedNumber;
            } else {
                JOptionPane.showMessageDialog(null, "Id Does'nt Exeist :-(");
                reset();
            }
        } catch (Exception ex) {
            Logger.getLogger(userQuiry.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void nextRow(ActionEvent event) {
        try {
            int getHiddenLabel = indexID;
            getHiddenLabel ++;
            showInfo(getHiddenLabel);
            indexID = getHiddenLabel;
        } catch (Exception ex) {
            Logger.getLogger(userQuiry.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void prevRow(ActionEvent event) {
        try {
            int getHiddenLabel = indexID;
            getHiddenLabel --;
            showInfo(getHiddenLabel);
            indexID = getHiddenLabel;
        } catch (Exception ex) {
            Logger.getLogger(userQuiry.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void showInfo(int index){
        try {
            ArrayList<UserDetailsVo> list = UserDetailsDao.getInstance().loadAll();
            txtId.setText(String.valueOf(list.get(index).getUservo().getId()));
            txtUserName.setText(  list.get(index).getUservo().getUserName());
            txtPassword.setText(  list.get(index).getUservo().getPassword());
            txtFirstName.setText( list.get(index).getFirstName());
            txtSecondName.setText(list.get(index).getSecondName());
            txtMobile.setText(    list.get(index).getMobile());
            cUserType.getSelectionModel().select(list.get(index).getUservo().getUserType().getId() - 1);
            if(index < list.size()- 1 )
                btnNext.setDisable(false);
            else
                btnNext.setDisable(true);
            if(index > 0)
                btnPrev.setDisable(false);
            else
               btnPrev.setDisable(true); 
        } catch (Exception ex) {
            Logger.getLogger(userQuiry.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    private void reset(){
        txtId.setText("");
        txtUserName.setText("");
        txtPassword.setText("");
        txtFirstName.setText("");
        txtSecondName.setText("");
        txtMobile.setText("");
        indexID = 0;
        btnNext.setDisable(true);
        btnPrev.setDisable(true);
    }
    
    public JFXTextField getTxtId() {
        return txtId;
    }

    public void setTxtId(JFXTextField txtId) {
        this.txtId = txtId;
    }

    public JFXTextField getTxtUserName() {
        return txtUserName;
    }

    public void setTxtUserName(JFXTextField txtUserName) {
        this.txtUserName = txtUserName;
    }

    public JFXTextField getTxtPassword() {
        return txtPassword;
    }

    public void setTxtPassword(JFXTextField txtPassword) {
        this.txtPassword = txtPassword;
    }

    public JFXComboBox<String> getcUserType() {
        return cUserType;
    }

    public void setcUserType(JFXComboBox<?> cUserType) {
        this.cUserType = (JFXComboBox<String>) cUserType;
    }

    public JFXTextField getTxtFirstName() {
        return txtFirstName;
    }

    public void setTxtFirstName(JFXTextField txtFirstName) {
        this.txtFirstName = txtFirstName;
    }

    public JFXTextField getTxtSecondName() {
        return txtSecondName;
    }

    public void setTxtSecondName(JFXTextField txtSecondName) {
        this.txtSecondName = txtSecondName;
    }

    public JFXTextField getTxtMobile() {
        return txtMobile;
    }

    public void setTxtMobile(JFXTextField txtMobile) {
        this.txtMobile = txtMobile;
    }

    public JFXButton getBtnNext() {
        return btnNext;
    }

    public void setBtnNext(JFXButton btnNext) {
        this.btnNext = btnNext;
    }    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getcUserType().setItems( FXCollections.observableArrayList( "ADMIN","DOCTOR","NURSE"));
        getcUserType().getSelectionModel().selectFirst();
        //set filcor
        Funcs.textFaildsStyle("-fx-text-fill:#f00;-fx-background-color:#fff", getTxtId());
        Funcs.textFaildsStyle("-fx-text-fill:#000;-fx-background-color:rgba(9, 9, 9, 0.06)",getTxtUserName(),getTxtPassword(),getTxtFirstName(),getTxtSecondName(),getTxtMobile());
        getcUserType().setStyle("-fx-font-size:16;-fx-background-color:rgba(9, 9, 9, 0.06)");
    }
}
