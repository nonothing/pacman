package com.codenjoy.dojo.packman.model;

import java.util.ArrayList;
import java.util.List;

import com.codenjoy.dojo.packman.model.Brick;
import com.codenjoy.dojo.packman.model.Player;
import com.codenjoy.dojo.packman.model.spirit.*;
import com.codenjoy.dojo.packman.view.Texture;

public class World {
    
    private Player player;
    
    public List<Brick> bricks;
    public List<Brick> oldBricks;
    private List<Spirit> spirits;
    private int  width;
    private int  height;
    private int countPoint;

    public World(Level level) {
        oldBricks = level.getBricks();
        bricks = level.getBricks();
        width = level.getWidth();
        height = level.getHeight();
        player = level.getPlayer();
        spirits = new ArrayList<Spirit>();

        newGame();
     }
 
    public void startPointPlayer(){
        player.setState(State.DEFENCE);
        player.setPosition(new Rectangle(8 * player.getSize(), 13 * player.getSize(), player.getSize(), player.getSize()));
    }
    
    public void createSpirit() {
        if (spirits != null) {
            spirits.clear();
        }
        
        spirits.add(new Blinky());
        spirits.add(new Clyde());
        spirits.add(new Pinky());
        spirits.add(new Inky());
    }
       
    public boolean collidesWithLevel(Rectangle rect) {
        for (Brick brick : bricks) {
            if (brick.getBounds().intersects(rect)
                    && brick.getTexture() != Texture.background
                    && brick.getTexture() != Texture.point
                    && brick.getTexture() != Texture.bonus
                    && brick.getTexture() != Texture.none) {
                return true;
            }
        }
        return false;
    }

    public int generationPoint() {
        int result = 0;
        for (Brick brick : bricks) {
            if (brick.getTexture() == Texture.background) {
                brick.setTexture(Texture.point);
            }
            if (brick.getTexture() == Texture.point) {
                result++;
            }
        }
        return result;
    }

    public void newGame(){
        for (int i = 0; i < oldBricks.size(); i++) {
            bricks.get(i).setTexture(oldBricks.get(i).getTexture());
        }

        createSpirit();
        startPointPlayer();
        countPoint = generationPoint(); 
    }

    public boolean eatPoint(){
        if (player.eatPoint(getBricks())) {
            countPoint--;
            return true;
        }
        return false;
    }
    
    public boolean eatBonus(){
        if (player.eatBonus(getBricks())) {
            defenceNPC();
            return true;
        }
        return false;
    }
    
    public void tryToPlayerGo(Direction direction) {
        player.onMove(direction);
   
        if (!collidesWithLevel(player.getBounds())) {
            player.setDirection(direction);
        }
        player.onMove(player.getDirection());
        
        if (!collidesWithLevel(player.getBounds())) {
            player.setPosition(player.getBounds());
        }
    }

    private void defenceNPC() {
        for (Spirit spirit : getSpirits()) {
            if (spirit.getState() == State.ATTACK) {
                spirit.setState(State.DEFENCE);
            }
        }
    }

    public void attackNPC() {
        for (Spirit spirit : getSpirits()) {
            if (spirit.getState() == State.DEFENCE) {
                spirit.setState(State.ATTACK);
            }
        }
        player.setState(State.DEFENCE);
    }


    public boolean deadPlayer (){
        for (Spirit spirit : getSpirits()) {
            if ((spirit.getBounds().intersects(player.getBounds()))) {
                if (spirit.getState() == State.ATTACK){
                    player.setState(State.DEAD);
                    player.setLife(player.getLife() - 1);
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean deadSpirit(){
        for (Spirit spirit : getSpirits()) {
            if ((spirit.getBounds().intersects(player.getBounds()))) {
                if (player.getState() == State.ATTACK && spirit.getState() != State.DEAD){
                    spirit.setState(State.DEAD);
                    return true;
                }
            }
        }
        return false;
    }
    public boolean isVictory(){
        if(countPoint <= 0 )
            return true;
        return false;
    }
    
    public boolean isGameOver(){
        if(player.getLife() <= 0 )
            return true;
        return false;
    }
    
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Iterable<Brick> getBricks() {
        return bricks;
    }

    public Player getPlayer() {
        return player;
    }

    public List<Spirit> getSpirits() {
        return spirits;
    }
    
}
