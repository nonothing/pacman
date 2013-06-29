package com.codenjoy.dojo.view;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.codenjoy.dojo.packman.model.Menu;
import com.codenjoy.dojo.packman.view.MenuRenderer;


public class TestMenuRenderer {
    private MenuRenderer view;
    private Menu menu;
    @Before
    public void init(){
        view = new MenuRenderer();
        menu = new Menu();
        view.init(menu);
    }
    
    @Test
    public void testIsView() {
        assertNotNull(view);
    }
     
    @Test
    public void testIsFrame(){
        assertNotNull(view.getFrame());
    }
    
    @Test
    public void testDrawMenuOffSound(){
        menu.isSound();
    }
    
    
}
