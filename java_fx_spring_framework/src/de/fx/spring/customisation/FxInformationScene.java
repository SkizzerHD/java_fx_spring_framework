package de.fx.spring.customisation;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FxInformationScene{
	
	private Stage stage;
	private StackPane root;
	private InfoType type;
	private Node[] components;
	
	public FxInformationScene(InfoType type,Node ...components ) {
		this.type = type;
		this.components = components;
		showScene();
	}
	
	private void showScene() {
		stage = new Stage();
		stage.setTitle(type.toString());
		root = new StackPane();
		Scene scene = new Scene(root,calculateWidht(components),calculateHeigth(components));
		
		HBox hBox = new HBox();
		hBox.setPrefWidth(scene.getWidth());
		hBox.setPrefHeight(scene.getHeight());
		hBox.setAlignment(Pos.CENTER);
		hBox.setSpacing(10);	
		hBox.getChildren().add(getImageView());
		
		VBox vBox = new VBox();
		vBox.setAlignment(Pos.CENTER);
		vBox.setSpacing(10);
		
		for(int i=0; i<components.length;i++) {
			vBox.getChildren().addAll(components[i]);
		}
		
		hBox.getChildren().add(vBox);
		root.getChildren().add(hBox);
		stage.setScene(scene);
		stage.show();
	}
	
	private double calculateWidht(Node...components) {
		double width = 0;
		for(Node c : components ) {
			if(c.getScaleX() > width) {
				width = c.getScaleX();
			}
			
		}
		width = width*200;
		if(width < 250) {
			width = 250;
		}
		return width;
	}
	
	private double calculateHeigth(Node...components) {
		double height = 0;
		for(Node c : components ) {
			height = height+c.getScaleY();
		}
		height = height*50;
		if(height < 50) {
			height = 50;
		}
		return height;
	}
	
	public void setRootStyle(String style) {
		root.setStyle(style);
	}
	
	private ImageView getImageView() {
		Image img = null;
		if(type == InfoType.INFORMATION) {
			img = new Image("/images/info_image.png");
		}else if(type == InfoType.ERROR) {
			img = new Image("/images/error_image.png");
		}else if(type == InfoType.CONFIRMATION) {
			img = new Image("/images/confirm_image.png");
		}
		ImageView view = new ImageView(img);
		view.setFitHeight(30);
		view.setPreserveRatio(true);
		return view;
	}
}
