/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.dock.ControlsController;
import controller.dock.HelpScreenController;
import controller.dock.MixerController;
import controller.dock.SceneController;
import controller.dock.SceneTransitionController;
import controller.dock.ScreenController;
import controller.dock.SourceController;
import controller.dock.StudioController;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

/**
 *
 * @author rohanayan
 */

public class MainController implements Initializable, ThemeController{

    private FXMLLoader settingsLoader;
    private FXMLLoader mixerLoader;
    private FXMLLoader sceneLoader;
    private FXMLLoader sourceLoader;
    private FXMLLoader sceneTransitionLoader;
    private FXMLLoader controlsLoader;
    private FXMLLoader screenLoader;
    private FXMLLoader studioLoader;
    private FXMLLoader helpLoader;
    
    private Stage settingsStage;
    private Stage mixerStage;
    private Stage sceneStage;
    private Stage sourceStage;
    private Stage sceneTransitionStage;
    private Stage controlsStage;
    private Stage screenStage;
    private Stage studioStage;
    private Stage helpStage;
    
    private SettingsController settingsController;
    private MixerController mixerController;
    private SceneController sceneController;
    private SourceController sourceController;
    private SceneTransitionController sceneTransitionController;
    private ControlsController controlsController;
    private ScreenController screenController;
    private StudioController studioController;
    private HelpScreenController helpScreenController;
    
    private List<ThemeController> listOfThemeControllers = new ArrayList<>();
    private HBox docksHolder = new HBox();
    private HBox screenHolder = new HBox();
    private Alert alert = new Alert(AlertType.NONE, "Allow opening file?", ButtonType.YES, ButtonType.NO);
    private FileChooser fileChooser = new FileChooser();
    private TextInputDialog dialog = new TextInputDialog();
    private String copySource = null;
    private static int fullscreenToggle = 0;
    private Image fullscreenImage;
    private Image normalScreenImage;
    private ImageView fullscreenImageView;
    private ImageView normalScreenImageView;
        
    @FXML
    private VBox mainBox;

    @FXML
    private MenuBar topMenu;

    @FXML
    private MenuItem fmShow;

    @FXML
    private MenuItem fmRemux;

    @FXML
    private MenuItem fmSettings;

    @FXML
    private MenuItem fmShowSettingsFolder;

    @FXML
    private MenuItem fmShowProfileFolder;

    @FXML
    private MenuItem fmExit;

    @FXML
    private MenuItem emCopy;

    @FXML
    private MenuItem emPasteRef;

    @FXML
    private MenuItem emMoveUp;

    @FXML
    private MenuItem emMoveDown;

    @FXML
    private MenuItem emMoveTop;

    @FXML
    private MenuItem emMoveBtm;

    @FXML
    private CheckMenuItem emLockPreview;

    @FXML
    private MenuItem vmFullscreen;

    @FXML
    private MenuItem vmResetUi;

    @FXML
    private CheckMenuItem vmScenes;

    @FXML
    private CheckMenuItem vmSources;

    @FXML
    private CheckMenuItem vmMixer;

    @FXML
    private CheckMenuItem vmSceneTransition;

    @FXML
    private CheckMenuItem vmControls;

    @FXML
    private CheckMenuItem vmListboxes;

    @FXML
    private MenuItem pmNew;

    @FXML
    private MenuItem pmDuplicate;

    @FXML
    private MenuItem pmRename;

    @FXML
    private MenuItem pmImport;

    @FXML
    private MenuItem pmExport;

    @FXML
    private MenuItem scmNew;

    @FXML
    private MenuItem scmDuplicate;

    @FXML
    private MenuItem scmRename;

    @FXML
    private MenuItem scmImport;

    @FXML
    private MenuItem scmExport;

    @FXML
    private ToolBar tlbrTop;

    @FXML
    private HBox topButtonHolder;

    @FXML
    private Button btnNewStream;

    @FXML
    private Button btnRemux;

    @FXML
    private Button btnCopy;

    @FXML
    private Button btnPaste;

    @FXML
    private Button btnFullscreen;

    @FXML
    private Button btnProfile;

    @FXML
    private Button btnHelp;
    
    public MainController(){
        fileChooser.setTitle("Open File");
    }
    
    public CheckMenuItem getMixer(){
        return vmMixer;
    }
    
    public CheckMenuItem getControls(){
        return vmControls;
    }
    
    public CheckMenuItem getScenes(){
        return vmScenes;
    }
    
    public CheckMenuItem getSceneTransition(){
        return vmSceneTransition;
    }
    
    public CheckMenuItem getSources(){
        return vmSources;
    }
    
    public HBox getDocksHolder(){
        return docksHolder;
    }

    @FXML
    public void btnCopyPressed(ActionEvent event) {
        copySource = sourceController.getSelectedSource();
    }

    @FXML
    public void btnFullscreenPressed(ActionEvent event) {
        Stage stage = (Stage)(((Button)event.getSource()).getScene().getWindow());
        if(fullscreenToggle == 0){
            btnFullscreen.setGraphic(normalScreenImageView);
            stage.setFullScreen(true);
            fullscreenToggle = 1;
            return;
        }
        if(fullscreenToggle == 1){
            btnFullscreen.setGraphic(fullscreenImageView);
            stage.setFullScreen(false);
            fullscreenToggle = 0;
        }
    }

    @FXML
    public void btnHelpPressed(ActionEvent event) {
        helpStage.show();
        helpScreenController.getWebEngine().load("https://obsproject.com/help");
    }

    @FXML
    public void btnNewStreamPressed(ActionEvent event) {
        alert.setTitle("Show Recordings Folder");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            fileChooser.showOpenDialog(mainBox.getScene().getWindow());
        }
    }

    @FXML
    public void btnPastePressed(ActionEvent event) {
        if(copySource != null){
            sourceController.addToListOfSources(copySource);
        }
    }

    @FXML
    public void btnProfilePressed(ActionEvent event) {
        dialog.setTitle("Add Profile");
        dialog.setHeaderText(null);
        dialog.setGraphic(null);
        dialog.setContentText("Please enter the name of the profile:");
        dialog.showAndWait();
    }

    @FXML
    public void btnRemuxPressed(ActionEvent event) {
        alert.setTitle("Show Remux Folder");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            fileChooser.showOpenDialog(mainBox.getScene().getWindow());
        }
    }

    @FXML
    public void emCopyPressed(ActionEvent event) {
        copySource = sourceController.getSelectedSource();
    }


    @FXML
    public void emLockPreviewPressed(ActionEvent event) {
        if(emLockPreview.isSelected()){
            controlsController.disableBtnClose(true);
            sceneTransitionController.disableBtnClose(true);
            mixerController.disableBtnClose(true);
            sourceController.disableBtnClose(true);
            sceneController.disableBtnClose(true);
            vmControls.setDisable(true);
            vmSceneTransition.setDisable(true);
            vmMixer.setDisable(true);
            vmSources.setDisable(true);
            vmScenes.setDisable(true);
        }else{
            controlsController.disableBtnClose(false);
            sceneTransitionController.disableBtnClose(false);
            mixerController.disableBtnClose(false);
            sourceController.disableBtnClose(false);
            sceneController.disableBtnClose(false);
            vmControls.setDisable(false);
            vmSceneTransition.setDisable(false);
            vmMixer.setDisable(false);
            vmSources.setDisable(false);
            vmScenes.setDisable(false);
        }
    }

    @FXML
    public void emMoveBtmPressed(ActionEvent event) {
        String s = sourceController.getSelectedSource();
        sourceController.moveToBottomListOfSources(s);
    }

    @FXML
    public void emMoveDownPressed(ActionEvent event) {
        String s = sourceController.getSelectedSource();
        sourceController.moveDownListOfSources(s);
    }

    @FXML
    public void emMoveTopPressed(ActionEvent event) {
        String s = sourceController.getSelectedSource();
        sourceController.moveToTopListOfSources(s);
    }

    @FXML
    public void emMoveUpPressed(ActionEvent event) {
        String s = sourceController.getSelectedSource();
        sourceController.moveUpListOfSources(s);
    }

    @FXML
    public void emPasteRefPressed(ActionEvent event) {
        if(copySource != null){
            sourceController.addToListOfSources(copySource);
        }
    }

    @FXML
    public void fmExitPressed(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    public void fmRemuxPressed(ActionEvent event) {
        alert.setTitle("Show Remux Folder");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            fileChooser.showOpenDialog(mainBox.getScene().getWindow());
        }
    }

    @FXML
    public void fmSettingsPressed(ActionEvent event) {
        settingsStage.show();
    }

    @FXML
    public void fmShowPressed(ActionEvent event) {
        alert.setTitle("Show Recordings Folder");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            fileChooser.showOpenDialog(mainBox.getScene().getWindow());
        }
    }

    @FXML
    public void fmShowProfileFolderPressed(ActionEvent event) {
        alert.setTitle("Show Profile Folder");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            fileChooser.showOpenDialog(mainBox.getScene().getWindow());
        }
    }

    @FXML
    public void fmShowSettingsFolderPressed(ActionEvent event) {
        alert.setTitle("Show Settings Folder");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            fileChooser.showOpenDialog(mainBox.getScene().getWindow());
        }
    }

    @FXML
    public void pmDuplicatePressed(ActionEvent event) {
        dialog.setTitle("Duplicate Profile");
        dialog.setHeaderText(null);
        dialog.setGraphic(null);
        dialog.setContentText("Please enter the name of the profile:");
        dialog.showAndWait();
    }

    @FXML
    public void pmExportPressed(ActionEvent event) {
        fileChooser.showOpenDialog(mainBox.getScene().getWindow());
    }

    @FXML
    public void pmImportPressed(ActionEvent event) {
        fileChooser.showOpenDialog(mainBox.getScene().getWindow());
    }

    @FXML
    public void pmNewPressed(ActionEvent event) {
        dialog.setTitle("Add Profile");
        dialog.setHeaderText(null);
        dialog.setGraphic(null);
        dialog.setContentText("Please enter the name of the profile:");
        dialog.showAndWait();
    }

    @FXML
    public void pmRenamePressed(ActionEvent event) {
        dialog.setTitle("Rename Profile");
        dialog.setHeaderText(null);
        dialog.setGraphic(null);
        dialog.setContentText("Please enter the name of the profile:");
        dialog.showAndWait();
    }

    @FXML
    public void scmDuplicatePressed(ActionEvent event) {
        dialog.setTitle("Duplicate Scene Collection");
        dialog.setHeaderText(null);
        dialog.setGraphic(null);
        dialog.setContentText("Please enter the name of the scene collection:");
        dialog.showAndWait();
    }

    @FXML
    public void scmExportPressed(ActionEvent event) {
        fileChooser.showOpenDialog(mainBox.getScene().getWindow());
    }

    @FXML
    public void scmImportPressed(ActionEvent event) {
        fileChooser.showOpenDialog(mainBox.getScene().getWindow());
    }

    @FXML
    public void scmNewPressed(ActionEvent event) {
        dialog.setTitle("Add Scene Collection");
        dialog.setHeaderText(null);
        dialog.setGraphic(null);
        dialog.setContentText("Please enter the name of the scene collection:");
        dialog.showAndWait();
    }

    @FXML
    public void scmRenamePressed(ActionEvent event) {
        dialog.setTitle("Rename Scene Collection");
        dialog.setHeaderText(null);
        dialog.setGraphic(null);
        dialog.setContentText("Please enter the name of the scene collection:");
        dialog.showAndWait();
    }

    @FXML
    public void vmControlsPressed(ActionEvent event) {
        if(vmControls.isSelected()){
            docksHolder.getChildren().add(controlsController.getParent());
        }else{
            docksHolder.getChildren().remove(controlsController.getParent());
        }
    }

    @FXML
    public void vmListboxesPressed(ActionEvent event) {
        if(vmListboxes.isSelected()){
            sceneController.setBtmButtonHolderVisible(true);
            sourceController.setBtmButtonHolderVisible(true);
        }else{
            sceneController.setBtmButtonHolderVisible(false);
            sourceController.setBtmButtonHolderVisible(false);
        }
    }

    @FXML
    public void vmMixerPressed(ActionEvent event) {
        if(vmMixer.isSelected()){
            docksHolder.getChildren().add(mixerController.getParent());
        }else{
            docksHolder.getChildren().remove(mixerController.getParent());
        }
    }

    @FXML
    public void vmResetUiPressed(ActionEvent event) {
        docksHolder.getChildren().clear();
        vmControls.setSelected(false);
        vmSceneTransition.setSelected(false);
        vmMixer.setSelected(false);
        vmSources.setSelected(false);
        vmScenes.setSelected(false);
        docksHolder.getChildren().add(sceneController.getParent());
        docksHolder.getChildren().add(sourceController.getParent());
        docksHolder.getChildren().add(mixerController.getParent());
        docksHolder.getChildren().add(sceneTransitionController.getParent());
        docksHolder.getChildren().add(controlsController.getParent());
        vmControls.setSelected(true);
        vmSceneTransition.setSelected(true);
        vmMixer.setSelected(true);
        vmSources.setSelected(true);
        vmScenes.setSelected(true);
    }

    @FXML
    public void vmSceneTransitionPressed(ActionEvent event) {
        if(vmSceneTransition.isSelected()){
            docksHolder.getChildren().add(sceneTransitionController.getParent());
        }else{
            docksHolder.getChildren().remove(sceneTransitionController.getParent());
        }
    }

    @FXML
    public void vmScenesPressed(ActionEvent event) {
        if(vmScenes.isSelected()){
            docksHolder.getChildren().add(sceneController.getParent());
        }else{
            docksHolder.getChildren().remove(sceneController.getParent());
        }
    }

    @FXML
    public void vmSourcesPressed(ActionEvent event) {
        if(vmSources.isSelected()){
            docksHolder.getChildren().add(sourceController.getParent());
        }else{
            docksHolder.getChildren().remove(sourceController.getParent());
        }
    }
    
    public List<ThemeController> getListOfThemeControllers(){
        return listOfThemeControllers;
    }
    
    public HBox getScreenHolder(){
        return screenHolder;
    }
    
    public StudioController getStudioController(){
        return studioController;
    }
    
    public ScreenController getScreenController(){
        return screenController;
    }
    
    public SettingsController getSettingsController(){
        return settingsController;
    }
    
    private double getColorBrightness(Color c){
        double r = c.getRed(); double g = c.getGreen(); double b = c.getBlue();
        double br = 0.2126*r + 0.7152*g + 0.0722*b;
        return br;
    }
    
    private Color getDarkColor(Color c){
        double darkness = 0.5;
        double r = c.getRed(); double g = c.getGreen(); double b = c.getBlue();
        r *= darkness; g *= darkness; b *= darkness;
        return new Color(r, g, b, 1.0);
    }
    
    public void setTheme(Color c){
        String c1 = "#" + Integer.toHexString(c.hashCode());
        String c2 = "";

        switch (c1) {
            case "#ff":
                c1 = "#333333ff";
                c2 = "#4d4d4dff";
                break;
            case "#1a1a1aff":
                c1 = "#4d4d4dff";
                c2 = "#666666ff";
                break;
            case "#1a0068ff":
                c1 = "#331a80ff";
                c2 = "#4d3399ff";
                break;
            case "#1a80ff":
                c1 = "#334db3ff";
                c2 = "#4d66ccff";
                break;
            case "#1a3399ff":
                c1 = "#4d66ccff";
                c2 = "#6680e6ff";
                break;
            case "#1a4d4dff":
                c1 = "#336666ff";
                c2 = "#4d8080ff";
                break;
            default:
                break;
        }
        
        Color darkColor = getDarkColor(c);
        if("".equals(c2)){
            c2 = "#" + Integer.toHexString(darkColor.hashCode());
        }
        double brightness = getColorBrightness(c);
        
        for(ThemeController tc: listOfThemeControllers){
            tc.setTheme(c1, c2);
            if(brightness > 0.7){
                tc.setTheme(true);
            }else{
                tc.setTheme(false);
            }
        }
    }
    
    public void close(){
        settingsStage.close();
    }
    
    public void showSettings(){
        settingsStage.show();
    }
    
    private Stage initStage(FXMLLoader loader, boolean undecorate){
        Stage stage = new Stage();
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setAlwaysOnTop(true);
            if(undecorate){
                stage.initStyle(StageStyle.UNDECORATED);
            }

        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stage;
    }
    
    private FXMLLoader initLoader(String fxmlFilename){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFilename));
        return loader;
    }

    private void applyTheme(VBox vBox, String idleString, String hoverString){
        for(Node node:vBox.getChildren()) {
            node.setStyle(idleString);
            node.setOnMouseEntered(e -> node.setStyle(hoverString));
            node.setOnMouseExited(e -> node.setStyle(idleString));
            node.setOnMousePressed(e -> node.setStyle(idleString));
            node.setOnMouseReleased(e -> node.setStyle(hoverString));
        }
    }
    
    private void applyTheme(HBox hBox, String idleString, String hoverString){
        for(Node node:hBox.getChildren()) {
            node.setStyle(idleString);
            node.setOnMouseEntered(e -> node.setStyle(hoverString));
            node.setOnMouseExited(e -> node.setStyle(idleString));
            node.setOnMousePressed(e -> node.setStyle(idleString));
            node.setOnMouseReleased(e -> node.setStyle(hoverString));
        }
    }
    private void applyTheme(Button button, String idleString, String hoverString){
            button.setStyle(idleString);
            button.setOnMouseEntered(e -> button.setStyle(hoverString));
            button.setOnMouseExited(e -> button.setStyle(idleString));
            button.setOnMousePressed(e -> button.setStyle(idleString));
            button.setOnMouseReleased(e -> button.setStyle(hoverString));
    }
    
    @Override
    public void setTheme(String c1, String c2){
        final String IDLE_BUTTON_STYLE = "-fx-background-color: #FFFFFF;";
        final String HOVERED_BUTTON_STYLE = "-fx-background-color: linear-gradient(" + c1 + ", " + c2 + ");";

        mainBox.setStyle("-fx-background-color: linear-gradient(" + c1 + ", " + c2 + ");");
        tlbrTop.setStyle("-fx-background-color: linear-gradient(" + c2 + ", " + c1 + ");");
        topMenu.setStyle("-fx-background-color: linear-gradient(" + c2 + ", " + c1 + ");");

        applyTheme(topButtonHolder, IDLE_BUTTON_STYLE, HOVERED_BUTTON_STYLE);
        applyTheme(btnNewStream, IDLE_BUTTON_STYLE, HOVERED_BUTTON_STYLE);
        applyTheme(btnCopy, IDLE_BUTTON_STYLE, HOVERED_BUTTON_STYLE);
        applyTheme(btnPaste, IDLE_BUTTON_STYLE, HOVERED_BUTTON_STYLE);
        applyTheme(btnProfile, IDLE_BUTTON_STYLE, HOVERED_BUTTON_STYLE);
        applyTheme(btnRemux, IDLE_BUTTON_STYLE, HOVERED_BUTTON_STYLE);
        applyTheme(btnFullscreen, IDLE_BUTTON_STYLE, HOVERED_BUTTON_STYLE);
        applyTheme(btnHelp, IDLE_BUTTON_STYLE, HOVERED_BUTTON_STYLE);
    }
    
    public void setTheme(String c1, String c2, boolean resetTheme){
        final String IDLE_BUTTON_STYLE = "-fx-background-color: #FFFFFF;";
        final String HOVERED_BUTTON_STYLE = "-fx-background-color: linear-gradient(" + c1 + ", " + c2 + ");";

        mainBox.setStyle("-fx-background-color: linear-gradient(" + c1 + ", " + c2 + ");");
        tlbrTop.setStyle("-fx-background-color: linear-gradient(" + c2 + ", " + c1 + ");");
        topMenu.setStyle("-fx-background-color: linear-gradient(" + c2 + ", " + c1 + ");");

        applyTheme(topButtonHolder, IDLE_BUTTON_STYLE, HOVERED_BUTTON_STYLE);
        applyTheme(btnNewStream, IDLE_BUTTON_STYLE, HOVERED_BUTTON_STYLE);
        applyTheme(btnCopy, IDLE_BUTTON_STYLE, HOVERED_BUTTON_STYLE);
        applyTheme(btnPaste, IDLE_BUTTON_STYLE, HOVERED_BUTTON_STYLE);
        applyTheme(btnProfile, IDLE_BUTTON_STYLE, HOVERED_BUTTON_STYLE);
        applyTheme(btnRemux, IDLE_BUTTON_STYLE, HOVERED_BUTTON_STYLE);
        applyTheme(btnFullscreen, IDLE_BUTTON_STYLE, HOVERED_BUTTON_STYLE);
        applyTheme(btnHelp, IDLE_BUTTON_STYLE, HOVERED_BUTTON_STYLE);
    }
    
    public void setTheme(boolean labelIsBlack){
        
    }
    
        @Override
    public void initialize(URL location, ResourceBundle resources) {
        settingsLoader = initLoader("/view/Settings.fxml");
        mixerLoader = initLoader("/view/Mixer.fxml");
        sceneLoader = initLoader("/view/Scene.fxml");
        sourceLoader = initLoader("/view/Source.fxml");
        sceneTransitionLoader = initLoader("/view/SceneTransition.fxml");
        controlsLoader = initLoader("/view/Controls.fxml");
        screenLoader = initLoader("/view/Screen.fxml");
        studioLoader = initLoader("/view/Studio.fxml");
        helpLoader = initLoader("/view/HelpScreen.fxml");
        
        settingsStage = initStage(settingsLoader, false);
        mixerStage = initStage(mixerLoader, true);
        sceneStage = initStage(sceneLoader, true);
        sourceStage = initStage(sourceLoader, true);
        sceneTransitionStage = initStage(sceneTransitionLoader, true);
        controlsStage = initStage(controlsLoader, true);
        screenStage = initStage(screenLoader, true);
        studioStage = initStage(studioLoader, true);
        helpStage = initStage(helpLoader, false);
        settingsStage.setTitle("Settings Window");
        helpStage.setTitle("Help Window");
        
        settingsController = settingsLoader.getController();
        mixerController = mixerLoader.getController();
        sceneController = sceneLoader.getController();
        sourceController = sourceLoader.getController();
        sceneTransitionController = sceneTransitionLoader.getController();
        controlsController = controlsLoader.getController();
        screenController = screenLoader.getController();
        studioController = studioLoader.getController();
        helpScreenController = helpLoader.getController();
        
        listOfThemeControllers.add(this);
        listOfThemeControllers.add(mixerController);
        listOfThemeControllers.add(sceneController);
        listOfThemeControllers.add(sourceController);
        listOfThemeControllers.add(sceneTransitionController);
        listOfThemeControllers.add(controlsController);
        listOfThemeControllers.add(settingsController);
        listOfThemeControllers.add(screenController);
        listOfThemeControllers.add(studioController);
        
        settingsController.setMainController(this);
        mixerController.setMainController(this);
        controlsController.setMainController(this);
        sceneController.setMainController(this);
        sceneTransitionController.setMainController(this);
        sourceController.setMainController(this);
        screenController.setMainController(this);
        studioController.setMainController(this);
        helpScreenController.setMainController(this);
        
        screenHolder.getChildren().add(screenController.getParent());
        screenHolder.setAlignment(Pos.CENTER);
        HBox.setHgrow(screenHolder, Priority.ALWAYS);
        mainBox.getChildren().add(screenHolder);
        
        docksHolder.getChildren().add(sceneController.getParent());
        docksHolder.getChildren().add(sourceController.getParent());
        docksHolder.getChildren().add(mixerController.getParent());
        docksHolder.getChildren().add(sceneTransitionController.getParent());
        docksHolder.getChildren().add(controlsController.getParent());
        docksHolder.setSpacing(10);
        mainBox.getChildren().add(docksHolder);
        VBox.setMargin(docksHolder, new Insets(25,10,10,25));
        
        docksHolder.setAlignment(Pos.CENTER);
        docksHolder.setMinHeight(300);

        vmScenes.setSelected(true);
        vmSources.setSelected(true);
        vmMixer.setSelected(true);
        vmSceneTransition.setSelected(true);
        vmControls.setSelected(true);
        
        vmListboxes.setSelected(true);
        
        final Tooltip showRecordingTooltip = new Tooltip("Show recordings");
        final Tooltip showRemuxTooltip = new Tooltip("Remux recordings");
        final Tooltip copyTooltip = new Tooltip("Copy");
        final Tooltip pasteTooltip = new Tooltip("Paste");
        final Tooltip fullscreenTooltip = new Tooltip("Fullscreen");
        final Tooltip profileTooltip = new Tooltip("Add new profile");
        final Tooltip helpTooltip = new Tooltip("Help");
        btnNewStream.setTooltip(showRecordingTooltip);
        btnRemux.setTooltip(showRemuxTooltip);
        btnCopy.setTooltip(copyTooltip);
        btnPaste.setTooltip(pasteTooltip);
        btnFullscreen.setTooltip(fullscreenTooltip);
        btnProfile.setTooltip(profileTooltip);
        btnHelp.setTooltip(helpTooltip);
        
        fullscreenImage = new Image(getClass().getResourceAsStream("/images/fullscreen.png"));
        fullscreenImageView = new ImageView(fullscreenImage);
        fullscreenImageView.setFitWidth(30);
        fullscreenImageView.setFitHeight(30);
        
        normalScreenImage = new Image(getClass().getResourceAsStream("/images/normal_screen.png"));
        normalScreenImageView = new ImageView(normalScreenImage);
        normalScreenImageView.setFitWidth(30);
        normalScreenImageView.setFitHeight(30);
    }
}
