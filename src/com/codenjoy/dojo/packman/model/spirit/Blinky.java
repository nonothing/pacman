package com.codenjoy.dojo.packman.model.spirit;

import com.codenjoy.dojo.packman.model.Point;
import com.codenjoy.dojo.packman.model.World;
import com.codenjoy.dojo.packman.model.spirit.Spirit;
import com.codenjoy.dojo.packman.view.Texture;


public class Blinky extends Spirit{
    
    private static final Point START_POINT   = new Point(8, 11);
    private static final Point DEFENCE_POINT = new Point(13, 1);

    public Blinky() {
        super(START_POINT, Texture.blinkyUp);
    }

    @Override
    public void ai(World world) {
        switch (getState()) {
        case ATTACK:
            findPathAttack(world, world.getPlayer(),this);
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

    @Override
    public Texture left() {
        return Texture.blinkyLeft;
    }
    @Override
    public Texture right() {
        return Texture.blinkyRight;
    }
    @Override
    public Texture down() {
        return Texture.blinkyDown;
    }
    @Override
    public Texture up() {
        return Texture.blinkyUp;
    }


}
