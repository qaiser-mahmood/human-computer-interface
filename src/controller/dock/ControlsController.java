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
import controller.MyWindow;
import controller.ThemeController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ControlsController extends MyWindow implements Initializable, ThemeController{

    private MainController mainController;
    private static int studioToggle = 0;
    private static int streamToggle = 0;
    private static int recordingToggle = 0;
    private Image screenImage;
    private Image studioImage;
    private Image streamImage;
    private Image stopImage;
    private Image stopImage2;
    private Image recordImage;
    private ImageView screenImageView;
    private ImageView studioImageView;
    private ImageView streamImageView;
    private ImageView stopImageView;
    private ImageView stopImageView2;
    private ImageView recordImageView;
    
    @FXML
    private VBox controlsWindow;

    @FXML
    private ToolBar tlbrTop;

    @FXML
    private HBox topButtonHolder;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnStudio;

    @FXML
    private Button btnExit;

    @FXML
    private Button btnNewStream;

    @FXML
    private Button btnStartRecording;

    @FXML
    private Button btnSettings;

    @FXML
    private ToolBar tlbrBtm;
    
    @FXML
    private Label lblTop;

    public VBox getParent(){
        return controlsWindow;
    }
    
    public void disableBtnClose(boolean b){
        btnClose.setDisable(b);
    }
    
    public void setMainController(MainController mainController){
        this.mainController = mainController;
    }
    
    @FXML
    public void btnClosePressed(ActionEvent event) {
        mainController.getDocksHolder().getChildren().remove(controlsWindow);
        mainController.getControls().setSelected(false);
    }

    @FXML
    public void btnExitPressed(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    public void btnNewStreamPressed(ActionEvent event) {
        if(streamToggle == 0){
            CheckBox checkBox1 = mainController.getSettingsController().getCheckBoxStartStartStream();
            if(checkBox1.isSelected()){
                Alert alert1 = new Alert(Alert.AlertType.NONE, "Do you want to start stream?", ButtonType.YES, ButtonType.NO);
                alert1.setTitle("Start stream dialog");
                alert1.showAndWait();
                if (alert1.getResult() == ButtonType.YES) {
                    mainController.getScreenController().setLabelStreaming(true);
                    mainController.getStudioController().setLabelStreaming(true);
                    btnNewStream.setGraphic(stopImageView);
                    streamToggle = 1;
                    return;
                }
            }else{
                mainController.getScreenController().setLabelStreaming(true);
                mainController.getStudioController().setLabelStreaming(true);
                btnNewStream.setGraphic(stopImageView);
                streamToggle = 1;
                return;
            }
        }
        if(streamToggle == 1){
            CheckBox checkBox2 = mainController.getSettingsController().getCheckBoxStopStartStream();
            if(checkBox2.isSelected()){
                Alert alert2 = new Alert(Alert.AlertType.NONE, "Do you want to stop stream?", ButtonType.YES, ButtonType.NO);
                alert2.setTitle("Stop stream dialog");
                alert2.showAndWait();
                if (alert2.getResult() == ButtonType.YES) {
                    mainController.getScreenController().setLabelStreaming(false);
                    mainController.getStudioController().setLabelStreaming(false);
                    btnNewStream.setGraphic(streamImageView);
                    streamToggle = 0;
                }
            }else{
                mainController.getScreenController().setLabelStreaming(false);
                mainController.getStudioController().setLabelStreaming(false);
                btnNewStream.setGraphic(streamImageView);
                streamToggle = 0;
            }
        }
    }

    @FXML
    public void btnSettingsPressed(ActionEvent event) {
        mainController.showSettings();
    }

    @FXML
    public void btnStartRecordingPressed(ActionEvent event) {
        if(recordingToggle == 0){
            mainController.getScreenController().setLabelRecording(true);
            mainController.getStudioController().setLabelRecording(true);
            btnStartRecording.setGraphic(stopImageView2);
            recordingToggle = 1;
            return;
        }
        if(recordingToggle == 1){
            mainController.getScreenController().setLabelRecording(false);
            mainController.getStudioController().setLabelRecording(false);
            btnStartRecording.setGraphic(recordImageView);
            recordingToggle = 0;
        }
    }

    @FXML
    public void btnStudioPressed(ActionEvent event) {
        
        if(studioToggle == 0){
            btnStudio.setGraphic(screenImageView);
            mainController.getScreenHolder().getChildren().remove(0);
            mainController.getScreenHolder().getChildren().add(0, mainController.getStudioController().getParent());
            studioToggle = 1;
            return;
        }
        if(studioToggle == 1){
            btnStudio.setGraphic(studioImageView);
            mainController.getScreenHolder().getChildren().remove(0);
            mainController.getScreenHolder().getChildren().add(0, mainController.getScreenController().getParent());
            studioToggle = 0;
        }
    }

    @Override
    public void setTheme(String c1, String c2){
        final String IDLE_BUTTON_STYLE = "-fx-background-color: #FFFFFF;";
        final String HOVERED_BUTTON_STYLE = "-fx-background-color: linear-gradient(" + c1 + ", " + c2 + ");";

        controlsWindow.setStyle("-fx-background-color: linear-gradient(" + c1 + ", " + c2 + ");");
        tlbrTop.setStyle("-fx-background-color: linear-gradient(" + c2 + ", " + c1 + ");");
        tlbrBtm.setStyle("-fx-background-color: linear-gradient(" + c2 + ", " + c1 + ");");

        applyTheme(topButtonHolder, IDLE_BUTTON_STYLE, HOVERED_BUTTON_STYLE);
        applyTheme(btnNewStream, IDLE_BUTTON_STYLE, HOVERED_BUTTON_STYLE);
        applyTheme(btnExit, IDLE_BUTTON_STYLE, HOVERED_BUTTON_STYLE);
        applyTheme(btnSettings, IDLE_BUTTON_STYLE, HOVERED_BUTTON_STYLE);
        applyTheme(btnStartRecording, IDLE_BUTTON_STYLE, HOVERED_BUTTON_STYLE);
        applyTheme(btnStudio, IDLE_BUTTON_STYLE, HOVERED_BUTTON_STYLE);
    }
    
    @Override
    public void setTheme(String c1, String c2, boolean resetTheme){
        final String IDLE_BUTTON_STYLE = "-fx-background-color: #FFFFFF;";
        final String HOVERED_BUTTON_STYLE = "-fx-background-color: linear-gradient(" + c1 + ", " + c2 + ");";

        controlsWindow.setStyle("-fx-background-color: linear-gradient(" + c1 + ", " + c2 + ");");
        tlbrTop.setStyle("-fx-background-color: linear-gradient(" + c2 + ", " + c1 + ");");
        tlbrBtm.setStyle("-fx-background-color: linear-gradient(" + c2 + ", " + c1 + ");");

        applyTheme(topButtonHolder, IDLE_BUTTON_STYLE, HOVERED_BUTTON_STYLE);
        applyTheme(btnNewStream, IDLE_BUTTON_STYLE, HOVERED_BUTTON_STYLE);
        applyTheme(btnExit, IDLE_BUTTON_STYLE, HOVERED_BUTTON_STYLE);
        applyTheme(btnSettings, IDLE_BUTTON_STYLE, HOVERED_BUTTON_STYLE);
        applyTheme(btnStartRecording, IDLE_BUTTON_STYLE, HOVERED_BUTTON_STYLE);
        applyTheme(btnStudio, IDLE_BUTTON_STYLE, HOVERED_BUTTON_STYLE);
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
        screenImage = new Image(getClass().getResourceAsStream("/images/screen_mode.png"));
        screenImageView = new ImageView(screenImage);
        screenImageView.setFitWidth(50);
        screenImageView.setFitHeight(50);
        
        studioImage = new Image(getClass().getResourceAsStream("/images/studio_mode.png"));
        studioImageView = new ImageView(studioImage);
        studioImageView.setFitWidth(50);
        studioImageView.setFitHeight(50);
        
        streamImage = new Image(getClass().getResourceAsStream("/images/video_stream.png"));
        streamImageView = new ImageView(streamImage);
        streamImageView.setFitWidth(50);
        streamImageView.setFitHeight(50);
        
        recordImage = new Image(getClass().getResourceAsStream("/images/audio_stream.png"));
        recordImageView = new ImageView(recordImage);
        recordImageView.setFitWidth(50);
        recordImageView.setFitHeight(50);
        
        stopImage = new Image(getClass().getResourceAsStream("/images/stop.png"));
        stopImageView = new ImageView(stopImage);
        stopImageView.setFitWidth(50);
        stopImageView.setFitHeight(50);
        
        stopImage2 = new Image(getClass().getResourceAsStream("/images/stop.png"));
        stopImageView2 = new ImageView(stopImage2);
        stopImageView2.setFitWidth(50);
        stopImageView2.setFitHeight(50);
        
        final Tooltip streamTooltip = new Tooltip("Start/Stop streaming");
        final Tooltip recordTooltip = new Tooltip("Start/Stop recording");
        final Tooltip studioTooltip = new Tooltip("Screen/Studio mode");
        final Tooltip settingsTooltip = new Tooltip("Settings");
        final Tooltip exitTooltip = new Tooltip("Exit");
        final Tooltip closeTooltip = new Tooltip("Close");
        btnClose.setTooltip(closeTooltip);
        btnNewStream.setTooltip(streamTooltip);
        btnStartRecording.setTooltip(recordTooltip);
        btnStudio.setTooltip(studioTooltip);
        btnSettings.setTooltip(settingsTooltip);
        btnExit.setTooltip(exitTooltip);
    }
}
