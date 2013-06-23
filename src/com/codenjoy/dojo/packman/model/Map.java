package com.codenjoy.dojo.packman.model;

import com.codenjoy.dojo.packman.model.spirit.Spirit;
import com.codenjoy.dojo.packman.view.Texture;

public class Map {
    private static final int WALL = 200;
    private int[][] map;
    private int step;
    private int width;
    private int height;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        map = new int[width][height];
    }

    public void potencialMap(Point point, Spirit spirit, Iterable<? extends WorldObject> object) {
        inverseMap(object);
        int count = 0;
        step = 2;

        map[point.getX() / 30][point.getY() / 30] = 1;

        if (spirit.getState() != State.DEAD) {
            changeMap(spirit);
        }

        while (count < 40) {
            for (int row = 0; row < width; row++) {
                for (int column = 0; column < height; column++) {
                    if (map[row][column] == step - 1) {
                        if (row > 1) {
                            if (map[row - 1][column] == 0) {
                                map[row - 1][column] = step;
                            }
                        }
                        if (row < width - 1) {
                            if (map[row + 1][column] == 0) {
                                map[row + 1][column] = step;
                            }
                        }
                        if (column > 1) {
                            if (map[row][column - 1] == 0) {
                                map[row][column - 1] = step;
                            }
                        }
                        if (column < height - 1) {
                            if (map[row][column + 1] == 0) {
                                map[row][column + 1] = step;
                            }
                        }
                    }
                }
            }
            step++;
            count++;
        }

    }

    private void changeMap(Spirit spirit) {
        if (spirit.getDirection() == Direction.LEFT)
            map[(spirit.getPosition().getX() / spirit.getSize()) + 1][spirit
                    .getPosition().getY() / spirit.getSize()] = WALL;

        if (spirit.getDirection() == Direction.RIGHT)
            map[(spirit.getPosition().getX() / spirit.getSize()) - 1][spirit
                    .getPosition().getY() / spirit.getSize()] = WALL;

        if (spirit.getDirection() == Direction.UP)
            map[spirit.getPosition().getX() / spirit.getSize()][(spirit
                    .getPosition().getY() / spirit.getSize()) + 1] = WALL;

        if (spirit.getDirection() == Direction.DOWN)
            map[spirit.getPosition().getX() / spirit.getSize()][(spirit
                    .getPosition().getY() / spirit.getSize()) - 1] = WALL;
    }

    public void inverseMap(Iterable<? extends WorldObject> bricks) {
        int row = 0;
        int column = 0;
        for (WorldObject object : bricks) {
            if (object.getTexture() == Texture.background
                    || object.getTexture() == Texture.point
                    || object.getTexture() == Texture.none
                    || object.getTexture() == Texture.bonus) {
                map[row][column] = 0;
            } else {
                map[row][column] = WALL;
            }
            row++;
            if (row == width) {
                row = 0;
                column++;
            }
            if (column == height) {
                column = 0;
            }
        }
    }

    public int[][] getMap() {
        return map;
    }
}
