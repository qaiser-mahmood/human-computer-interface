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
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author rohanayan
 */
public class MixerController extends MyWindow implements Initializable, ThemeController{
    private static int micToggle = 0;
    private static int volumeToggle = 0;
    private MainController mainController;
    private Image micImage;
    private Image micMuteImage;
    private Image volumeImage;
    private Image volumeMuteImage;
    
    private ImageView micImageView;
    private ImageView micMuteImageView;
    private ImageView volumeImageView;
    private ImageView volumeMuteImageView;
    
    @FXML
    private VBox mixerWindow;

    @FXML
    private ToolBar tlbrTop;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnVolume;

    @FXML
    private Button btnMic;

    @FXML
    private ToolBar tlbrBtm;
    
    @FXML
    private HBox buttonHolder;
    
    @FXML
    private HBox topButtonHolder;
    
     @FXML
    private Label lblAudio;

    @FXML
    private Label lblMic;
    
    @FXML
    private Slider sliderMic;
    
    @FXML
    private Slider sliderVolume;
    
    @FXML
    private Label lblTop;
    
    @FXML
    private Label lblDestopAudio;

    @FXML
    private Label lblMicAudio;

    
    public VBox getParent(){
        return mixerWindow;
    }
    
    public void setMainController(MainController mainController){
        this.mainController = mainController;
    }
    
    public void disableBtnClose(boolean b){
        btnClose.setDisable(b);
    }
    
    @FXML
    public void btnClosePressed(ActionEvent event) {
        mainController.getDocksHolder().getChildren().remove(mixerWindow);
        mainController.getMixer().setSelected(false);
    }

    @FXML
    public void btnMicPressed(ActionEvent event) {
        if(micToggle == 0){
            btnMic.setGraphic(micMuteImageView);
            micToggle = 1;
            return;
        }
        if(micToggle == 1){
            btnMic.setGraphic(micImageView);
            micToggle = 0;
            return;
        } 
    }

    @FXML
    public void btnVolumePressed(ActionEvent event) {
        if(volumeToggle == 0){
            btnVolume.setGraphic(volumeMuteImageView);
            volumeToggle = 1;
            return;
        }
        if(volumeToggle == 1){
            btnVolume.setGraphic(volumeImageView);
            volumeToggle = 0;
            return;
        } 
    }
    
    @FXML
    void onSliderMicDragged(MouseEvent event) {
        DecimalFormat df = new DecimalFormat();
        double d = Math.round(sliderMic.getValue());
        lblMic.setText(df.format(d));
    }
    
    @FXML
    void onSliderVolumeDragged(MouseEvent event) {
        DecimalFormat df = new DecimalFormat();
        double d = Math.round(sliderVolume.getValue());
        lblAudio.setText(df.format(d));
    }
    
    @Override
    public void setTheme(String c1, String c2){
        final String IDLE_BUTTON_STYLE = "-fx-background-color: #FFFFFF;";
        final String HOVERED_BUTTON_STYLE = "-fx-background-color: linear-gradient(" + c1 + ", " + c2 + ");";

        mixerWindow.setStyle("-fx-background-color: linear-gradient(" + c1 + ", " + c2 + ");");
        tlbrTop.setStyle("-fx-background-color: linear-gradient(" + c2 + ", " + c1 + ");");
        tlbrBtm.setStyle("-fx-background-color: linear-gradient(" + c2 + ", " + c1 + ");");

        applyTheme(topButtonHolder, IDLE_BUTTON_STYLE, HOVERED_BUTTON_STYLE);
        applyTheme(btnVolume, IDLE_BUTTON_STYLE, HOVERED_BUTTON_STYLE);
        applyTheme(btnMic, IDLE_BUTTON_STYLE, HOVERED_BUTTON_STYLE);
    }
    
    public void setTheme(String c1, String c2, boolean resetTheme){
        final String IDLE_BUTTON_STYLE = "-fx-background-color: #FFFFFF;";
        final String HOVERED_BUTTON_STYLE = "-fx-background-color: linear-gradient(" + c1 + ", " + c2 + ");";

        mixerWindow.setStyle("-fx-background-color: linear-gradient(" + c1 + ", " + c2 + ");");
        tlbrTop.setStyle("-fx-background-color: linear-gradient(" + c2 + ", " + c1 + ");");
        tlbrBtm.setStyle("-fx-background-color: linear-gradient(" + c2 + ", " + c1 + ");");

        applyTheme(topButtonHolder, IDLE_BUTTON_STYLE, HOVERED_BUTTON_STYLE);
        applyTheme(btnVolume, IDLE_BUTTON_STYLE, HOVERED_BUTTON_STYLE);
        applyTheme(btnMic, IDLE_BUTTON_STYLE, HOVERED_BUTTON_STYLE);
    }
    
    public void setTheme(boolean labelIsBlack){
        if(labelIsBlack){
            lblTop.setStyle("-fx-text-fill: black;");
            lblAudio.setStyle("-fx-text-fill: black;");
            lblMic.setStyle("-fx-text-fill: black;");
            lblDestopAudio.setStyle("-fx-text-fill: black;");
            lblMicAudio.setStyle("-fx-text-fill: black;");
        }else{
            lblTop.setStyle("-fx-text-fill: white;");
            lblAudio.setStyle("-fx-text-fill: white;");
            lblMic.setStyle("-fx-text-fill: white;");
            lblDestopAudio.setStyle("-fx-text-fill: white;");
            lblMicAudio.setStyle("-fx-text-fill: white;");
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        micImage = new Image(getClass().getResourceAsStream("/images/mic.png"));
        micMuteImage = new Image(getClass().getResourceAsStream("/images/mic_mute.png"));
        volumeImage = new Image(getClass().getResourceAsStream("/images/volume.png"));
        volumeMuteImage = new Image(getClass().getResourceAsStream("/images/volume_mute.png"));
        
        micImageView = new ImageView(micImage);
        micMuteImageView = new ImageView(micMuteImage);
        volumeImageView = new ImageView(volumeImage);
        volumeMuteImageView = new ImageView(volumeMuteImage);
        
        double width = 20;
        double height = 20;
        
        micImageView.setFitWidth(width);
        micImageView.setFitHeight(height);
        micMuteImageView.setFitWidth(width);
        micMuteImageView.setFitHeight(height);
        volumeImageView.setFitWidth(width);
        volumeImageView.setFitHeight(height);
        volumeMuteImageView.setFitWidth(width);
        volumeMuteImageView.setFitHeight(height);
        
        final Tooltip volumeTooltip = new Tooltip("Mute/Unmute volume");
        final Tooltip micTooltip = new Tooltip("Mute/Unmute mic");
        final Tooltip closeTooltip = new Tooltip("Close");
        btnClose.setTooltip(closeTooltip);
        btnVolume.setTooltip(volumeTooltip);
        btnMic.setTooltip(micTooltip);
    }
    
}
