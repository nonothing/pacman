package com.codenjoy.dojo.model;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.codenjoy.dojo.packman.model.Brick;
import com.codenjoy.dojo.packman.model.Direction;
import com.codenjoy.dojo.packman.model.LevelReader;
import com.codenjoy.dojo.packman.model.Rectangle;
import com.codenjoy.dojo.packman.model.State;
import com.codenjoy.dojo.packman.model.World;
import com.codenjoy.dojo.packman.model.spirit.Spirit;
import com.codenjoy.dojo.packman.view.Texture;

public class TestWorld {
   private World world;
    
    @Before public void initialization(){
        world  = new World(new LevelReader( "res/lvl_1.txt"));
    }
    
    @Test public void testIsNotGameOver() {
        assertEquals(false, world.isGameOver()); 
    }
    
    @Test public void testIsGameOver() {
        world.getPlayer().setLife(0);
        assertEquals(true, world.isGameOver()); 
    }
    @Test public void testEatPoint(){
        assertTrue(world.eatPoint());
    }
    
    @Test public void testEatNotPoint(){
        assertTrue(world.eatPoint());
        assertFalse(world.eatPoint());
    }
    
    @Test public void testEatBonus(){
        world.getPlayer().setPosition(new Rectangle(150, 450, 30, 30)) ;
        assertTrue(world.eatBonus());
    }
    
    @Test public void testEatNotBonus(){
       assertFalse(world.eatBonus());
    }

    @Test
    public void testAttackNPC() {
        for (Spirit spirit : world.getSpirits()) {
            spirit.setState(State.DEFENCE);
        }
        world.attackNPC();
        for (Spirit spirit : world.getSpirits()) {
            assertEquals(State.ATTACK, spirit.getState());
        }
        assertEquals(State.DEFENCE, world.getPlayer().getState());
    }
    
    @Test public void testIsPlayer() {
        assertNotNull(world.getPlayer());
    }
    
    @Test public void testMovePlayer() {
        world.tryToPlayerGo(Direction.LEFT);
        assertPlayer( 235,390);

        world.tryToPlayerGo(Direction.DOWN);
        assertPlayer( 235,395);
        
        world.tryToPlayerGo(Direction.RIGHT);
        assertPlayer(240,395);

        world.tryToPlayerGo(Direction.UP);
        assertPlayer( 240,390);
        //this is collision
        world.tryToPlayerGo(Direction.UP);
        assertPlayer( 240,390);
    }
    
    @Test public void testStartPointPlayer() {
        assertEquals(State.DEFENCE, world.getPlayer().getState());
        assertPlayer( 240,390);
    }
    
    @Test public void testIsNotNullArray() {
    assertNotNull(world.getSpirits());
    }
    
    @Test public void testNewGame() {
        world.newGame();
        assertTrue(assertEqualsList(world.oldBricks, world.bricks));
        testStartPointPlayer();
        testGenerationPoint();
    }
    
    @Test public void testCollisionWithLevel() {
        assertFalse(world.collidesWithLevel( new Rectangle(240, 390, 30, 30)));
    }
    
    @Test public void testNotCollisionWithLevel() {
        assertTrue(world.collidesWithLevel( new Rectangle(0, 0, 30, 30)));
    }
    
    @Test public void testGenerationPoint() {
        assertEquals(177, world.generationPoint());
    }
    
    @Test public void testGetWidth() {
        assertEquals(16, world.getWidth());
    }
    
    @Test public void testGetHeight() {
        assertEquals(24, world.getHeight());
    }
    
    @Test public void testIsNotVictory() {
        assertEquals(false, world.isVictory());
    }
    
    @Test public void testIsVictory() {
        for(int x = 0; x < 15; x++){
            for(int y = 0; y < 24; y++){
                world.getPlayer().setPosition(new Rectangle(x * 30, y * 30, 30, 30));
                world.eatPoint();
            }
        }
        assertTrue(world.isVictory());
    }


    @Test public void testIsPlayerAttackDeadSpirit() {
        Rectangle rectangle = new Rectangle(30, 30, 30, 30);
        world.getPlayer().setPosition(rectangle);
        world.getPlayer().setState(State.ATTACK);
        world.getSpirits().get(0).setPosition(rectangle);
        assertEquals(true, world.deadSpirit());
    }

    @Test public void testIsPlayerDefenceDeadSpirit() {
        Rectangle rectangle = new Rectangle(30, 30, 30, 30);
        world.getPlayer().setPosition(rectangle);
        world.getPlayer().setState(State.DEFENCE);
        world.getSpirits().get(0).setPosition(rectangle);
        assertEquals(false, world.deadSpirit());
    }
    

    @Test public void testIsPlayerDefenceDeadPlayer() {
        Rectangle rectangle = new Rectangle(30, 30, 30, 30);
        world.getPlayer().setPosition(rectangle);
        world.getPlayer().setState(State.DEFENCE);
        world.getSpirits().get(0).setPosition(rectangle);
        assertEquals(true, world.deadPlayer());
        assertEquals(State.DEAD, world.getPlayer().getState());
    }
    
    @Test public void testIsPlayerDefenceNotDeadPlayer() {
        assertFalse(world.deadPlayer());
    }
    
    
    private void assertPlayer(int expectedX, int expectedY) {
        assertEquals(expectedX, world.getPlayer().getPosition().getX());
        assertEquals(expectedY, world.getPlayer().getPosition().getY());
    }
    
    private boolean assertEqualsList(List<Brick> list1, List<Brick> list2) {
        if (list1.size() == list2.size()) {
            for (int index = 0; index < list1.size(); index++) {
                if (list1.get(index).getTexture() != list2.get(index)
                        .getTexture()) {
                    if (list1.get(index).getTexture() == Texture.background
                            && list2.get(index).getTexture() == Texture.point) {
                    } else {
                        return false;
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }

}
