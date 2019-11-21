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
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class HelpScreenController implements Initializable{
    
    private MainController mainController;
    private WebEngine webEngine;

    @FXML
    private VBox helpBox;

    @FXML
    private WebView webviewHelp;
    
    public WebEngine getWebEngine(){
        return webEngine;
    }
    
    public void setMainController(MainController mainController){
        this.mainController = mainController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        webEngine = webviewHelp.getEngine();
    }

}
