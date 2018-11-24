/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import com.hussam.hospital.db.dao.PatientInfoDao;
import com.hussam.hospital.db.vo.PatientInfoVo;
import com.hussam.hospital.tools.Funcs;
import com.hussam.hospital.validation.Validation;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author HA
 */
public class AddPatientController implements Initializable {
    @FXML
    private JFXTextField txtFirstName;

    @FXML
    private JFXTextField txtSecondName;

    @FXML
    private JFXTextField txtMobile;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXButton btnAdd;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Funcs.textFaildsStyle("-fx-text-fill:#673AB7;-fx-background-color:#fff",getTxtFirstName(),getTxtSecondName(),getTxtMobile(),getTxtEmail());
        getBtnAdd().addEventHandler(MouseEvent.MOUSE_PRESSED,(MouseEvent e)->{
            String firstName   = getTxtFirstName().getText();
            String secondName  = getTxtSecondName().getText();
            String mobile      = getTxtMobile().getText();
            String email       = getTxtEmail().getText();
            // validation start
            boolean isTextEmpty = Validation.isEmpty(firstName,secondName,mobile,email);
            boolean isDigit     = Validation.isDigit(mobile);
            boolean isText     = Validation.isText(firstName,secondName);
            if(isTextEmpty == true){
                JOptionPane.showMessageDialog(null, "please Insert valid data :(");
                return;
            }
            if(isDigit == false ||isText == false ){
                JOptionPane.showMessageDialog(null, "please check data type :-(");
                return;
            }
            // validation end 
            PatientInfoVo pivToInject = new PatientInfoVo() ;
            pivToInject.setFirstName(firstName);
            pivToInject.setSecondName(secondName);
            pivToInject.setMobile(mobile);
            pivToInject.setEmail(email);
            
            try {
                int count = PatientInfoDao.getInstance().insert(pivToInject);
                if (count == 1){
                    JOptionPane.showMessageDialog(null, "insert Successfully");
                    reset(); 
                } else {JOptionPane.showMessageDialog(null, "insert faild ");}
            } catch (Exception ex) {
                Logger.getLogger(AddPatientController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }  
    private void reset(){
        txtFirstName.setText("");
        txtSecondName.setText("");
        txtMobile.setText("");
        txtEmail.setText("");
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

    public JFXTextField getTxtEmail() {
        return txtEmail;
    }

    public void setTxtEmail(JFXTextField txtEmail) {
        this.txtEmail = txtEmail;
    }

    public JFXButton getBtnAdd() {
        return btnAdd;
    }

    public void setBtnAdd(JFXButton btnAdd) {
        this.btnAdd = btnAdd;
    }
    
    
    
}
