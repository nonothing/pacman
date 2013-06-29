package com.codenjoy.dojo.spirit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.codenjoy.dojo.packman.model.Direction;
import com.codenjoy.dojo.packman.model.LevelReader;
import com.codenjoy.dojo.packman.model.State;
import com.codenjoy.dojo.packman.model.World;
import com.codenjoy.dojo.packman.model.spirit.Inky;
import com.codenjoy.dojo.packman.view.Texture;



public class TestInky {
    private Inky inky;
    private World world;
    
    @Before
    public void init(){
        inky = new Inky();
        world  = new World(new LevelReader( "res/lvl_1.txt"));
        world.startPointPlayer();
    }
    
    @Test
    public void testIsView() {
        assertNotNull(inky);
    }
    
    @Test
    public void testInkyAttack(){
       inky.setState(State.ATTACK);
       inky.go(world);
       assertEquals(13, inky.maps.getMap()[6][11]);
       assertEquals(1, inky.maps.getMap()[4][15]);
    }
    
    @Test
    public void testOnMove(){
       inky.setDirection(Direction.LEFT);
       inky.onMove(world);
       assertSpirit(180,330);

       inky.setDirection(Direction.UP);
       inky.onMove(world);
       assertSpirit(180,330);
       
       inky.setDirection(Direction.DOWN);
       inky.onMove(world);
       assertSpirit(180,330);
       
       inky.setDirection(Direction.RIGHT);
       inky.onMove(world);
       assertSpirit(185,330);
    }
    
    @Test
    public void testInkyDefence(){
       inky.setState(State.DEFENCE);
       inky.go(world);
       assertEquals(1, inky.maps.getMap()[13][22]);
    }
    
    @Test
    public void testInkyDead(){
       inky.setState(State.DEAD);
       inky.go(world);
       assertEquals(1, inky.maps.getMap()[6][11]);
    }
    
    @Test
    public void testInkyTexture(){
        assertEquals(Texture.inkyLeft,inky.left());
        assertEquals(Texture.inkyRight,inky.right());
        assertEquals(Texture.inkyDown,inky.down());
        assertEquals(Texture.inkyUp,inky.up());
    }
    
    private void assertSpirit(int expectedX, int expectedY) {
        assertEquals(expectedX, inky.getPosition().getX());
        assertEquals(expectedY, inky.getPosition().getY());
    }
}
