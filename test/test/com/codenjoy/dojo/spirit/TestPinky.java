package com.codenjoy.dojo.spirit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.codenjoy.dojo.packman.model.Direction;
import com.codenjoy.dojo.packman.model.LevelReader;
import com.codenjoy.dojo.packman.model.State;
import com.codenjoy.dojo.packman.model.World;
import com.codenjoy.dojo.packman.model.spirit.Pinky;
import com.codenjoy.dojo.packman.view.Texture;



public class TestPinky {
    private Pinky pinky;
    private World world;
    
    @Before
    public void init(){
        pinky = new Pinky();
        world  = new World(new LevelReader( "res/lvl_1.txt"));
        world.startPointPlayer();
    }
    
    @Test
    public void testIsView() {
        assertNotNull(pinky);
    }
    
    @Test
    public void testPinkyAttack(){
       pinky.setState(State.ATTACK);
        pinky.go(world);
       assertEquals(10, pinky.maps.getMap()[9][11]);
       assertEquals(1, pinky.maps.getMap()[11][12]);
    }
    
    @Test
    public void testOnMove(){
       pinky.setDirection(Direction.LEFT);
       pinky.onMove(world);
       assertSpirit(265,330);
       
       pinky.setDirection(Direction.UP);
       pinky.onMove(world);
       assertSpirit(265,330);  //colision
       
       pinky.setDirection(Direction.DOWN);
       pinky.onMove(world);
       assertSpirit(265,330); //colision
       
       pinky.setDirection(Direction.RIGHT);
       pinky.onMove(world);
      assertSpirit(270,330);
    }
    
    @Test
    public void testPinkyDefence(){
       pinky.setState(State.DEFENCE);
       pinky.go(world);
       assertEquals(1, pinky.maps.getMap()[1][2]);
       
    }
    
    @Test
    public void testPinkyDead(){
       pinky.setState(State.DEAD);
       pinky.go(world);
       assertEquals(1, pinky.maps.getMap()[9][11]);
    }
    
    @Test
    public void testPinkyTexture(){
        assertEquals(Texture.pinkyLeft,pinky.left());
        assertEquals(Texture.pinkyRight,pinky.right());
        assertEquals(Texture.pinkyDown,pinky.down());
        assertEquals(Texture.pinkyUp,pinky.up());
    }
    
    private void assertSpirit(int expectedX, int expectedY) {
        assertEquals(expectedX, pinky.getPosition().getX());
        assertEquals(expectedY, pinky.getPosition().getY());
    }
}
