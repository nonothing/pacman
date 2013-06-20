package com.codenjoy.dojo.packman.model;

import java.util.ArrayList;
import java.util.List;
import com.codenjoy.dojo.packman.model.Brick;
import com.codenjoy.dojo.packman.model.Player;
import com.codenjoy.dojo.packman.model.spirit.Blinky;
import com.codenjoy.dojo.packman.model.spirit.Clyde;
import com.codenjoy.dojo.packman.model.spirit.Inky;
import com.codenjoy.dojo.packman.model.spirit.Pinky;
import com.codenjoy.dojo.packman.model.spirit.Spirit;
import com.codenjoy.dojo.packman.view.Sound;
import com.codenjoy.dojo.packman.view.Texture;
import com.codenjoy.dojo.packman.view.Wave;


public class World {
    
    private Player player;
    
    private List<Brick> bricks;
    private List<Spirit> spirits;
    private Map map;
    private  int  width;
    private  int  height;
    private Sound sound;
    private Sound backgroundSound;
    private boolean isSound;
    private int countPoint;

    public World(Level level) {
        bricks = level.getBricks();
        width = level.getWidth();
        height = level.getHeight();
        player = level.getPlayer();
        map = new Map(width, height);
        spirits = new ArrayList<Spirit>();
        sound = new Sound(); 
        backgroundSound = new Sound();
        newGame();
     }

    public void playBackground(){
        if(isSound){
        backgroundSound.play(Wave.sirenSound, true);
        }
    }
    
    public void stopBackground(){
        backgroundSound.stop();
    }
    
    public void startPointPlayer(){
        player.setState(State.DEFENCE);
        player.setPosition(new Point(8 ,13),player.getSize());
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

    private int generationPoint() {
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
        createSpirit();
        startPointPlayer();
        countPoint = generationPoint(); 
    }
    
    private void processInput(Direction direction) {

        player.onMove(direction);

        if (!collidesWithLevel(player.getNext())) {
            player.setDirection(direction);
        }
        player.onMove(player.getDirection());

        if (!collidesWithLevel(player.getNext())) {

            if (player.eatPoint(getBricks())) {
                countPoint--;
                if (isSound) {
                    sound.play(Wave.chomp, false);
                }
            }

            if (player.eatBonus(getBricks())) {
                if (isSound) {
                    sound.play(Wave.eatFruit, false);
                }
                defenceNPC();
            }

            player.setPosition(player.getNext());
        }
    }

    public void defenceNPC() {
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

    private void collisionPlayerNPC() {
        for (Spirit spirit : getSpirits()) {
            if ((spirit.getBounds().intersects(player.getNext()))) {
                if (spirit.getState() == State.ATTACK){
                    if(isSound){
                    sound.play(Wave.death, false);
                    }
                    player.setState(State.DEAD);
                    player.setLife(player.getLife() - 1);
                }
                if (player.getState() == State.ATTACK && spirit.getState() != State.DEAD){
                    if(isSound){
                    sound.play(Wave.eatSpirit, false);
                    }
                    spirit.setState(State.DEAD);
                }
            }
        }

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
    
    public void tryToPlayerGo(Direction direction) {
        processInput(direction);
        collisionPlayerNPC();
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
    
    public Map getMap() {
        return map;
    }

    public Sound getSound(){
        return sound;
    }

    public boolean isSound() {
        return isSound;
    }
    public void setSound(boolean isSound) {
        this.isSound = isSound;
    }
    
}
