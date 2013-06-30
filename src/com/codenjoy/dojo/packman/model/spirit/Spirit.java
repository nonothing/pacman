package com.codenjoy.dojo.packman.model.spirit;

import com.codenjoy.dojo.packman.model.Direction;
import com.codenjoy.dojo.packman.model.Map;
import com.codenjoy.dojo.packman.model.Point;
import com.codenjoy.dojo.packman.model.Rectangle;
import com.codenjoy.dojo.packman.model.State;
import com.codenjoy.dojo.packman.model.World;
import com.codenjoy.dojo.packman.model.WorldObjectMove;

import com.codenjoy.dojo.packman.view.Texture;

public abstract class Spirit extends WorldObjectMove {

    private int countStep;
    private boolean leftDefence;
    public Map maps;

    public Spirit(Point position, Texture texture) {
        super(position, texture);

        setState(State.ATTACK);
        setCountStep(6);
    }

    protected abstract void ai(World world);

    public abstract Texture left();

    public abstract Texture right();

    public abstract Texture down();

    public abstract Texture up();

    private void refresh() {

        if (getState() == State.DEAD) {
            Rectangle startRectangle = new Rectangle(7 * getSize(),
                    10 * getSize(), getSize() + getSize(), getSize());
            if (startRectangle.intersects(bounds)) {
                state = State.ATTACK;
            }
        }

    }

    public void go(World world) {
        maps = new Map(world.getWidth(), world.getHeight());
        ai(world);
    }

    private void onLoadImageAttack() {
        switch (direction) {
        case LEFT:
            setTexture(left());
            break;
        case RIGHT:
            setTexture(right());
            break;
        case DOWN:
            setTexture(down());
            break;
        case UP:
            setTexture(up());
            break;
        }
    }

    private void onLoadImageDead() {
        switch (direction) {
        case LEFT:
            setTexture(Texture.orbLeft);
            break;
        case RIGHT:
            setTexture(Texture.orbRight);
            break;
        case UP:
            setTexture(Texture.orbUp);
            break;
        case DOWN:
            setTexture(Texture.orbDown);
            break;
        }
    }

    private void onLoadImageDefence(boolean isWhite) {
        if (isWhite) {
            setTexture(Texture.spiritDefenceWhite);
        } else {
            setTexture(Texture.spiritDefence);
        }
    }

    private void onLoadImage() {
        switch (getState()) {
        case ATTACK:
            onLoadImageAttack();
            break;
        case DEFENCE:
            onLoadImageDefence(leftDefence);
            break;
        case DEAD:
            onLoadImageDead();
            break;
        }
    }

    public void onMove(World world) {
        onLoadImage();
        refresh();
        onMove(direction);

        if (!world.collidesWithLevel(getBounds())) {
            setPosition(getBounds());
        }
        countStep++;
    }

    protected void findDirection(World world, Point point, Spirit spirit) {
        
        maps.potencialMap(point, spirit, world.getBricks());
        if (getCountStep() == (getSize() / SPEED)) {
            int[][] map = maps.getMap();

            int step = map[getPointX()][getPointY()];

            if (map[getPointX() - 1][getPointY()] < step + 1) {
                setDirection(Direction.LEFT);
            }
            if (map[getPointX() + 1][getPointY()] < step + 1) {
                setDirection(Direction.RIGHT);
            }
            if (map[getPointX()][getPointY() - 1] < step + 1) {
                setDirection(Direction.UP);
            }
            if (map[getPointX()][getPointY() + 1] < step + 1) {
                setDirection(Direction.DOWN);
            }

            setCountStep(0);
        }
    }

    public int getCountStep() {
        return countStep;
    }

    public void setCountStep(int countStep) {
        this.countStep = countStep;
    }

    public void setDefence(boolean isDefence) {
        this.leftDefence = isDefence;
    }
}
