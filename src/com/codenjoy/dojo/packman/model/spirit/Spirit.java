package com.codenjoy.dojo.packman.model.spirit;

import com.codenjoy.dojo.packman.model.Direction;
import com.codenjoy.dojo.packman.model.Map;
import com.codenjoy.dojo.packman.model.Point;
import com.codenjoy.dojo.packman.model.Rectangle;
import com.codenjoy.dojo.packman.model.State;
import com.codenjoy.dojo.packman.model.World;
import com.codenjoy.dojo.packman.model.WorldObject;

import com.codenjoy.dojo.packman.view.Texture;

public abstract class Spirit extends WorldObject {

    private static final int SPEED = 5;

    private int countStep;
    private State state;
    private boolean leftDefence;
    protected Map maps;

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
            Rectangle staRectangle = new Rectangle(7 * getSize(),
                    10 * getSize(), getSize() + getSize(), getSize());
            if (staRectangle.intersects(bounds)) {
                state = State.ATTACK;
            }
        }

    }

    public void go(World world){
        maps = new Map(world.getWidth(), world.getHeight());
        ai(world);
    }
    protected void onLoadImageAttack() {
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
        switch (direction) {
        case RIGHT:
            setNext(SPEED, 0);
            break;
        case LEFT:
            setNext(inverse(SPEED), 0);
            break;
        case UP:
            setNext(0, inverse(SPEED));
            break;
        case DOWN:
            setNext(0, SPEED);
            break;
        }

        if (!world.collidesWithLevel(getBounds())) {
            setPosition(getBounds());
        }
        countStep++;
    }

    public void findPathAttack(World world, Point point, Spirit spirit) {
        maps.potencialMap(point, spirit, world.getBricks());

        if (getCountStep() == (getSize() / SPEED)) {
            int[][] map = maps.getMap();
            int x = getPosition().getX() / getSize();
            int y = getPosition().getY() / getSize();
            int step = map[x][y];

            if (map[x - 1][y] < step + 1)
                setDirection(Direction.LEFT);
            if (map[x + 1][y] < step + 1)
                setDirection(Direction.RIGHT);
            if (map[x][y - 1] < step + 1)
                setDirection(Direction.UP);
            if (map[x][y + 1] < step + 1)
                setDirection(Direction.DOWN);

            setCountStep(0);
        }
    }

    public void findPathDefence(World world, Point point, Spirit spirit) {
        maps.potencialMap(new Point(point.getX(), point.getY(), getSize()),
                spirit, world.getBricks());

        if (getCountStep() == (getSize() / SPEED)) {

            int[][] map = maps.getMap();
            int x = getPosition().getX() / getSize();
            int y = getPosition().getY() / getSize();

            int step = map[x][y];

            if (map[x - 1][y] < step + 1)
                setDirection(Direction.LEFT);
            if (map[x + 1][y] < step + 1)
                setDirection(Direction.RIGHT);
            if (map[x][y - 1] < step + 1)
                setDirection(Direction.UP);
            if (map[x][y + 1] < step + 1)
                setDirection(Direction.DOWN);

            setCountStep(0);
        }
    }

    public int getCountStep() {
        return countStep;
    }

    public void setCountStep(int countStep) {
        this.countStep = countStep;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setDefence(boolean isDefence) {
        this.leftDefence = isDefence;
    }

    public int getSize() {
        return super.getSize();
    }
}
