package de.fx.spring.customisation;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FxInformationScene extends FxControlStylingService{

	private Stage stage;
	private StackPane root;
	private InfoType type;
	private Node[] components;
	private Button button;

	public FxInformationScene(InfoType type,Node ...components ) {
		this.type = type;
		this.components = components;
		showScene();
	}

	private void showScene() {
		stage = new Stage();
		if(type != null) {
			stage.setTitle(type.toString());	
		}
		root = new StackPane();
		Scene scene = new Scene(root,calculateWidht(components),calculateHeigth(components));

		VBox mainBox = new VBox();
		mainBox.setPrefWidth(scene.getWidth());
		mainBox.setPrefHeight(scene.getHeight());
		mainBox.setAlignment(Pos.CENTER);
		mainBox.setSpacing(10);

		HBox hBox = new HBox();
		hBox.setAlignment(Pos.CENTER);
		hBox.setSpacing(10);	
		if(type != null) {
			hBox.getChildren().add(getImageView());
		}

		VBox vBox = new VBox();
		vBox.setAlignment(Pos.CENTER);
		vBox.setSpacing(10);

		for(int i=0; i<components.length;i++) {
			vBox.getChildren().addAll(components[i]);
		}

		button = new Button("OK");
		button.setStyle("-fx-background-color: #FFFAF0; -fx-font-weight:bold;-fx-border-color:black;-fx-scale-x:1.5");
		button.setOnMouseEntered(e ->{hovering(e);});
		button.setOnMouseExited(e ->{ hoveringEnded(e); });
		button.setOnAction(e->{stage.close();});

		hBox.getChildren().add(vBox);
		mainBox.getChildren().add(hBox);
		mainBox.getChildren().add(button);
		root.getChildren().add(mainBox);
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
		if(width < 270) {
			width = 270;
		}
		return width;
	}

	private double calculateHeigth(Node...components) {
		double height = 0;
		for(Node c : components ) {
			height = height+c.getScaleY();
		}
		if(components.length>1) {
			height = height*50;
		}else {
			height = height*100;	
		}
		
		if(height < 100) {
			height = 100;
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

	@Override
	public void setHoveringStyle() {
		setControlObject(button);
		setStyle(";-fx-background-color:white");
	}

	@Override
	public void setControlActiveStyle() {
		// TODO Auto-generated method stub
		
	}

	public Button getButton() {
		return button;
	}
	
	
}
