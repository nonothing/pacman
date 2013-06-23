package com.codenjoy.dojo.packman.model.spirit;


import com.codenjoy.dojo.packman.model.Direction;
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
            findPathAttack(world, doubleVectorBetweenTwoPoints(findPathTwoStep(world), world.getSpirits().get(0).getPosition() ), this);
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

    private Point doubleVectorBetweenTwoPoints(Point point1, Point point2) {

        int[] aMatrix = { point2.getX() / 30, point2.getY() / 30, 1 };
        int[][] bMatrix = { { -1, 0, 0 }, { 0, -1, 0 },
                { 2 * (point1.getX() / 30), 2 * (point1.getY() / 30), 1 } };

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

        point1 = new Point(point[X], point[Y],30);

        return point1;
    }


    private Point findPathTwoStep(World world) {
        maps.potencialMap(world.getPlayer().getPosition(), this, world.getBricks());
        
        int[][] map =maps.getMap();
        int min = 0;
        int max = 999;

        Point point = new Point(0,0);
        for (int row = 0; row < world.getWidth(); row++) {
            for (int column = 0; column < world.getHeight(); column++) {
                if (map[row][column] == 3) {

                    if (world.getPlayer().getDirection() == Direction.LEFT) {
                        if (max > row) {
                            max = row;
                            point = new Point(row, column,getSize());
                        }
                    }

                    if (world.getPlayer().getDirection() == Direction.RIGHT) {
                        if (min < row) {
                            min = row;
                            point = new Point(row, column,getSize());
                        }
                    }

                    if (world.getPlayer().getDirection() == Direction.UP) {
                        if (max > column) {
                            max = column;
                            point = new Point(row, column,getSize());
                        }
                    }

                    if (world.getPlayer().getDirection() == Direction.DOWN) {
                        if (min < column) {
                            min = column;
                            point = new Point(row, column,getSize());
                        }
                    }

                }
            }
        }

        return point;
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
