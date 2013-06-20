package com.codenjoy.dojo.packman.model.spirit;


import com.codenjoy.dojo.packman.model.Direction;
import com.codenjoy.dojo.packman.model.Player;
import com.codenjoy.dojo.packman.model.Point;
import com.codenjoy.dojo.packman.model.World;
import com.codenjoy.dojo.packman.model.spirit.Spirit;
import com.codenjoy.dojo.packman.view.Texture;


public class Inky extends Spirit {
    private static final int Y = 1;
    private static final int X = 0;
    private static final Point START_POINT   = new Point(6, 11);
    private static final Point DEFENCE_POINT = new Point(13, 22);
    public Inky() {
       super(START_POINT, Texture.inkyRight);
    }


    @Override
    public void ai(World world) {
        switch (getState()) {
        case ATTACK:
            findPathAttack(world, doubleVectorBetweenTwoPoints(findPathTwoStep(world), world.getSpirits().get(0) ), this);
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

    private Player doubleVectorBetweenTwoPoints(Player point1, Spirit point2) {

        int[] aMatrix = { point2.getPosition().getX() / point2.getSize(),
                point2.getPosition().getY() / point2.getSize(), 1 };
        int[][] bMatrix = {
                { -1, 0, 0 },
                { 0, -1, 0 },
                { 2 * (point1.getPosition().getX() / point1.getSize()),
                        2 * (point1.getPosition().getY() / point1.getSize()), 1 } };

        int[] point = new int[3];

        for (int column = 0; column < 3; column++) {
            for (int inner = 0; inner < 3; inner++) {
                point[column] += aMatrix[inner] * bMatrix[inner][column];
            }

        }

        if (point[X] >= 15) {
            point[X] = 14;
        }
        if (point[X] < 1) {
            point[X] = 1;
        }
        if (point[Y] >= 23) {
            point[Y] = 22;
        }
        if (point[Y] < 1) {
            point[Y] = 1;
        }

        point1.setPosition(new Point(point[X], point[Y]), point1.getSize());

        return point1;
    }


    private Player findPathTwoStep(World world) {
        
        world.getMap().potencialMap(world.getPlayer(), this, world.getBricks());
        
        int[][] map = world.getMap().getMap();
        int min = 0;
        int max = 999;

        Player player = new Player(new Point(0,0), Texture.none);
        for (int row = 0; row < world.getWidth(); row++) {
            for (int column = 0; column < world.getHeight(); column++) {
                if (map[row][column] == 3) {

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
        return Texture.inkyLeft;
    }

    @Override
    public Texture right() {
        return Texture.inkyRight;
    }

    @Override
    public Texture down() {
        return Texture.inkyDown;
    }

    @Override
    public Texture up() {
        return Texture.inkyUp;
    }



}
