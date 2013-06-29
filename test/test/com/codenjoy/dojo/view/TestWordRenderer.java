package com.codenjoy.dojo.view;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.codenjoy.dojo.packman.model.LevelReader;
import com.codenjoy.dojo.packman.model.World;
import com.codenjoy.dojo.packman.view.WorldRenderer;

public class TestWordRenderer {
    private WorldRenderer view;
    private World world;
    
    @Before
    public void init(){
        view = new WorldRenderer();
        world = new World(new LevelReader( "res/lvl_1.txt"));
        view.init(world);
    }
    
    @Test
    public void testIsView() {
        assertNotNull(view);
    }
     
    @Test
    public void testIsFrame(){
        assertNotNull(view.getFrame());
    }
    
    
}
