/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rohanayan
 */

package controller.dock;

import controller.MainController;
import controller.MyWindow;
import controller.ThemeController;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SceneController extends MyWindow implements Initializable, ThemeController{
    
    private MainController mainController;
    private TextInputDialog dialog = new TextInputDialog();
    ObservableList<String> listOfScenes = FXCollections.observableArrayList();
    
    @FXML
    private VBox sceneWindow;
    
    @FXML
    private ToolBar tlbrTop;
    
     @FXML
    private ToolBar tlbrBtm;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnPlus;

    @FXML
    private Button btnMinus;

    @FXML
    private Button btnUp;

    @FXML
    private Button btnDown;
    
    @FXML
    private HBox topButtonHolder;
    
    @FXML
    private HBox btmButtonHolder;
    
    @FXML
    private Label lblTop;
    
    @FXML
    private ListView<String> lvScene;

    
    public VBox getParent(){
        return sceneWindow;
    }
    
    public void disableBtnClose(boolean b){
        btnClose.setDisable(b);
    }
    
    public void setMainController(MainController mainController){
        this.mainController = mainController;
    }
    
    public void setBtmButtonHolderVisible(boolean b){
        btmButtonHolder.setVisible(b);
    }
    
    @FXML
    public void btnClosePressed(ActionEvent event) {
        mainController.getDocksHolder().getChildren().remove(sceneWindow);
        mainController.getScenes().setSelected(false);
    }

    @FXML
    public void btnDownPressed(ActionEvent event) {
        try {
            int selectedIndex = lvScene.getSelectionModel().getSelectedIndices().get(0);
            int nextIndex = selectedIndex + 1;
            nextIndex = nextIndex % listOfScenes.size();
            String itemAtSelectedIndex = listOfScenes.get(selectedIndex);
            listOfScenes.remove(selectedIndex);
            listOfScenes.add(nextIndex, itemAtSelectedIndex);
        } catch (ArrayIndexOutOfBoundsException | ArithmeticException e) {
        }
    }

    @FXML
    public void btnMinusPressed(ActionEvent event) {
        listOfScenes.remove(lvScene.getSelectionModel().getSelectedItems().get(0));
    }

    @FXML
    public void btnPlusPressed(ActionEvent event) {
        dialog.getEditor().clear();
        dialog.setTitle("Add Scene");
        dialog.setHeaderText(null);
        dialog.setGraphic(null);
        dialog.setContentText("Please enter the name of the scene:");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            listOfScenes.add(result.get());
        }
    }

    @FXML
    public void btnUpPressed(ActionEvent event) {
        try {
            int selectedIndex = lvScene.getSelectionModel().getSelectedIndices().get(0);
            int prevIndex = selectedIndex - 1;
            prevIndex = prevIndex % listOfScenes.size();
            String itemAtSelectedIndex = listOfScenes.get(selectedIndex);
            listOfScenes.remove(selectedIndex);
            listOfScenes.add(prevIndex, itemAtSelectedIndex);
        } catch (ArrayIndexOutOfBoundsException | ArithmeticException e) {
        }
    }
    
    @Override
    public void setTheme(String c1, String c2){
        final String IDLE_BUTTON_STYLE = "-fx-background-color: #FFFFFF;";
        final String HOVERED_BUTTON_STYLE = "-fx-background-color: linear-gradient(" + c1 + ", " + c2 + ");";

        sceneWindow.setStyle("-fx-background-color: linear-gradient(" + c1 + ", " + c2 + ");");
        tlbrTop.setStyle("-fx-background-color: linear-gradient(" + c2 + ", " + c1 + ");");
        tlbrBtm.setStyle("-fx-background-color: linear-gradient(" + c2 + ", " + c1 + ");");

        applyTheme(btmButtonHolder, IDLE_BUTTON_STYLE, HOVERED_BUTTON_STYLE);
        applyTheme(topButtonHolder, IDLE_BUTTON_STYLE, HOVERED_BUTTON_STYLE);
    }
    
    public void setTheme(String c1, String c2, boolean resetTheme){
        final String IDLE_BUTTON_STYLE = "-fx-background-color: #FFFFFF;";
        final String HOVERED_BUTTON_STYLE = "-fx-background-color: linear-gradient(" + c1 + ", " + c2 + ");";

        sceneWindow.setStyle("-fx-background-color: linear-gradient(" + c1 + ", " + c2 + ");");
        tlbrTop.setStyle("-fx-background-color: linear-gradient(" + c2 + ", " + c1 + ");");
        tlbrBtm.setStyle("-fx-background-color: linear-gradient(" + c2 + ", " + c1 + ");");

        applyTheme(btmButtonHolder, IDLE_BUTTON_STYLE, HOVERED_BUTTON_STYLE);
        applyTheme(topButtonHolder, IDLE_BUTTON_STYLE, HOVERED_BUTTON_STYLE);
    }
    
    public void setTheme(boolean labelIsBlack){
        if(labelIsBlack){
            lblTop.setStyle("-fx-text-fill: black;");
        }else{
            lblTop.setStyle("-fx-text-fill: white;");
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lvScene.setItems(listOfScenes);
        
        final Tooltip plusTooltip = new Tooltip("Add scene");
        final Tooltip minusTooltip = new Tooltip("Delete selected scene");
        final Tooltip upTooltip = new Tooltip("Move selected scene up");
        final Tooltip downTooltip = new Tooltip("Move selecte scene down");
        final Tooltip closeTooltip = new Tooltip("Close");
        btnClose.setTooltip(closeTooltip);
        btnPlus.setTooltip(plusTooltip);
        btnMinus.setTooltip(minusTooltip);
        btnUp.setTooltip(upTooltip);
        btnDown.setTooltip(downTooltip);
    }
}
