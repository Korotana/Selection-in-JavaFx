package com.example.box2021demo;

import java.util.ArrayList;

public class InteractionModel {
    Box selection;
    ArrayList<BoxModelListener> subscribers;

    public InteractionModel() {
        subscribers = new ArrayList<>();
        selection = null;
    }

    public void setSelection(Box b) {
        selection = b;
        notifySubscribers();
    }

    public void addSubscriber (BoxModelListener aSub) {
        subscribers.add(aSub);
    }

    private void notifySubscribers() {
        subscribers.forEach(sub -> sub.modelChanged());
    }

    public Box getSelection() {
        return selection;
    }

    public void unselect() {
        selection = null;
        notifySubscribers();
    }
}
