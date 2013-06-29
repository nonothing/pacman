package com.codenjoy.dojo.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.codenjoy.dojo.packman.model.Menu;

public class TestMenu {

    private Menu menu;
    
    @Before public void initialization(){
        menu = new Menu();
    }
    
    @Test
    public void testIsMenu() {
       assertNotNull(menu);
    }
    
    @Test
    public void testMenuDown(){
        int position = menu.getPosition();
        
        menu.onDown();
        int result = menu.getPosition();
        
        assertEquals(position + 1, result);
    }
    
    @Test
    public void testMenuDownMaxPosition(){
        menu.onDown();
        menu.onDown();
        menu.onDown();
        
        assertEquals(0, menu.getPosition());
    }
    
    @Test
    public void testMenuUp(){
        int position = menu.getPosition();
        
        menu.onUp();
        int result = menu.getPosition();
        
        assertEquals(position + 2, result);
    }
    
    @Test
    public void testMenuIsSound(){
        assertTrue(menu.isSound());
        assertFalse(menu.isSound());
    }
    
    @Test
    public void testMenuGetSound(){
        assertFalse(menu.getSound());
    }

}
