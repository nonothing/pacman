package com.codenjoy.dojo.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.codenjoy.dojo.packman.controller.MenuController;
import com.codenjoy.dojo.packman.controller.SoundController;
import com.codenjoy.dojo.packman.controller.WorldController;
import com.codenjoy.dojo.packman.model.LevelReader;
import com.codenjoy.dojo.packman.model.Menu;
import com.codenjoy.dojo.packman.model.World;
import com.codenjoy.dojo.packman.view.MenuRenderer;
import com.codenjoy.dojo.packman.view.WorldRenderer;

public class TestWorldController {

    private WorldController worldController;
    
    @Before
    public void init() {
        worldController = new WorldController(new World(new LevelReader(
                "res/lvl_1.txt")), new WorldRenderer(), new MenuController(
                new Menu(), new MenuRenderer()), new SoundController());
    }

    @Test
    public void testIsNotNull() {
        assertNotNull(worldController);
    }
    

}
