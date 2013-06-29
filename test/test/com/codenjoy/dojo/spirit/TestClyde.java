package com.codenjoy.dojo.spirit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.codenjoy.dojo.packman.model.Direction;
import com.codenjoy.dojo.packman.model.LevelReader;
import com.codenjoy.dojo.packman.model.Rectangle;
import com.codenjoy.dojo.packman.model.State;
import com.codenjoy.dojo.packman.model.World;
import com.codenjoy.dojo.packman.model.spirit.Clyde;
import com.codenjoy.dojo.packman.view.Texture;



public class TestClyde {
    private Clyde clyde;
    private World world;
    
    @Before
    public void init(){
        clyde = new Clyde();
        world  = new World(new LevelReader( "res/lvl_1.txt"));
        world.startPointPlayer();
    }
    
    @Test
    public void testIsView() {
        assertNotNull(clyde);
    }
    
    @Test
    public void testClydeAttack(){
        clyde.setState(State.ATTACK);
        clyde.go(world);
        
        assertEquals(14, clyde.maps.getMap()[7][11]);
        assertEquals(1, clyde.maps.getMap()[8][13]);
        
        world.getPlayer().setPosition(new Rectangle(150, 300, 30, 30));
        clyde.go(world);
        assertEquals(1, clyde.maps.getMap()[1][2]);
        
    }
    
    @Test
    public void testno(){
   

    }

    
    @Test
    public void testOnMove(){
       clyde.setDirection(Direction.LEFT);
       clyde.onMove(world);
       assertSpirit(205,330);

       clyde.setDirection(Direction.UP);
       clyde.onMove(world);
       assertSpirit(205,330);
       
       clyde.setDirection(Direction.DOWN);
       clyde.onMove(world);
       assertSpirit(205,330);
       
       clyde.setDirection(Direction.RIGHT);
       clyde.onMove(world);
       assertSpirit(210,330);
    }
    
    @Test
    public void testClydeDefence(){
       clyde.setState(State.DEFENCE);
       clyde.go(world);
       assertEquals(1, clyde.maps.getMap()[2][22]);
    }
    
    @Test
    public void testClydeDead(){
       clyde.setState(State.DEAD);
       clyde.go(world);
       assertEquals(1, clyde.maps.getMap()[7][11]);
    }
    
    @Test
    public void testClydeTexture(){
        assertEquals(Texture.clydeLeft,clyde.left());
        assertEquals(Texture.clydeRight,clyde.right());
        assertEquals(Texture.clydeDown,clyde.down());
        assertEquals(Texture.clydeUp,clyde.up());
    }
    
    private void assertSpirit(int expectedX, int expectedY) {
        assertEquals(expectedX, clyde.getPosition().getX());
        assertEquals(expectedY, clyde.getPosition().getY());
    }
}
