package com.rao.agri.desktop.gui.controls;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class LeftNavigation extends VBox {

	
	public LeftNavigation(){
		super(0);
		super.getStyleClass().setAll("nav-bar");
		
		//Create a toggle group
		final ToggleGroup toggleGroup = new ToggleGroup();
        
		final ToggleButton moneyButton = new ToggleButton();
		moneyButton.setText("Money");
		moneyButton.setToggleGroup(toggleGroup);
        
		final ToggleButton workButton = new ToggleButton();
		workButton.setText("Work");
		workButton.setToggleGroup(toggleGroup);
		
		final ToggleButton cropButton = new ToggleButton();
		cropButton.setText("Crop");
		cropButton.setToggleGroup(toggleGroup); 
		
		InvalidationListener viewChangeNotifyListner = new InvalidationListener() {
            public void invalidated(Observable ov) {
                if(moneyButton.isSelected()) {
                    // TODO Change view 
                	System.out.println("Change view to money");
                } else if(workButton.isSelected()) {
                	// TODO Change view
                	System.out.println("Change view to work");
                } else if(cropButton.isSelected()) {
                	// TODO Change view
                	System.out.println("Change view to crop");
                }
            }
        };
		
        moneyButton.selectedProperty().addListener(viewChangeNotifyListner);
        workButton.selectedProperty().addListener(viewChangeNotifyListner);
        cropButton.selectedProperty().addListener(viewChangeNotifyListner);
        
		super.getChildren().addAll(moneyButton,workButton,cropButton);
	}
}
