/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dock;

import controller.MainController;
import controller.MyWindow;
import controller.ThemeController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author rohanayan
 */
public class SceneTransitionController extends MyWindow implements Initializable, ThemeController{

    private MainController mainController;
    ObservableList<String> listOfTransitions = FXCollections.observableArrayList("Cut", "Fade", "Swipe", "Slide", "Stinger", "Fade to Color", "Luma Wipe");
    ObservableList<String> listOfDirections = FXCollections.observableArrayList("Up", "Down", "Left", "Right", "Top Right", "Top Left", "Bottom Right", "Bottom Left");
    SpinnerValueFactory<Integer> duration;
    
    @FXML
    private VBox sceneTransitionWindow;
    
    @FXML
    private HBox topButtonHolder;

    @FXML
    private ToolBar tlbrTop;

    @FXML
    private Button btnClose;

    @FXML
    private Spinner<Integer> spinnerDuration;

    @FXML
    private ComboBox<String> comboTransition;

    @FXML
    private Button btnMinus;

    @FXML
    private TextField tfTransitionName;

    @FXML
    private ComboBox<String> comboDirection;

    @FXML
    private Button btnOk;

    @FXML
    private Button btnCancel;

    @FXML
    private ToolBar tlbrBtm;
    
    @FXML
    private Label header_label;
    
    @FXML
    private Label lblMsg;
    
    @FXML
    private Label lblTop;

    @FXML
    private Label lblDuration;

    @FXML
    private Label lblTransition;

    @FXML
    private Label lblName;
    
    @FXML
    private Label lblDirection;

    public VBox getParent(){
        return sceneTransitionWindow;
    }
    
    public void disableBtnClose(boolean b){
        btnClose.setDisable(b);
    }
    
    public void setMainController(MainController mainController){
        this.mainController = mainController;
    }
    
    @FXML
    public void btnCancelPressed(ActionEvent event) {
        lblMsg.setText("Not saved!");
    }

    @FXML
    public void btnClosePressed(ActionEvent event) {
        mainController.getDocksHolder().getChildren().remove(sceneTransitionWindow);
        mainController.getSceneTransition().setSelected(false);
    }

    @FXML
    public void btnMinusPressed(ActionEvent event) {
        listOfTransitions.remove(comboTransition.getValue());
        lblMsg.setText("Transition Deleted!");
        tfTransitionName.setText("");
    }

    @FXML
    public void btnOkPressed(ActionEvent event) {
        if(tfTransitionName.getText().equals("")){
            lblMsg.setText("Not saved!...No Transition Name.");
        }else{
            listOfTransitions.add(tfTransitionName.getText());
            lblMsg.setText("Transition Saved!");
        }
    }
    
    @FXML
    void tfTransitionNameClicked(MouseEvent event) {
        lblMsg.setText("");
        tfTransitionName.setText("");
    }
    
    @Override
    public void setTheme(String c1, String c2){
        final String IDLE_BUTTON_STYLE = "-fx-background-color: #FFFFFF;";
        final String HOVERED_BUTTON_STYLE = "-fx-background-color: linear-gradient(" + c1 + ", " + c2 + ");";
        
        sceneTransitionWindow.setStyle("-fx-background-color: linear-gradient(" + c1 + ", " + c2 + ");");
        tlbrTop.setStyle("-fx-background-color: linear-gradient(" + c2 + ", " + c1 + ");");
        tlbrBtm.setStyle("-fx-background-color: linear-gradient(" + c2 + ", " + c1 + ");");
        
        applyTheme(topButtonHolder, IDLE_BUTTON_STYLE, HOVERED_BUTTON_STYLE);
        applyTheme(btnCancel, IDLE_BUTTON_STYLE, HOVERED_BUTTON_STYLE);
        applyTheme(btnOk, IDLE_BUTTON_STYLE, HOVERED_BUTTON_STYLE);
        applyTheme(btnMinus, IDLE_BUTTON_STYLE, HOVERED_BUTTON_STYLE);
    }
    
    public void setTheme(String c1, String c2, boolean resetTheme){
        final String IDLE_BUTTON_STYLE = "-fx-background-color: #FFFFFF;";
        final String HOVERED_BUTTON_STYLE = "-fx-background-color: linear-gradient(" + c1 + ", " + c2 + ");";

        sceneTransitionWindow.setStyle("-fx-background-color: linear-gradient(" + c1 + ", " + c2 + ");");
        tlbrTop.setStyle("-fx-background-color: linear-gradient(" + c2 + ", " + c1 + ");");
        tlbrBtm.setStyle("-fx-background-color: linear-gradient(" + c2 + ", " + c1 + ");");
        
        applyTheme(topButtonHolder, IDLE_BUTTON_STYLE, HOVERED_BUTTON_STYLE);
        applyTheme(btnCancel, IDLE_BUTTON_STYLE, HOVERED_BUTTON_STYLE);
        applyTheme(btnOk, IDLE_BUTTON_STYLE, HOVERED_BUTTON_STYLE);
        applyTheme(btnMinus, IDLE_BUTTON_STYLE, HOVERED_BUTTON_STYLE);
    }
    
    public void setTheme(boolean labelIsBlack){
        if(labelIsBlack){
            lblTop.setStyle("-fx-text-fill: black;");
            lblDirection.setStyle("-fx-text-fill: black;");
            lblDuration.setStyle("-fx-text-fill: black;");
            lblName.setStyle("-fx-text-fill: black;");
            lblTransition.setStyle("-fx-text-fill: black;");
        }else{
            lblTop.setStyle("-fx-text-fill: white;");
            lblDirection.setStyle("-fx-text-fill: white;");
            lblDuration.setStyle("-fx-text-fill: white;");
            lblName.setStyle("-fx-text-fill: white;");
            lblTransition.setStyle("-fx-text-fill: white;");
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboTransition.setItems(listOfTransitions);
        comboDirection.setItems(listOfDirections);
        comboTransition.setValue(listOfTransitions.get(0));
        comboDirection.setValue(listOfDirections.get(0));
        duration = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 300);
        spinnerDuration.setValueFactory(duration);
        
        final Tooltip deletTooltip = new Tooltip("Delete selected transition");
        final Tooltip closeTooltip = new Tooltip("Close");
        btnClose.setTooltip(closeTooltip);
        btnMinus.setTooltip(deletTooltip);
    }
    
}
