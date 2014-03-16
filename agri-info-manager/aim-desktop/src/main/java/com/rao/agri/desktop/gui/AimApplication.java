package com.rao.agri.desktop.gui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import com.rao.agri.desktop.AimConstants;
import com.rao.agri.desktop.gui.controls.LeftNavigation;
import com.rao.agri.desktop.gui.controls.WindowButtons;
import com.rao.agri.desktop.gui.controls.WindowResizeButton;

public class AimApplication extends Application{

	private static AimApplication AIM_APPLICATION;
    private double mouseDragOffsetX = 0;
    private double mouseDragOffsetY = 0;
    
	@Override
	public void start(final Stage stage) throws Exception {
		AIM_APPLICATION = this;
		stage.setTitle(AimConstants.APP_TITLE);
	    // We don't want default application frame 
		stage.initStyle(StageStyle.UNDECORATED);
        
		// create window resize button
		final WindowResizeButton windowResizeButton = new WindowResizeButton(stage, AimConstants.APP_MIN_WIDTH,AimConstants.APP_MIN_HEIGHT);
        
		// create root
		BorderPane root = new BorderPane() {
            @Override 
            protected void layoutChildren() {
                super.layoutChildren();
                windowResizeButton.autosize();
                windowResizeButton.setLayoutX(getWidth() - windowResizeButton.getLayoutBounds().getWidth());
                windowResizeButton.setLayoutY(getHeight() - windowResizeButton.getLayoutBounds().getHeight());
            }
        };
        root.getStyleClass().add("application");
        root.setId("root");
	    
        Scene scene = new Scene(root, AimConstants.APP_MIN_WIDTH,AimConstants.APP_MIN_HEIGHT);
        scene.getStylesheets().add(AimApplication.class.getResource("AimApplication.css").toExternalForm());
        
        
        ToolBar toolBar = new ToolBar();
        toolBar.setId("mainToolBar");
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        toolBar.getItems().add(spacer);
        
        Region spacer2 = new Region();
        HBox.setHgrow(spacer2, Priority.ALWAYS);
        toolBar.getItems().add(spacer2);
        
        toolBar.setPrefHeight(66);
        toolBar.setMinHeight(66);
        toolBar.setMaxHeight(66);
        GridPane.setConstraints(toolBar, 0, 0);
        
        
        final WindowButtons windowButtons = new WindowButtons(stage);
        toolBar.getItems().add(windowButtons);
        // add window header double clicking
        toolBar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override 
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    windowButtons.toogleMaximized();
                }
            }
        });
        // add window dragging
        toolBar.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override 
            public void handle(MouseEvent event) {
                mouseDragOffsetX = event.getSceneX();
                mouseDragOffsetY = event.getSceneY();
            }
        });
        toolBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override 
            public void handle(MouseEvent event) {
                if(!windowButtons.isMaximized()) {
                    stage.setX(event.getScreenX()-mouseDragOffsetX);
                    stage.setY(event.getScreenY()-mouseDragOffsetY);
                }
            }
        });
        
        root.setTop(toolBar);
        
        LeftNavigation leftNav = new LeftNavigation();
        leftNav.setId("left-nav-bar");
        //leftNav.setMinHeight(29);
        //leftNav.setMaxWidth(Double.MAX_VALUE);
       
        
        
        
        
    
        
        
        
        root.setLeft(leftNav);;
        
	    stage.setScene(scene);
	    stage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
