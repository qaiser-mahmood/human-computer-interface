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
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Window;

/**
 *
 * @author rohanayan
 */
public class SourceController extends MyWindow implements Initializable, ThemeController{

    private MainController mainController;
    ObservableList<String> listOfSources = FXCollections.observableArrayList();
    MenuItem audioCaptureDevice = new MenuItem("Audio Capture Device");
    MenuItem audioInputCapture = new MenuItem("Audio Input Capture");
    MenuItem audioOutputCapture = new MenuItem("Audio Output Capture");
    MenuItem colorSource = new MenuItem("Color Source");
    MenuItem image = new MenuItem("Image");
    MenuItem imageSlideShow = new MenuItem("Image Slide Show");
    MenuItem jackInputClient = new MenuItem("JACK Input Client");
    MenuItem mediaSource = new MenuItem("Media Source");
    MenuItem screenCapture = new MenuItem("Screen Capture");
    MenuItem text = new MenuItem("Text (Free Type 2)");
    MenuItem vlcVideoSource = new MenuItem("VLC Video Source");
    MenuItem videoCaptureDevice = new MenuItem("Video Capture Device");
    MenuItem windowCapture = new MenuItem("Window Capture");
    private ContextMenu contextMenu = new ContextMenu();
    private TextInputDialog dialog = new TextInputDialog();
    
    @FXML
    private VBox sourceWindow;

    @FXML
    private ToolBar tlbrTop;

    @FXML
    private Button btnClose;

    @FXML
    private ToolBar tlbrBtm;

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
    private ListView<String> lvSource;


    public VBox getParent(){
        return sourceWindow;
    }
    
    public void disableBtnClose(boolean b){
        btnClose.setDisable(b);
    }
    
    public void setMainController(MainController mainController){
        this.mainController = mainController;
    }
    
    public String getSelectedSource(){
        return lvSource.getSelectionModel().getSelectedItems().get(0);
    }
    
    public void addToListOfSources(String s){
        listOfSources.add(s);
    }
    
    public void moveUpListOfSources(String s){
        try {
            int selectedIndex = listOfSources.indexOf(s);
            int prevIndex = selectedIndex - 1;
            prevIndex = prevIndex % listOfSources.size();
            String itemAtSelectedIndex = listOfSources.get(selectedIndex);
            listOfSources.remove(selectedIndex);
            listOfSources.add(prevIndex, itemAtSelectedIndex);
        } catch (ArrayIndexOutOfBoundsException | ArithmeticException e) {
        }
    }
    
    public void moveDownListOfSources(String s){
        try {
            int selectedIndex = listOfSources.indexOf(s);
            int nextIndex = selectedIndex + 1;
            nextIndex = nextIndex % listOfSources.size();
            String itemAtSelectedIndex = listOfSources.get(selectedIndex);
            listOfSources.remove(selectedIndex);
            listOfSources.add(nextIndex, itemAtSelectedIndex);
        } catch (ArrayIndexOutOfBoundsException | ArithmeticException e) {
        }
    }
    
    public void moveToTopListOfSources(String s){
        try {
            int selectedIndex = listOfSources.indexOf(s);
            String itemAtSelectedIndex = listOfSources.get(selectedIndex);
            listOfSources.remove(selectedIndex);
            listOfSources.add(0, itemAtSelectedIndex);
        } catch (ArrayIndexOutOfBoundsException | ArithmeticException e) {
        }
    }
    
    public void moveToBottomListOfSources(String s){
        try {
            int selectedIndex = listOfSources.indexOf(s);
            String itemAtSelectedIndex = listOfSources.get(selectedIndex);
            listOfSources.remove(selectedIndex);
            listOfSources.add(listOfSources.size()-1, itemAtSelectedIndex);
        } catch (ArrayIndexOutOfBoundsException | ArithmeticException e) {
        }
    }
    
    public void setBtmButtonHolderVisible(boolean b){
        btmButtonHolder.setVisible(b);
    }
    
    @FXML
    public void btnClosePressed(ActionEvent event) {
        mainController.getDocksHolder().getChildren().remove(sourceWindow);
        mainController.getSources().setSelected(false);
    }

    @FXML
    public void btnDownPressed(ActionEvent event) {
        try {
            int selectedIndex = lvSource.getSelectionModel().getSelectedIndices().get(0);
            int nextIndex = selectedIndex + 1;
            nextIndex = nextIndex % listOfSources.size();
            String itemAtSelectedIndex = listOfSources.get(selectedIndex);
            listOfSources.remove(selectedIndex);
            listOfSources.add(nextIndex, itemAtSelectedIndex);
        } catch (ArrayIndexOutOfBoundsException | ArithmeticException e) {
        }
    }

    @FXML
    public void btnMinusPressed(ActionEvent event) {
        listOfSources.remove(lvSource.getSelectionModel().getSelectedItems().get(0));
    }
    
    @FXML
    void btnPlusPressed(MouseEvent event) {
        Window window = ((Button)event.getSource()).getScene().getWindow();
        contextMenu.show(window, event.getScreenX(), event.getScreenY()-350);
    }

    @FXML
    public void btnUpPressed(ActionEvent event) {
        try {
            int selectedIndex = lvSource.getSelectionModel().getSelectedIndices().get(0);
            int prevIndex = selectedIndex - 1;
            prevIndex = prevIndex % listOfSources.size();
            String itemAtSelectedIndex = listOfSources.get(selectedIndex);
            listOfSources.remove(selectedIndex);
            listOfSources.add(prevIndex, itemAtSelectedIndex);
        } catch (ArrayIndexOutOfBoundsException | ArithmeticException e) {
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        contextMenu.getItems().addAll(audioCaptureDevice, audioInputCapture, audioOutputCapture, colorSource, image, imageSlideShow);
        contextMenu.getItems().addAll(jackInputClient, mediaSource, screenCapture, text, vlcVideoSource, videoCaptureDevice, windowCapture);
        audioCaptureDevice.setOnAction(audioCaptureDeviceHandler());
        audioInputCapture.setOnAction(audioInputCaptureHandler());
        audioOutputCapture.setOnAction(audioOutputCaptureHandler());
        colorSource.setOnAction(colorSourceHandler());
        image.setOnAction(imageHandler());
        imageSlideShow.setOnAction(imageSlideShowHandler());
        jackInputClient.setOnAction(jackInputClientHandler());
        mediaSource.setOnAction(mediaSourceHandler());
        screenCapture.setOnAction(screenCaptureHandler());
        text.setOnAction(textHandler());
        vlcVideoSource.setOnAction(vlcVideoSourceHandler());
        videoCaptureDevice.setOnAction(videoCaptureDeviceHandler());
        windowCapture.setOnAction(windowCaptureHandler());
        
        lvSource.setItems(listOfSources);
        
        final Tooltip plusTooltip = new Tooltip("Add source");
        final Tooltip minusTooltip = new Tooltip("Delete selected source");
        final Tooltip upTooltip = new Tooltip("Move selected source up");
        final Tooltip downTooltip = new Tooltip("Move selecte source down");
        final Tooltip closeTooltip = new Tooltip("Close");
        btnClose.setTooltip(closeTooltip);
        btnPlus.setTooltip(plusTooltip);
        btnMinus.setTooltip(minusTooltip);
        btnUp.setTooltip(upTooltip);
        btnDown.setTooltip(downTooltip);
    }
    
    private EventHandler<ActionEvent> audioCaptureDeviceHandler(){
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.getEditor().clear();
                dialog.setTitle("Create Source");
                dialog.setHeaderText(null);
                dialog.setGraphic(null);
                dialog.setContentText("Please enter the name of the source:");
                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()){
                    listOfSources.add(result.get());
                }
            }
        };
    }
    
    private EventHandler<ActionEvent> audioInputCaptureHandler(){
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.getEditor().clear();
                dialog.setTitle("Create Source");
                dialog.setHeaderText(null);
                dialog.setGraphic(null);
                dialog.setContentText("Please enter the name of the source:");
                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()){
                    listOfSources.add(result.get());
                }
            }
        };
    }
    
    private EventHandler<ActionEvent> audioOutputCaptureHandler(){
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.getEditor().clear();
                dialog.setTitle("Create Source");
                dialog.setHeaderText(null);
                dialog.setGraphic(null);
                dialog.setContentText("Please enter the name of the source:");
                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()){
                    listOfSources.add(result.get());
                }
            }
        };
    }
    
    private EventHandler<ActionEvent> colorSourceHandler(){
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.getEditor().clear();
                dialog.setTitle("Create Source");
                dialog.setHeaderText(null);
                dialog.setGraphic(null);
                dialog.setContentText("Please enter the name of the source:");
                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()){
                    listOfSources.add(result.get());
                }
            }
        };
    }
    
    private EventHandler<ActionEvent> imageHandler(){
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.getEditor().clear();
                dialog.setTitle("Create Source");
                dialog.setHeaderText(null);
                dialog.setGraphic(null);
                dialog.setContentText("Please enter the name of the source:");
                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()){
                    listOfSources.add(result.get());
                }
            }
        };
    }
    
    private EventHandler<ActionEvent> imageSlideShowHandler(){
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.getEditor().clear();
                dialog.setTitle("Create Source");
                dialog.setHeaderText(null);
                dialog.setGraphic(null);
                dialog.setContentText("Please enter the name of the source:");
                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()){
                    listOfSources.add(result.get());
                }
            }
        };
    }
    
    private EventHandler<ActionEvent> jackInputClientHandler(){
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.getEditor().clear();
                dialog.setTitle("Create Source");
                dialog.setHeaderText(null);
                dialog.setGraphic(null);
                dialog.setContentText("Please enter the name of the source:");
                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()){
                    listOfSources.add(result.get());
                }
            }
        };
    }
    
    private EventHandler<ActionEvent> mediaSourceHandler(){
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.getEditor().clear();
                dialog.setTitle("Create Source");
                dialog.setHeaderText(null);
                dialog.setGraphic(null);
                dialog.setContentText("Please enter the name of the source:");
                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()){
                    listOfSources.add(result.get());
                }
            }
        };
    }
    
    private EventHandler<ActionEvent> screenCaptureHandler(){
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.getEditor().clear();
                dialog.setTitle("Create Source");
                dialog.setHeaderText(null);
                dialog.setGraphic(null);
                dialog.setContentText("Please enter the name of the source:");
                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()){
                    listOfSources.add(result.get());
                }
            }
        };
    }
    
    private EventHandler<ActionEvent> textHandler(){
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.getEditor().clear();
                dialog.setTitle("Create Source");
                dialog.setHeaderText(null);
                dialog.setGraphic(null);
                dialog.setContentText("Please enter the name of the source:");
                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()){
                    listOfSources.add(result.get());
                }
            }
        };
    }
    
    private EventHandler<ActionEvent> vlcVideoSourceHandler(){
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.getEditor().clear();
                dialog.setTitle("Create Source");
                dialog.setHeaderText(null);
                dialog.setGraphic(null);
                dialog.setContentText("Please enter the name of the source:");
                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()){
                    listOfSources.add(result.get());
                }
            }
        };
    }
    
    private EventHandler<ActionEvent> videoCaptureDeviceHandler(){
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.getEditor().clear();
                dialog.setTitle("Create Source");
                dialog.setHeaderText(null);
                dialog.setGraphic(null);
                dialog.setContentText("Please enter the name of the source:");
                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()){
                    listOfSources.add(result.get());
                }
            }
        };
    }
    
    private EventHandler<ActionEvent> windowCaptureHandler(){
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.getEditor().clear();
                dialog.setTitle("Create Source");
                dialog.setHeaderText(null);
                dialog.setGraphic(null);
                dialog.setContentText("Please enter the name of the source:");
                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()){
                    listOfSources.add(result.get());
                }
            }
        };
    }
    
    @Override
    public void setTheme(String c1, String c2){
        final String IDLE_BUTTON_STYLE = "-fx-background-color: #FFFFFF;";
        final String HOVERED_BUTTON_STYLE = "-fx-background-color: linear-gradient(" + c1 + ", " + c2 + ");";
        
        sourceWindow.setStyle("-fx-background-color: linear-gradient(" + c1 + ", " + c2 + ");");
        tlbrTop.setStyle("-fx-background-color: linear-gradient(" + c2 + ", " + c1 + ");");
        tlbrBtm.setStyle("-fx-background-color: linear-gradient(" + c2 + ", " + c1 + ");");
        
        applyTheme(btmButtonHolder, IDLE_BUTTON_STYLE, HOVERED_BUTTON_STYLE);
        applyTheme(topButtonHolder, IDLE_BUTTON_STYLE, HOVERED_BUTTON_STYLE);
    }
    
    public void setTheme(String c1, String c2, boolean resetTheme){
        final String IDLE_BUTTON_STYLE = "-fx-background-color: #FFFFFF;";
        final String HOVERED_BUTTON_STYLE = "-fx-background-color: linear-gradient(" + c1 + ", " + c2 + ");";
        
        sourceWindow.setStyle("-fx-background-color: linear-gradient(" + c1 + ", " + c2 + ");");
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
    
}
