package com.codenjoy.dojo.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.codenjoy.dojo.packman.model.Direction;
import com.codenjoy.dojo.packman.model.Player;
import com.codenjoy.dojo.packman.model.Point;
import com.codenjoy.dojo.packman.model.Rectangle;
import com.codenjoy.dojo.packman.view.Texture;

public class TestPlayer {
    
    private Player player;
    
    @Before public void initialization(){
        player = new Player(new Point(8, 13), Texture.pacmanUpOpen);
    }
    
    
    @Test
    public void testIsPlayer() {
        assertNotNull(player);
    }
    
    @Test
    public void testIsAnimatePlayerIsOpen() {
      player.setPosition(new Rectangle(30,30,30,30));
      
      player.setDirection(Direction.LEFT);
      player.animate();
      assertEquals(Texture.pacmanLeftOpen, player.getTexture());
      
      player.setDirection(Direction.RIGHT);
      player.animate();
      assertEquals(Texture.pacmanRightOpen, player.getTexture());
      
      player.setDirection(Direction.UP);
      player.animate();
      assertEquals(Texture.pacmanUpOpen, player.getTexture());
      
      player.setDirection(Direction.DOWN);
      player.animate();
      assertEquals(Texture.pacmanDownOpen, player.getTexture());
    }
    
    @Test
    public void testIsAnimatePlayerIsClose() {
      player.setPosition(new Rectangle(25,25,30,30));
      
      player.setDirection(Direction.LEFT);
      player.animate();
      assertEquals(Texture.pacmanLeftClose, player.getTexture());
      
      player.setDirection(Direction.RIGHT);
      player.animate();
      assertEquals(Texture.pacmanRightClose, player.getTexture());
      
      player.setDirection(Direction.UP);
      player.animate();
      assertEquals(Texture.pacmanUpClose, player.getTexture());
      
      player.setDirection(Direction.DOWN);
      player.animate();
      assertEquals(Texture.pacmanDownClose, player.getTexture());
    }

    
    @Test
    public void testGetSpeed(){
        assertEquals(5, player.getSpeed());
    }

}
