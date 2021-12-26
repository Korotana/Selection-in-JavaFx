package com.example.box2021demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        HBox root = new HBox();

        // MVC
        BoxView view = new BoxView(700,700);
        BoxView mini = new BoxView(200,200);

        BoxModel model = new BoxModel();
        BoxController controller = new BoxController();
        InteractionModel iModel = new InteractionModel();

        view.setModel(model);
        view.setController(controller);
        view.setInteractionModel(iModel);
        controller.setInteractionModel(iModel);
        controller.setModel(model);

        model.addSubscriber(view);
        iModel.addSubscriber(view);
        model.addSubscriber(mini);
        iModel.addSubscriber(mini);
//
        mini.setModel(model);
        mini.setController(controller);
        mini.setInteractionModel(iModel);

        root.getChildren().addAll(view,mini);
//        root.getChildren().addAll(view);

        model.createBox(0.8,0.2);
        model.createBox(0.4,0.3);


        stage.setTitle("BoxDemo");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}