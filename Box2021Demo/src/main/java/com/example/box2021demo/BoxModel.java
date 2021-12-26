package com.example.box2021demo;

import java.util.ArrayList;

public class BoxModel {
    public ArrayList<Box> boxes;
    ArrayList<BoxModelListener> subscribers;

    public BoxModel() {
        subscribers = new ArrayList<>();
        boxes = new ArrayList<>();
    }

    public void createBox(double left, double top) {
        Box b = new Box(left - 0.05, top-0.05, 0.1, 0.1);
        boxes.add(b);
        notifySubscribers();
    }

    public boolean checkHit(double x, double y) {
        return boxes.stream()
                .anyMatch(b -> b.checkHit(x,y));
    }

    public Box whichBox(double x, double y) {
        Box found = null;
        for (Box b : boxes) {
            if (b.checkHit(x,y)) {
                found = b;
            }
        }
        return found;
    }

    public void moveBox(Box b, double dX, double dY) {
        b.move(dX,dY);
        notifySubscribers();
    }

    public void addSubscriber (BoxModelListener aSub) {
        subscribers.add(aSub);
    }

    private void notifySubscribers() {
        subscribers.forEach(sub -> sub.modelChanged());
    }
}
