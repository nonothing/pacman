package com.codenjoy.dojo.packman.model;

import com.codenjoy.dojo.packman.view.Texture;
import com.codenjoy.dojo.packman.model.Rectangle;

public abstract class WorldObject {

    private static final int SIZE = 30;

    private Texture texture;

    protected Point position;
    protected Rectangle bounds;

    public WorldObject(Point point, Texture texture) {

        position = new Point(point.getX(), point.getY(), SIZE);
        bounds = new Rectangle(position.getX(), position.getY(), SIZE, SIZE);

        this.texture = texture;
    }

    public int getSize() {
        return SIZE;
    }

    public Point getPosition() {
        return position;
    }

    public Texture getTexture() {
        return texture;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public void animate() {
        // do nothing
    }

    public int inverse(int count) {
        return (-1) * count;
    }
}
