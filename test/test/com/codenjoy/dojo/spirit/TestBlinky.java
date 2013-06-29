package com.codenjoy.dojo.spirit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.codenjoy.dojo.packman.model.Direction;
import com.codenjoy.dojo.packman.model.LevelReader;
import com.codenjoy.dojo.packman.model.State;
import com.codenjoy.dojo.packman.model.World;
import com.codenjoy.dojo.packman.model.spirit.Blinky;
import com.codenjoy.dojo.packman.view.Texture;



public class TestBlinky {
    private Blinky blinky;
    private World world;
    
    @Before
    public void init(){
        blinky = new Blinky();
        world  = new World(new LevelReader( "res/lvl_1.txt"));
        world.startPointPlayer();
    }
    
    @Test
    public void testIsView() {
        assertNotNull(blinky);
    }
    
    @Test
    public void testBlinkyAttack(){
       blinky.setState(State.ATTACK);
        blinky.go(world);
       assertEquals(13, blinky.maps.getMap()[8][11]);
       assertEquals(1, blinky.maps.getMap()[8][13]);
    }
    
    @Test
    public void testOnMove(){
       blinky.setDirection(Direction.LEFT);
       blinky.onMove(world);
       assertSpirit(235,330);

       blinky.setDirection(Direction.UP);
       blinky.onMove(world);
       assertSpirit(235,325);
       
       blinky.setDirection(Direction.DOWN);
       blinky.onMove(world);
       assertSpirit(235,330);
       
       blinky.setDirection(Direction.RIGHT);
       blinky.onMove(world);
       assertSpirit(240,330);
    }
    
    @Test
    public void testBlinkyDefence(){
       blinky.setState(State.DEFENCE);
       blinky.go(world);
       assertEquals(1, blinky.maps.getMap()[13][1]);
    }
    
    @Test
    public void testBlinkyDead(){
       blinky.setState(State.DEAD);
       blinky.go(world);
       assertEquals(1, blinky.maps.getMap()[8][11]);
    }
    
    @Test
    public void testBlinkyTexture(){
        assertEquals(Texture.blinkyLeft,blinky.left());
        assertEquals(Texture.blinkyRight,blinky.right());
        assertEquals(Texture.blinkyDown,blinky.down());
        assertEquals(Texture.blinkyUp,blinky.up());
    }
    
    private void assertSpirit(int expectedX, int expectedY) {
        assertEquals(expectedX, blinky.getPosition().getX());
        assertEquals(expectedY, blinky.getPosition().getY());
    }
}
