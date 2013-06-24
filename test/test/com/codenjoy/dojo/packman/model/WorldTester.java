package com.codenjoy.dojo.packman.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class WorldTester {
    World world;
    
    @Before public void init(){
        world  = new World(new LevelReader( "res/lvl_1.txt"));// допустим левелридер работает правильно
    }
    
    @Test public void testIsGameOver() {
        assertEquals(false, world.isGameOver()); // ???
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
        assertEquals(world.oldBricks, world.bricks); // почему-то разные = (
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
    
    @Test public void testIsVictory() {
        assertEquals(false, world.isVictory());
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
    
    
    private void assertPlayer(int expectedX, int expectedY) {
        assertEquals(expectedX, world.getPlayer().getPosition().getX());
        assertEquals(expectedY, world.getPlayer().getPosition().getY());
    }
          

}
