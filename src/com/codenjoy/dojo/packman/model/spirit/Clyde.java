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
            findPathDefence(world, DEFENCE_POINT, this);
            break;
        case DEAD:
            findPathDefence(world, START_POINT, this);
            break;
        }
        onMove(world);
    }

    private void AIattack(World world) {
        maps.potencialMap(world.getPlayer().getPosition(), this, world.getBricks());
        
        int[][] map = maps.getMap();
        int x = getPosition().getX() / getSize();
        int y = getPosition().getY() / getSize();
        int step = map[x][y];

        if (step <= 9) {
            findPathAttack(world, new Point(1, 1), this);
        } else {
            findPathAttack(world, world.getPlayer().getPosition(), this);
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
