package com.codenjoy.dojo.packman.model;

public class Point {

    private int x;
    private int y;
    public Point () {

    }

    public Point (int x, int y) {
      this.x = x;
      this.y = y;
    }

    public Point(Point position) {
        this.x = position.x;
        this.y = position.y;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
