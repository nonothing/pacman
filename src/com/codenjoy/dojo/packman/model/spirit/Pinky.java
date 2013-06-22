package com.codenjoy.dojo.packman.model.spirit;


import com.codenjoy.dojo.packman.model.Direction;
import com.codenjoy.dojo.packman.model.Player;
import com.codenjoy.dojo.packman.model.Point;
import com.codenjoy.dojo.packman.model.World;
import com.codenjoy.dojo.packman.model.spirit.Spirit;
import com.codenjoy.dojo.packman.view.Texture;


public class Pinky extends Spirit {

    private static final Point START_POINT = new Point(9, 11);
    private static final Point DEFENCE_POINT = new Point(1, 2);

    public Pinky() {
        super(START_POINT, Texture.pinkyLeft);
    }

    @Override
    public void go(World world) {
        switch (getState()) {
        case ATTACK:
            findPathAttack(world, findPathFourStep(world), this);
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

    private Player findPathFourStep(World world) {
        world.getMap().potencialMap(world.getPlayer(), this, world.getBricks());
        Player player = new Player(new Point(0, 0), Texture.none);
        int[][] map = world.getMap().getMap();
        int min = 0;
        int max = 999;

        for (int row = 0; row < world.getWidth(); row++) {
            for (int column = 0; column < world.getHeight(); column++) {
                if (map[row][column] == 5) {

                    if (world.getPlayer().getDirection() == Direction.LEFT) {
                        if (max > row) {
                            max = row;
                            player.setPosition(new Point(row, column),player.getSize());
                        }
                    }

                    if (world.getPlayer().getDirection() == Direction.RIGHT) {
                        if (min < row) {
                            min = row;
                            player.setPosition(new Point(row, column),player.getSize());
                        }
                    }

                    if (world.getPlayer().getDirection() == Direction.UP) {
                        if (max > column) {
                            max = column;
                            player.setPosition(new Point(row, column),player.getSize());
                        }
                    }

                    if (world.getPlayer().getDirection() == Direction.DOWN) {
                        if (min < column) {
                            min = column;
                            player.setPosition(new Point(row, column),player.getSize());
                        }
                    }

                }
            }
        }

        return player;
    }

    @Override
    public Texture left() {
        return Texture.pinkyLeft;
    }

    @Override
    public Texture right() {
        return Texture.pinkyRight;
    }

    @Override
    public Texture down() {
        return Texture.pinkyDown;
    }

    @Override
    public Texture up() {
        return Texture.pinkyUp;
    }



}
