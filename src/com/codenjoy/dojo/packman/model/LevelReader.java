package com.codenjoy.dojo.packman.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.codenjoy.dojo.packman.view.Texture;

public class LevelReader implements Level{

    private List<Integer> list = new ArrayList<Integer>();
    
    public LevelReader(String file){
        readFromFile(file);
    }
    
    private void readFromFile(String file) {
        Scanner scanner;
        try {
            scanner = new Scanner(new File(file));
            while (scanner.hasNext()) {
                list.add(scanner.nextInt());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Override
    public int getWidth() {
        return 16;
    }

    @Override
    public int getHeight() {
        return 24;
    }

    @Override
    public List<Brick> getBricks() {
        List<Brick> bricks = new LinkedList<Brick>();

        int x = 0;
        int y = 0;
        for (int i = 0; i < list.size(); i++) {

            switch (list.get(i)) {
                case 1:
                    bricks.add(new Brick(new Point(x, y), Texture.point));
                    break;
                case 2:
                    bricks.add(new Brick(new Point(x, y), Texture.horizontal));
                    break;
                case 3:
                    bricks.add(new Brick(new Point(x, y), Texture.vertical));
                    break;
                case 4:
                    bricks.add(new Brick(new Point(x, y), Texture.angleLeftUp));
                    break;
                case 5:
                    bricks.add(new Brick(new Point(x, y), Texture.angleRightUp));
                    break;
                case 6:
                    bricks.add(new Brick(new Point(x, y), Texture.angleRightDown));
                    break;
                case 7:
                    bricks.add(new Brick(new Point(x, y), Texture.angleLeftDown));
                    break;
                case 8:
                    bricks.add(new Brick(new Point(x, y), Texture.arcDown));
                    break;
                case 9:
                    bricks.add(new Brick(new Point(x, y), Texture.arcLeft));
                    break;
                case 10:
                    bricks.add(new Brick(new Point(x, y), Texture.arcRight));
                    break;
                case 11:
                    bricks.add(new Brick(new Point(x, y), Texture.arcUp));
                    break;
                case 12:
                    bricks.add(new Brick(new Point(x, y), Texture.arc2Down));
                    break;
                case 13:
                    bricks.add(new Brick(new Point(x, y), Texture.arc2Right));
                    break;
                case 14:
                    bricks.add(new Brick(new Point(x, y), Texture.arc2Left));
                    break;
                case 15:
                    bricks.add(new Brick(new Point(x, y), Texture.arc2Up));
                    break;
                case 16: 
                    bricks.add(new Brick(new Point(x, y), Texture.bonus));
                    break; 
                case 99: 
                    bricks.add(new Brick(new Point(x, y), Texture.none));
                    break; 
                default:
                    bricks.add(new Brick(new Point(x, y), Texture.background));
                    break;
            }
            x++;
            if (x == getWidth()) {
                x = 0;
                y++;
            }
            if (y == getHeight()) {
                y = 0;
            }
        }
        return bricks;
    }



    @Override
    public Player getPlayer() {
        return new Player(new Point(8,13),Texture.pacmanLeftOpen);
    }

}
