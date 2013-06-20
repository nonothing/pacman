package com.codenjoy.dojo.packman.model;

public class Menu {

    private static final int POSITION_MAX = 2;
    private int position;
    private boolean isSound;

    public Menu() {
        position = 0;
    }

    public void onDown() {
        position++;
        if (position > POSITION_MAX) {
            position = 0;
        }
    }

    public void onUp() {
        position--;
        if (position < 0) {
            position = POSITION_MAX;
        }
    }

    public int getPosition() {
        return position;
    }

    public boolean isSound() {
        return isSound;
    }

    public void setSound(boolean isSound) {
        this.isSound = isSound;
    }
    
}
