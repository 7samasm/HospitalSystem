/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import com.hussam.hospital.db.dao.UserDetailsDao;
import com.hussam.hospital.db.type.UsersType;
import com.hussam.hospital.db.vo.UserDetailsVo;
import com.hussam.hospital.db.vo.UsersVo;
import com.hussam.hospital.tools.Funcs;
import com.hussam.hospital.tools.Shapes;
import com.hussam.hospital.validation.Validation;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 *
 * @author HA
 */
public class AddUser implements Initializable {
    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtUserName;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    private JFXComboBox<String> cUserType =  new JFXComboBox<>();

    @FXML
    private JFXTextField txtFirstName;

    @FXML
    private JFXTextField txtSecondName;

    @FXML
    private JFXTextField txtMobile;

    @FXML
    private JFXButton btnAdd;
    
    @FXML
    private StackPane stackPane;

    @FXML
    void addNewUser(ActionEvent event) {
        //user vars
        String id          = txtId.getText() ;
        String userName    = txtUserName.getText();
        String password    = txtPassword.getText();
        UsersType userType = UsersType.getUserTypeByType(getcUserType().getValue().toLowerCase());
        //User Details vars
        String firstName   = txtFirstName.getText();
        String secondName  = txtSecondName.getText();
        String mobile      = txtMobile.getText();
        
        //validation start
        boolean isTextEmpty = Validation.isEmpty(id,userName,password,firstName,secondName,mobile);
        boolean isNumEmpty  = Validation.isEmpty(getcUserType().getSelectionModel().getSelectedIndex());
        boolean isDigit     = Validation.isDigit(id,mobile);
        boolean isText     = Validation.isText(firstName,secondName);
        if(isTextEmpty == true ||isNumEmpty == true ){
            Shapes.dialog(stackPane,"please Insert valid data :-(","Error");
            return;
        }
        if(isDigit == false ||isText == false ){
            Shapes.dialog(stackPane,"please check data type :-(","Error");
            return;
        }
        // validation end 
        // set user vo object[uvToInjectIntoDaolist] to inject into Dao list methods[getData,insert,delete] 
        
        UsersVo uvToInjectIntoDaolist = new UsersVo();
        uvToInjectIntoDaolist.setId(Integer.valueOf(id));
        uvToInjectIntoDaolist.setUserName(userName);
        uvToInjectIntoDaolist.setPassword(password);
        uvToInjectIntoDaolist.setUserType(userType);
        
        //set User Details Vo object[udvToInjectIntoDaolist] to inject into Dao list methods[getData,insert,delete] 
        
        UserDetailsVo udvToInjectIntoDaolist = new UserDetailsVo();
        udvToInjectIntoDaolist.setUservo(uvToInjectIntoDaolist);
        udvToInjectIntoDaolist.setFirstName(firstName);
        udvToInjectIntoDaolist.setSecondName(secondName);
        udvToInjectIntoDaolist.setMobile(mobile);
        
        try {
            //int usersCount       = UsersDao.getInstance().insert(uvToInjectIntoDaolist);
            int Count = UserDetailsDao.getInstance().insert(udvToInjectIntoDaolist);
            if (Count == 1){
                Shapes.dialog(stackPane,"Insert Successfully","Done");
                Funcs.reset(getTxtId(),getTxtUserName(),getTxtPassword(),getTxtFirstName(),getTxtSecondName(),getTxtMobile());
            }else{
                Shapes.dialog(stackPane,"Insert faild :-(","Error");            
            }
        } catch (Exception ex) {
        }
    }
    
    @FXML
    void ToggleItems(MouseEvent event) {
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

    public void setcUserType(JFXComboBox<String> cUserType) {
        this.cUserType = cUserType;
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

    public JFXButton getBtnAdd() {
        return btnAdd;
    }

    public void setBtnAdd(JFXButton btnAdd) {
        this.btnAdd = btnAdd;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getcUserType().setItems( FXCollections.observableArrayList( "ADMIN","DOCTOR","NURSE"));
        getcUserType().getSelectionModel().selectFirst();
        
        //set filcor
        Funcs.textFaildsStyle("-fx-text-fill:#673AB7;-fx-background-color:#fff", getTxtId(),getTxtUserName(),getTxtPassword(),getTxtFirstName(),getTxtSecondName(),getTxtMobile());
        getcUserType().setStyle("-fx-font-size:16;-fx-background-color:#fff");
        System.out.println(getcUserType().getItems());
        
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
