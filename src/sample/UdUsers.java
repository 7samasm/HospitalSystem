/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import com.hussam.hospital.db.dao.UserDetailsDao;
import com.hussam.hospital.db.dao.UsersDao;
import com.hussam.hospital.db.type.UsersType;
import com.hussam.hospital.db.vo.UserDetailsVo;
import com.hussam.hospital.db.vo.UsersVo;
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
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javax.swing.JOptionPane;

/**
 *
 * @author HA
 */
public class UdUsers implements Initializable {
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

    @FXML
    private Label labelCount;
    
    @FXML
    private void update(ActionEvent event) {
        //user vars
        String id          = txtId.getText() ;
        String userName    = txtUserName.getText();
        String password    = txtPassword.getText();
        UsersType userType = UsersType.getUserTypeByType(cUserType.getValue().toLowerCase());
        //User Details vars
        String firstName   = txtFirstName.getText();
        String secondName  = txtSecondName.getText();
        String mobile      = txtMobile.getText();
        
        //validation start
        boolean isTextEmpty = Validation.isEmpty(id,userName,password,firstName,secondName,mobile);
        boolean isNumEmpty  = Validation.isEmpty(cUserType.getSelectionModel().getSelectedIndex());
        boolean isDigit     = Validation.isDigit(id,mobile);
        boolean isText      = Validation.isText(firstName,secondName);
        if(isTextEmpty == true ||isNumEmpty == true ){
            JOptionPane.showMessageDialog(null, "please Insert valid data :(");
            return;
        }
        if(isDigit == false ||isText == false ){
            JOptionPane.showMessageDialog(null, "please check data type :(");
            return;
        }
        // validation end 
        // set user vo object[uvToInjectIntoDaolist] to inject into Dao list methods[getData,insert,delete] 
        
        UsersVo uv = new UsersVo();
        uv.setId(Integer.valueOf(id));
        uv.setUserName(userName);
        uv.setPassword(password);
        uv.setUserType(userType);
        
        //set User Details Vo object[udvToInjectIntoDaolist] to inject into Dao list methods[getData,insert,delete] 
        
        UserDetailsVo udvToInjectIntoDaolist = new UserDetailsVo();
        udvToInjectIntoDaolist.setUservo(uv);
        udvToInjectIntoDaolist.setFirstName(firstName);
        udvToInjectIntoDaolist.setSecondName(secondName);
        udvToInjectIntoDaolist.setMobile(mobile);
        
        try {
            int Count = UserDetailsDao.getInstance().update(udvToInjectIntoDaolist);
            if (Count == 1){
                JOptionPane.showMessageDialog(null, "update Successfully");
                reset();
            }else{
                JOptionPane.showMessageDialog(null, "update faild  :( ");            
            }
        } catch (Exception ex) {
            Logger.getLogger(UdUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void delete(ActionEvent event) {
        //user vars
        String id          = txtId.getText() ;
        String userName    = txtUserName.getText();
        String password    = txtPassword.getText();
        UsersType userType = UsersType.getUserTypeByType(cUserType.getValue().toLowerCase());
        //User Details vars
        String firstName   = txtFirstName.getText();
        String secondName  = txtSecondName.getText();
        String mobile      = txtMobile.getText();
        
        //validation start
        boolean isTextEmpty = Validation.isEmpty(id,userName,password,firstName,secondName,mobile);
        boolean isNumEmpty  = Validation.isEmpty(cUserType.getSelectionModel().getSelectedIndex());
        boolean isDigit     = Validation.isDigit(id,mobile);
        boolean isText     = Validation.isText(firstName,secondName);
        if(isTextEmpty == true ||isNumEmpty == true ){
            JOptionPane.showMessageDialog(null, "please Insert valid data :(");
            return;
        }
        if(isDigit == false ||isText == false ){
            JOptionPane.showMessageDialog(null, "please check data type :(");
            return;
        }
        // validation end
        UsersVo uvToInjectIntoDaolist = new UsersVo();
        uvToInjectIntoDaolist.setId(Integer.valueOf(id));
    
        try {
            int Count = UsersDao.getInstance().delete(uvToInjectIntoDaolist);
            if (Count == 1){
                JOptionPane.showMessageDialog(null, "Delete Successfully");
                reset();
            }else{
                JOptionPane.showMessageDialog(null, "Delete faild  :( ");            
            }
        } catch (Exception ex) {
            Logger.getLogger(UdUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void getRowByClickIdFiald(ActionEvent event) {
        try {
            String id           = txtId.getText() ;
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
                getLabelCount().setText(String.valueOf(indexOfPressedNumber)); 
            } else {
                JOptionPane.showMessageDialog(null, "ID Does'nt Exeist :-(");
                reset();
            }
        } catch (Exception ex) {
            Logger.getLogger(UdUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void nextRow(ActionEvent event) {
        int getHiddenLabel = Integer.parseInt(getLabelCount().getText());
        getHiddenLabel ++;
        showInfo(getHiddenLabel);
        getLabelCount().setText(String.valueOf(getHiddenLabel));
    }
    
    @FXML
    private void prevRow(ActionEvent event) {
        int getHiddenLabel = Integer.parseInt(getLabelCount().getText());
        getHiddenLabel --;
        showInfo(getHiddenLabel);
        getLabelCount().setText(String.valueOf(getHiddenLabel));
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
            Logger.getLogger(UdUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void reset(){
        txtId.setText("");
        txtUserName.setText("");
        txtPassword.setText("");
        txtFirstName.setText("");
        txtSecondName.setText("");
        txtMobile.setText("");
        getLabelCount().setText("0");
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

    public Label getLabelCount() {
        return labelCount;
    }

    public void setLabelCount(Label labelCount) {
        this.labelCount = labelCount;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getcUserType().setItems( FXCollections.observableArrayList( "ADMIN","DOCTOR","NURSE"));
        getcUserType().getSelectionModel().selectFirst();
        //System.out.println(getTxtUserName());
        //set filcor
        Funcs.textFaildsStyle("-fx-text-fill:#673AB7;-fx-background-color:#fff", getTxtId(),getTxtUserName(),getTxtPassword(),getTxtFirstName(),getTxtSecondName(),getTxtMobile());
        getcUserType().setStyle("-fx-font-size:16;-fx-background-color:#fff");

        //SET COMBBOX SELECTED COLOR START
        getcUserType().buttonCellProperty().bind(Bindings.createObjectBinding(() -> {
            return new ListCell<String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setBackground(Background.EMPTY);
                        setText("");
                    } else {
                        setText(item);
                        setTextFill(Color.valueOf("#673AB7"));
                    }
                }
            };
        }, getcUserType().valueProperty()));
        //SET COMBBOX SELECTED COLOR END
    }
}
