package com.example.box2021demo;

import javafx.scene.input.MouseEvent;

public class BoxControllerB {
    InteractionModel iModel;
    BoxModel model;
    double prevX, prevY;

    private enum State {
        READY, HIGHLIGHT
    }

    private BoxControllerB.State currentState;

    public BoxControllerB() {
        currentState = State.READY;
    }

    public void setInteractionModel(InteractionModel newModel) {
        iModel = newModel;
    }

    public void setModel(BoxModel newModel) {
        model = newModel;
    }

    public void handlePressed(double normX, double normY, MouseEvent event) {
    }

    public void handleMoved(double normX, double normY, MouseEvent event) {
        switch (currentState) {
            case READY -> {
                // context: are we on a box?
                boolean hit = model.checkHit(normX, normY);
                if (hit) {
                    // side effects:
                    // - set selection
                    iModel.setSelection(model.whichBox(normX, normY));
                    // move to new state
                    currentState = State.HIGHLIGHT;
                }
            }
            case HIGHLIGHT -> {
                // context: are we on a box?
                boolean hit = model.checkHit(normX, normY);
                if (!hit) {
                    // side effects:
                    // - unset selection
                    iModel.unselect();
                    // move to new state
                    currentState = State.READY;
                }
            }
        }
    }

    public void handleDragged(double normX, double normY, MouseEvent event) {
    }

    public void handleReleased(double normX, double normY, MouseEvent event) {
    }
}
