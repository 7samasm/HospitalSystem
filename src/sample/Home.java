package sample;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Home implements Initializable {

    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXHamburger hamburger;
    
    @FXML
    private AnchorPane root;
    
    @FXML
    private Label addUserWindo;

    @FXML
    private Label updateDeleteWindo;
    
    @FXML
    private Label quiryWindow;
    
    @FXML
    private Label addPatientWindow;

    public static AnchorPane rootP;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rootP = root;
        
        try {
            VBox box = FXMLLoader.load(getClass().getResource("view/SidePanelContent.fxml"));
            drawer.setSidePane(box);
        } catch (IOException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
        transition.setRate(-1);
        
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED,(e)->{
            transition.setRate(transition.getRate()*-1);
            transition.play();
            if(drawer.isShown())
            drawer.close();
            else
            drawer.open();
        });
        
        //go to add window
        addUserWindo.addEventHandler(MouseEvent.MOUSE_PRESSED,(e)->{
            try {
                Parent root;
                Stage stage = new Stage();
                root = FXMLLoader.load(getClass().getResource("view/AddUser.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }           
        });
        //go to update & Delete window
        updateDeleteWindo.addEventHandler(MouseEvent.MOUSE_PRESSED,(e)->{
            try {
                Parent root;
                Stage stage = new Stage();
                root = FXMLLoader.load(getClass().getResource("view/UdUsers.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }           
        });
        //go to quiry window
        quiryWindow.addEventHandler(MouseEvent.MOUSE_PRESSED,(e)->{
            try {
                Parent root;
                Stage stage = new Stage();
                root = FXMLLoader.load(getClass().getResource("view/userQuiry.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }           
        });
        
        // AddPatient
        addPatientWindow.addEventHandler(MouseEvent.MOUSE_PRESSED,(e)->{
            try {
                Parent root;
                Stage stage = new Stage();
                root = FXMLLoader.load(getClass().getResource("view/AddPatient.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }           
        });
        
    }

}
