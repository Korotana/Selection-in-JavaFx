package com.example.box2021demo;

import javafx.scene.input.MouseEvent;

public class BoxControllerC {
    InteractionModel iModel;
    BoxModel model;
    double prevX, prevY;

    private enum State {
        READY, PREPARE_CREATE, DRAGGING
    }

    private State currentState;

    public BoxControllerC() {
        currentState = State.READY;
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
                    currentState = State.DRAGGING;
                } else {
                    // side effects
                    // - none
                    // move to new state
                    currentState = State.PREPARE_CREATE;
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
            case PREPARE_CREATE -> {
                // context: none
                // side effects:
                // - none
                // move to new state
                currentState = State.READY;
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
                currentState = State.READY;
            }
            case PREPARE_CREATE -> {
                // context: none
                // side effects
                // - create box
                model.createBox(normX,normY);
                // move to new state
                currentState = State.READY;
            }
        }
    }

    public void handleMoved(double normX, double normY, MouseEvent event) {
    }

}
