package com.example.box2021demo;

import javafx.scene.input.MouseEvent;

public class BoxControllerA {
    InteractionModel iModel;
    BoxModel model;
    double prevX, prevY;

    private enum State {
        READY, DRAGGING
    }

    private BoxControllerA.State currentState;

    public BoxControllerA() {
        currentState = BoxControllerA.State.READY;
    }

    public void setInteractionModel(InteractionModel newModel) {
        iModel = newModel;
    }

    public void setModel(BoxModel newModel) {
        model = newModel;
    }

    public void handlePressed(double normX, double normY, MouseEvent event) {
        prevX = normX;
        prevY = normY;

        switch (currentState) {
            case READY -> {
                // context: are we on a box?
                boolean hit = model.checkHit(normX, normY);
                if (hit) {
                    // side effects:
                    // - set selection
                    iModel.setSelection(model.whichBox(normX, normY));
                    // move to new state
                    currentState = BoxControllerA.State.DRAGGING;
                } else {
                    // side effects
                    // - create new box
                    // - set selection
                    model.createBox(normX, normY);
                    iModel.setSelection(model.whichBox(normX, normY));
                    // move to new state
                    currentState = BoxControllerA.State.DRAGGING;
                }
            }
        }
    }

    public void handleDragged(double normX, double normY, MouseEvent event) {
        double dX = normX - prevX;
        double dY = normY - prevY;
        prevX = normX;
        prevY = normY;

        switch (currentState) {
            case DRAGGING -> {
                // context: none
                // side effects:
                // - move box
                model.moveBox(iModel.selection, dX, dY);
                // stay in this state
            }
        }
    }

    public void handleReleased(double normX, double normY, MouseEvent event) {
        switch (currentState) {
            case DRAGGING -> {
                // context: none
                // side effects:
                // - set to no selection
                iModel.unselect();
                // move to new state
                currentState = BoxControllerA.State.READY;
            }
        }
    }

    public void handleMoved(double normX, double normY, MouseEvent event) {
    }

}
