/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dock;

/**
 *
 * @author rohanayan
 */

import controller.MainController;
import controller.ThemeController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class StudioController implements Initializable, ThemeController{
    private MainController mainController;
    
    @FXML
    private VBox studioBox;
    
    @FXML
    private Label lblRecording;

    @FXML
    private Label lblStreaming;
    
    @FXML
    private Label lblPreview;

    @FXML
    private Label lblLive;

    
    public void setMainController(MainController mainController){
        this.mainController = mainController;
    }
    
    public VBox getParent(){
        return studioBox;
    }
    
    public void setLabelStreaming(boolean b){
        lblStreaming.setVisible(b);
    }
    
    public void setLabelRecording(boolean b){
        lblRecording.setVisible(b);
    }

    @Override
    public void setTheme(String c1, String c2) {
        studioBox.setStyle("-fx-background-color: linear-gradient(" + c1 + ", " + c2 + ");");
    }
    
    public void setTheme(String c1, String c2, boolean resetTheme) {
        studioBox.setStyle("-fx-background-color: linear-gradient(" + c1 + ", " + c2 + ");");
    }
    
    public void setTheme(boolean labelIsBlack){
        if(labelIsBlack){
            lblPreview.setStyle("-fx-text-fill: black;");
            lblLive.setStyle("-fx-text-fill: black;");
        }else{
            lblPreview.setStyle("-fx-text-fill: white;");
            lblLive.setStyle("-fx-text-fill: white;");
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}

