package com.codenjoy.dojo.packman.model;

import com.codenjoy.dojo.packman.view.Texture;
import com.codenjoy.dojo.packman.model.Rectangle;

/**
 * User: sanja
 * Date: 15.06.13
 * Time: 18:53
 */
public abstract class WorldObject {

    private static final int SIZE = 30;

    private Texture texture;
    protected Direction direction;
    protected Point position;
    protected Rectangle next;
    protected Rectangle bounds;

    public WorldObject(Point point, Texture texture) {
        position = new Point(point.getX() * SIZE, point.getY() * SIZE);
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

    public Rectangle getNext() {
        return next;
    }

    public Direction getDirection() {
        return direction;
    }

    public Rectangle getBounds() {
        return bounds;
    }
    public void setPosition(int x, int y) {
        this.position = new Point(x, y);
    }

    public void setPosition(Point position) {
        this.position = new Point(position.getX(), position.getY());
    }

    public void setPosition(Rectangle rect) {
        this.position = new Point(rect.getX(), rect.getY());
        bounds = rect;
    }
    
    public void setPosition(Point position, int size) {
        this.position = new Point(position.getX() * size, position.getY() * size);
    }


    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setNext(Rectangle next) {
        this.next = next;
    }

    public void setNext(int speedX, int speedY) {
        this.next = new Rectangle(position.getX() + speedX, position.getY() + speedY,
                SIZE, SIZE);
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public void animate() {
        // do nothing
    }
    
    public int inverse(int count){
        return (-1) * count;
        
    }
}
