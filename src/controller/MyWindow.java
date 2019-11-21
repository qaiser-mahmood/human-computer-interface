/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author rohanayan
 */
public class MyWindow {
        
    public void applyTheme(VBox vBox, String idleString, String hoverString){
        for(Node node:vBox.getChildren()) {
            node.setStyle(idleString);
            node.setOnMouseEntered(e -> node.setStyle(hoverString));
            node.setOnMouseExited(e -> node.setStyle(idleString));
            node.setOnMousePressed(e -> node.setStyle(idleString));
            node.setOnMouseReleased(e -> node.setStyle(hoverString));
        }
    }
    
    public void applyTheme(HBox hBox, String idleString, String hoverString){
        for(Node node:hBox.getChildren()) {
            node.setStyle(idleString);
            node.setOnMouseEntered(e -> node.setStyle(hoverString));
            node.setOnMouseExited(e -> node.setStyle(idleString));
            node.setOnMousePressed(e -> node.setStyle(idleString));
            node.setOnMouseReleased(e -> node.setStyle(hoverString));
        }
    }
    public void applyTheme(Button button, String idleString, String hoverString){
            button.setStyle(idleString);
            button.setOnMouseEntered(e -> button.setStyle(hoverString));
            button.setOnMouseExited(e -> button.setStyle(idleString));
            button.setOnMousePressed(e -> button.setStyle(idleString));
            button.setOnMouseReleased(e -> button.setStyle(hoverString));
    }
}
