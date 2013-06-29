package com.codenjoy.dojo.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.codenjoy.dojo.packman.model.Direction;
import com.codenjoy.dojo.packman.model.LevelReader;
import com.codenjoy.dojo.packman.model.Map;
import com.codenjoy.dojo.packman.model.World;
import com.codenjoy.dojo.packman.model.spirit.Blinky;

public class TestMap {

   private Map map;
   private World world;
   private Blinky blinky;
    
    @Before public void initialization(){
        map  = new Map(16, 24);
        world = new World(new LevelReader( "res/lvl_1.txt"));
        blinky = new Blinky();
    }
    
    @Test
    public void testIsNotNullMap() {
        assertNotNull(map);
    }
    
    @Test
    public void testGetMap(){
        assertNotNull(map.getMap());
    }
    
    @Test
    public void testPotencialMap(){ 
        map.potencialMap(world.getPlayer().getPosition(), blinky, world.getBricks());
        assertEquals(20, map.getMap()[1][1]);
        assertEquals(1, map.getMap()[8][13]);

    }
    
    @Test
    public void testInverseMap(){
        map.potencialMap(world.getPlayer().getPosition(), blinky, world.getBricks());
        assertEquals(200, map.getMap()[0][0]);
    }
    
    @Test
    public void testChangeMapIsLeft() {

        blinky.setDirection(Direction.LEFT);
        map.potencialMap(world.getPlayer().getPosition(), blinky,
                world.getBricks());
        assertEquals(
                200,
                map.getMap()[blinky.getPosition().getX() / blinky.getSize() + 1][blinky
                        .getPosition().getY() / blinky.getSize()]);

    }
    
    @Test
    public void testChangeMapIsRight() {

        blinky.setDirection(Direction.RIGHT);
        map.potencialMap(world.getPlayer().getPosition(), blinky,
                world.getBricks());
        assertEquals(
                200,
                map.getMap()[blinky.getPosition().getX() / blinky.getSize() - 1][blinky
                        .getPosition().getY() / blinky.getSize()]);

    }
    
    
    @Test
    public void testChangeMapIsUp() {

        blinky.setDirection(Direction.UP);
        map.potencialMap(world.getPlayer().getPosition(), blinky,
                world.getBricks());
        assertEquals(
                200,
                map.getMap()[blinky.getPosition().getX() / blinky.getSize()][blinky
                        .getPosition().getY() / blinky.getSize() + 1]);

    }
    
    @Test
    public void testChangeMapIsDown() {

        blinky.setDirection(Direction.DOWN);
        map.potencialMap(world.getPlayer().getPosition(), blinky,
                world.getBricks());
        assertEquals(
                200,
                map.getMap()[blinky.getPosition().getX() / blinky.getSize()][blinky
                        .getPosition().getY() / blinky.getSize() - 1]);

    }

}
