package com.codenjoy.dojo.packman.model;

import com.codenjoy.dojo.packman.view.Texture;
import com.codenjoy.dojo.packman.model.Rectangle;

public abstract class WorldObject {

    private static final int SIZE = 30;

    private Texture texture;
    protected Direction direction;
    protected Point position;
    protected Rectangle bounds;

    public WorldObject(Point point, Texture texture) {

        position = new Point(point.getX(), point.getY(), SIZE);
        bounds = new Rectangle(position.getX(), position.getY(), SIZE, SIZE);

        this.texture = texture;

        direction = Direction.UP;
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

    public Direction getDirection() {
        return direction;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setPosition(Rectangle rect) {
        this.position = new Point(rect);
        bounds = rect;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setNext(int speedX, int speedY) {
        this.bounds = new Rectangle(position.getX() + speedX, position.getY()
                + speedY, SIZE, SIZE);
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
