package com.example.box2021demo;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class BoxView extends StackPane implements BoxModelListener {
    Canvas myCanvas;
    GraphicsContext gc;
    double width, height;
    BoxModel model;
    private InteractionModel iModel;
    double boxLeft, boxTop, boxWidth, boxHeight;

    public BoxView(double w, double h) {
        width = w;
        height = h;
        myCanvas = new Canvas(width, height);
        gc = myCanvas.getGraphicsContext2D();
        getChildren().add(myCanvas);
    }

    public void setModel(BoxModel newModel) {
        model = newModel;
    }

    public void setController(BoxController controller) {
        // set up event handling - normalize coordinates
        myCanvas.setOnMousePressed(e -> controller.handlePressed(e.getX()/width,e.getY()/height,e));
        myCanvas.setOnMouseDragged(e -> controller.handleDragged(e.getX()/width,e.getY()/height,e));
        myCanvas.setOnMouseMoved(e -> controller.handleMoved(e.getX()/width,e.getY()/height,e));
        myCanvas.setOnMouseReleased(e -> controller.handleReleased(e.getX()/width,e.getY()/height,e));
    }

    public void setInteractionModel(InteractionModel newModel) {
        iModel = newModel;
    }

    public void draw() {
        gc.clearRect(0, 0, width, height);
        gc.setStroke(Color.BLACK);
        gc.strokeRect(0, 0, width, height);
        for (Box b : model.boxes) {
            if (b == iModel.getSelection()) {
                gc.setFill(Color.HOTPINK);
            } else {
                gc.setFill(Color.PURPLE);
            }
            boxLeft = b.left * width;
            boxTop = b.top * height;
            boxWidth = b.width * width;
            boxHeight = b.height * height;
            gc.fillRect(boxLeft, boxTop, boxWidth, boxHeight);
            gc.strokeRect(boxLeft, boxTop, boxWidth, boxHeight);
        }
    }

    public void modelChanged() {
        draw();
    }
}
