package com.codenjoy.dojo.packman.model;

import com.codenjoy.dojo.packman.view.Texture;


public class Brick extends WorldObject{
    public Brick(Point position, Texture texture) {
        super(position, texture);
        
    }
    
    boolean tryToEat(Rectangle rectangle) {
        if (bounds.intersects(rectangle) && getTexture() == Texture.point) {
            setTexture(Texture.background);
            return true;
        }
        return false;
    }
    
    boolean tryToBonus(Rectangle rectangle) {
        if (bounds.intersects(rectangle) && getTexture() == Texture.bonus) {
            setTexture(Texture.background);
            return true;
        }
        return false;
    }
}