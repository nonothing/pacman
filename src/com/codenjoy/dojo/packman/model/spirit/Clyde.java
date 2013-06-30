package com.codenjoy.dojo.packman.model.spirit;

import com.codenjoy.dojo.packman.model.Point;
import com.codenjoy.dojo.packman.model.World;
import com.codenjoy.dojo.packman.model.spirit.Spirit;
import com.codenjoy.dojo.packman.view.Texture;

public class Clyde extends Spirit {

    private static final Point START_POINT = new Point(7, 11);
    private static final Point DEFENCE_POINT = new Point(2, 22);

    public Clyde() {
        super(START_POINT, Texture.clydeUp);
    }

    @Override
    public void ai(World world) {
        switch (getState()) {
        case ATTACK:
            AIattack(world);
            break;
        case DEFENCE:
            findDirection(world, DEFENCE_POINT.multiply(getSize()), this);
            break;
        case DEAD:
            findDirection(world, START_POINT.multiply(getSize()), this);
            break;
        }
        onMove(world);
    }

    public void AIattack(World world) {
        maps.potencialMap(world.getPlayer().getPosition(), this, world.getBricks());
        
        int[][] map = maps.getMap();
        int step = map[getPointX()][getPointY()];

        if (step <= 9) {
            findDirection(world, new Point(1 , 2, getSize()), this);
        } else {
            findDirection(world, world.getPlayer().getPosition(), this);
        }
    }

    @Override
    public Texture left() {
        return Texture.clydeLeft;
    }

    @Override
    public Texture right() {
        return Texture.clydeRight;
    }

    @Override
    public Texture down() {
        return Texture.clydeDown;
    }

    @Override
    public Texture up() {
        return Texture.clydeUp;
    }

}
