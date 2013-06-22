package com.codenjoy.dojo.packman.model;

import com.codenjoy.dojo.packman.model.Brick;
import com.codenjoy.dojo.packman.view.Texture;



public class Player extends WorldObject{

	private static final int SPEED = 5;
    private State state;
    private int life;


	public Player(Point position , Texture texture ) {
	    super(position, texture);
		setState(State.DEFENCE);
	}
	
    public boolean eatPoint(Iterable<Brick> bricks) {
        for (Brick brick : bricks) {
            if(brick.tryToEat(next))
                return true;
        }
        return false;
    }
    
    public boolean eatBonus(Iterable<Brick> bricks) { 
        for (Brick brick : bricks) {
            if(brick.tryToBonus(next)){
                state = State.ATTACK;
                return true;
                }
        }
        return false;
    }
    
    public void animate() {
        boolean isOpen = (getPosition().getX() % 15 == 0 && (direction == Direction.LEFT || direction == Direction.RIGHT))
                || (getPosition().getY() % 15 == 0 && (direction == Direction.UP || direction == Direction.DOWN));

        if (direction == Direction.LEFT) {
            if (isOpen) {
                setTexture(Texture.pacmanLeftOpen);
            } else {
                setTexture(Texture.pacmanLeftClose);
            }
        }
        if (direction == Direction.RIGHT) {
            if (isOpen) {
                setTexture(Texture.pacmanRightOpen);
            } else {
                setTexture(Texture.pacmanRightClose);
            }
        }
        if (direction == Direction.UP) {
            if (isOpen) {
                setTexture(Texture.pacmanUpOpen);
            } else {
                setTexture(Texture.pacmanUpClose);
            }
        }
        if (direction == Direction.DOWN) {
            if (isOpen) {
                setTexture(Texture.pacmanDownOpen);
            } else {
                setTexture(Texture.pacmanDownClose);
            }
        }
    }
    

    
    public void onMove(Direction direction) {
        switch (direction) {
        case RIGHT:setNext(SPEED, 0);         break;
        case LEFT: setNext(inverse(SPEED), 0);break;
        case UP:   setNext(0, inverse(SPEED));break;
        case DOWN: setNext(0, SPEED);         break;
        }
    }
    
	public int getSpeed(){
        return SPEED;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

}