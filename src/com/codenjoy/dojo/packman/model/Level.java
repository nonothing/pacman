package com.codenjoy.dojo.packman.model;

import java.util.List;


public interface Level {
    int getWidth();
    int getHeight();
    
    List<Brick> getBricks();

    Player getPlayer();
}
