/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dock;

import controller.MainController;
import controller.ThemeController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 *
 * @author rohanayan
 */
public class ScreenController implements Initializable, ThemeController{
    private MainController mainController;
    
    @FXML
    private VBox screenBox;
    
    @FXML
    private Label lblStreaming;
    
    @FXML
    private Label lblRecording;
    
    public void setMainController(MainController mainController){
        this.mainController = mainController;
    }

    public VBox getParent(){
        return screenBox;
    }
    
    public void setLabelStreaming(boolean b){
        lblStreaming.setVisible(b);
    }
    
    public void setLabelRecording(boolean b){
        lblRecording.setVisible(b);
    }
    
    @Override
    public void setTheme(String c1, String c2) {
       screenBox.setStyle("-fx-background-color: linear-gradient(" + c1 + ", " + c2 + ");");
    }
    
    public void setTheme(String c1, String c2, boolean resetTheme) {
       screenBox.setStyle("-fx-background-color: linear-gradient(" + c1 + ", " + c2 + ");");
    }
    
    public void setTheme(boolean labelIsBlack){
        
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

}
