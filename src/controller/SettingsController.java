/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author rohanayan
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SettingsController extends MyWindow implements Initializable, ThemeController{

    private MainController mainController;
    private String defaultColor1 = "";
    private String defaultColor2 = "";
    private static int showKeyToggle = 0;
    ObservableList<String> listOfServer = FXCollections.observableArrayList("Auto");
    ObservableList<String> listOfService = FXCollections.observableArrayList("Custom", "Twitch", "Youtube", "Smashcast", "Facebook Live", "Twitter");
    ObservableList<String> listOfDownFilter = FXCollections.observableArrayList("Bilinear (Fastest, but blurry if scaling)", "Bicubic (Sharpened scaling, 16 samples)", "Lanczos (Sharpened scaling, 32 samples)");
    ObservableList<String> listOfBaseResolution = FXCollections.observableArrayList("1920x1220", "1920x1080", "1280x720");
    ObservableList<String> listOfOutputResolution = FXCollections.observableArrayList("1920x1080", "1536x864", "1440x810", "1280x720");
    
    @FXML
    private VBox settingsWindow;
    
    @FXML
    private VBox settingsGeneral;
    
    @FXML
    private Pane generalTopPane;
    
    @FXML
    private Pane generalBtmPane;
    
    @FXML
    private Label lblErrorMessage;
    
    @FXML
    private AnchorPane settingsStream;
    
    @FXML
    private AnchorPane settingsVideo;
    
    @FXML
    private SplitPane splitPane;

    @FXML
    private Button btnGeneral;

    @FXML
    private Button btnStream;

    @FXML
    private Button btnVideo;
    
    @FXML
    private Button btnOk;
    
    @FXML
    private Button btnCancel;

    @FXML
    private RadioButton defaultTheme;

    @FXML
    private ColorPicker colorSelector;

    @FXML
    private ToolBar tlbrBtm;
    
    @FXML
    private ComboBox<String> comboService;

    @FXML
    private ComboBox<String> comboServer;

    @FXML
    private PasswordField psdStreamKey;

    @FXML
    private Button btnShowKey;

    @FXML
    private CheckBox checkDialogStartStream;

    @FXML
    private CheckBox checkDialogStopStream;

    @FXML
    private ComboBox<String> comboDownFilter;

    @FXML
    private ComboBox<String> comboBaseResolution;

    @FXML
    private ComboBox<String> comboOutputResolution;
    
    @FXML
    private TextField tfStreamKey;
    
    @FXML
    private Label lblService;

    @FXML
    private Label lblServer;

    @FXML
    private Label lblStreamKey;
    
    @FXML
    private Label lblBaseResolution;

    @FXML
    private Label lblOutputResolution;

    @FXML
    private Label lblDownscaleFilter;
    
    @FXML
    private Label lblGeneral;
    
    @FXML
    private Label lblOutput;
    
    public void setMainController(MainController mainController){
        this.mainController = mainController;
    }
    
    public CheckBox getCheckBoxStartStartStream(){
        return checkDialogStartStream;
    }
    
    public CheckBox getCheckBoxStopStartStream(){
        return checkDialogStopStream;
    }
    
    @FXML
    void onComboBaseResolutionPressed(ActionEvent event) {
        String resolution = comboBaseResolution.getValue();
        if(resolution.matches("\\d+x\\d+")){
            lblErrorMessage.setText("");
            comboBaseResolution.setValue(resolution);
        }else{
            comboBaseResolution.setValue("");
            lblErrorMessage.setText("Resolution must be in the format [number]x[number]");
        }
       
    }

    @FXML
    void onComboOutputResolutionPressed(ActionEvent event) {
        String resolution = comboOutputResolution.getValue();
        if(resolution.matches("\\d+x\\d+")){
            lblErrorMessage.setText("");
            comboOutputResolution.setValue(resolution);
        }else{
            comboOutputResolution.setValue("");
            lblErrorMessage.setText("Resolution must be in the format [number]x[number]");
        }
    }
    
    @FXML
    void onComboServicePressed(ActionEvent event) {
        switch(comboService.getSelectionModel().getSelectedIndex()){
            case 1:
                comboServer.setEditable(false);
                listOfServer.clear();
                listOfServer.add("Australia: Sydney");
                listOfServer.add("Asia: Singapore");
                listOfServer.add("Europe: France, Paris");
                listOfServer.add("US West: Los Angeles, CA");
                listOfServer.add("US East: Miami, FL");
                comboServer.setValue(listOfServer.get(0));
                break;
            case 2:
                comboServer.setEditable(false);
                listOfServer.clear();
                listOfServer.add("Primary YouTube ingest server");
                listOfServer.add("Backup YouTube ingest server");
                comboServer.setValue(listOfServer.get(0));
                break;
            case 3:
                comboServer.setEditable(false);
                listOfServer.clear();
                listOfServer.add("Default");
                listOfServer.add("US-East: New York");
                listOfServer.add("Asia: Singapore");
                comboServer.setValue(listOfServer.get(0));
                break;
            case 4:
                comboServer.setEditable(false);
                listOfServer.clear();
                listOfServer.add("Default");
                comboServer.setValue(listOfServer.get(0));
                break;
            case 5:
                comboServer.setEditable(false);
                listOfServer.clear();
                listOfServer.add("US West: California");
                listOfServer.add("Asia/Pacific: Australia");
                listOfServer.add("South America: Brazil");
                comboServer.setValue(listOfServer.get(0));
                break;
            default:
                listOfServer.clear();
                comboServer.setEditable(true);
        }
    }
    
    @FXML
    void btnGeneralPressed(ActionEvent event) {
        settingsStream.setVisible(false);
        settingsVideo.setVisible(false);
        settingsGeneral.setVisible(true);
    }

    @FXML
    void btnSteamPressed(ActionEvent event) {
        settingsVideo.setVisible(false);
        settingsGeneral.setVisible(false);
        settingsStream.setVisible(true);
    }

    @FXML
    void btnVideoPressed(ActionEvent event) {
        settingsStream.setVisible(false);
        settingsGeneral.setVisible(false);
        settingsVideo.setVisible(true);
    }

    private Color getDarkColor(Color c){
        double darkness = 0.5;
        double r = c.getRed(); double g = c.getGreen(); double b = c.getBlue();
        r *= darkness; g *= darkness; b *= darkness;
        return new Color(r, g, b, 1.0);
    }
    
    @FXML
    void btnShowKeyPressed(ActionEvent event) {
        if(showKeyToggle == 0){
            btnShowKey.setText("Hide");
            psdStreamKey.setVisible(false);
            tfStreamKey.setText(psdStreamKey.getText());
            tfStreamKey.setVisible(true);
            showKeyToggle = 1;
            return;
        }
        if(showKeyToggle == 1){
            btnShowKey.setText("Show");
            psdStreamKey.setVisible(true);
            tfStreamKey.setVisible(false);
            showKeyToggle = 0;
        }
    }
    
    @FXML
    public void btnOkPressed(ActionEvent event) {
        if(mainController != null){
            if(defaultTheme.isSelected()){
                for(ThemeController tc: mainController.getListOfThemeControllers()){
                    tc.setTheme(defaultColor1, defaultColor2, true);
                    tc.setTheme(false);
                }
            }else{
            mainController.setTheme(colorSelector.getValue());
            }
        }
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
    
    @FXML
    public void btnCancelPressed(ActionEvent event) {
        if(checkDialogStartStream.isSelected()){
            checkDialogStartStream.setSelected(false);
        }
        if(checkDialogStopStream.isSelected()){
            checkDialogStopStream.setSelected(false);
        }
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
    
    @FXML
    public void colorSelectorPressed(ActionEvent event) {
        defaultTheme.setSelected(false);
        defaultColor1 = "";
        defaultColor2 = "";
    }

    @FXML
    public void defaultThemeSelected(ActionEvent event) {
        defaultColor1 = "#2A5058";
        defaultColor2 = "#61a2b1";
    }

    public ColorPicker getColorPicker(){
        return colorSelector;
    }

    @Override
    public void setTheme(String c1, String c2) {
        final String IDLE_BUTTON_STYLE = "-fx-background-color: #FFFFFF;";
        final String HOVERED_BUTTON_STYLE = "-fx-background-color: linear-gradient(" + c1 + ", " + c2 + ");";

        settingsWindow.setStyle("-fx-background-color: linear-gradient(" + c1 + ", " + c2 + ");");
        settingsGeneral.setStyle("-fx-background-color: linear-gradient(" + c1 + ", " + c2 + ");");
        splitPane.setStyle("-fx-background-color: linear-gradient(" + c1 + ", " + c2 + ");");        
        tlbrBtm.setStyle("-fx-background-color: linear-gradient(" + c2 + ", " + c1 + ");");

        applyTheme(btnCancel, IDLE_BUTTON_STYLE, HOVERED_BUTTON_STYLE);
        applyTheme(btnOk, IDLE_BUTTON_STYLE, HOVERED_BUTTON_STYLE);
        applyTheme(btnGeneral, IDLE_BUTTON_STYLE, HOVERED_BUTTON_STYLE);
        applyTheme(btnStream, IDLE_BUTTON_STYLE, HOVERED_BUTTON_STYLE);
        applyTheme(btnVideo, IDLE_BUTTON_STYLE, HOVERED_BUTTON_STYLE);
    }
    
    public void setTheme(String c1, String c2, boolean resetTheme) {
        final String IDLE_BUTTON_STYLE = "-fx-background-color: #FFFFFF;";
        final String HOVERED_BUTTON_STYLE = "-fx-background-color: linear-gradient(" + c1 + ", " + c2 + ");";

        settingsWindow.setStyle("-fx-background-color: linear-gradient(" + c1 + ", " + c2 + ");");
        settingsGeneral.setStyle("-fx-background-color: linear-gradient(" + c1 + ", " + c2 + ");");
        splitPane.setStyle("-fx-background-color: linear-gradient(" + c1 + ", " + c2 + ");");        
        tlbrBtm.setStyle("-fx-background-color: linear-gradient(" + c2 + ", " + c1 + ");");

        applyTheme(btnCancel, IDLE_BUTTON_STYLE, HOVERED_BUTTON_STYLE);
        applyTheme(btnOk, IDLE_BUTTON_STYLE, HOVERED_BUTTON_STYLE);
        applyTheme(btnGeneral, IDLE_BUTTON_STYLE, HOVERED_BUTTON_STYLE);
        applyTheme(btnStream, IDLE_BUTTON_STYLE, HOVERED_BUTTON_STYLE);
        applyTheme(btnVideo, IDLE_BUTTON_STYLE, HOVERED_BUTTON_STYLE);
    }
    
    public void setTheme(boolean labelIsBlack){
        if(labelIsBlack){
            lblGeneral.setStyle("-fx-text-fill: black;");
            lblOutput.setStyle("-fx-text-fill: black;");
            checkDialogStartStream.setStyle("-fx-text-fill: black;");
            checkDialogStopStream.setStyle("-fx-text-fill: black;");
            defaultTheme.setStyle("-fx-text-fill: black;");
            
            lblBaseResolution.setStyle("-fx-text-fill: black;");
            lblOutputResolution.setStyle("-fx-text-fill: black;");
            lblDownscaleFilter.setStyle("-fx-text-fill: black;");
            lblServer.setStyle("-fx-text-fill: black;");
            lblService.setStyle("-fx-text-fill: black;");
            lblStreamKey.setStyle("-fx-text-fill: black;");
        }else{
            lblGeneral.setStyle("-fx-text-fill: white;");
            lblOutput.setStyle("-fx-text-fill: white;");
            checkDialogStartStream.setStyle("-fx-text-fill: white;");
            checkDialogStopStream.setStyle("-fx-text-fill: white;");
            
            lblBaseResolution.setStyle("-fx-text-fill: white;");
            lblOutputResolution.setStyle("-fx-text-fill: white;");
            lblDownscaleFilter.setStyle("-fx-text-fill: white;");
            lblServer.setStyle("-fx-text-fill: white;");
            lblService.setStyle("-fx-text-fill: white;");
            lblStreamKey.setStyle("-fx-text-fill: white;");
            defaultTheme.setStyle("-fx-text-fill: white;");
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        defaultColor1 = "#2A5058";
        defaultColor2 = "#61a2b1";
        defaultTheme.setSelected(true);
        comboServer.setItems(listOfServer);
        comboService.setItems(listOfService);
        comboDownFilter.setItems(listOfDownFilter);
        comboBaseResolution.setItems(listOfBaseResolution);
        comboOutputResolution.setItems(listOfOutputResolution);
        
        listOfServer.add("Australia: Sydney");
        listOfServer.add("Asia: Singapore");
        listOfServer.add("Europe: France, Paris");
        listOfServer.add("US West: Los Angeles, CA");
        listOfServer.add("US East: Miami, FL");
        
        comboService.setValue(listOfService.get(1));
        comboServer.setValue(listOfServer.get(0));
        comboDownFilter.setValue(listOfDownFilter.get(0));
        comboBaseResolution.setValue(listOfBaseResolution.get(0));
        comboOutputResolution.setValue(listOfOutputResolution.get(0));
    }
}
