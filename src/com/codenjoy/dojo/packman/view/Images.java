package com.codenjoy.dojo.packman.view;


import java.awt.Image;
import java.util.HashMap;

import javax.swing.ImageIcon;

import com.codenjoy.dojo.packman.model.WorldObject;



public class Images {
    HashMap<Texture,Image> textures = new HashMap<Texture,Image>();
    
     Images(){
        textures.put(Texture.angleLeftDown, new ImageIcon("res/angle_LD.png").getImage());
        textures.put(Texture.angleLeftUp, new ImageIcon("res/angle_LV.png").getImage());
        textures.put(Texture.angleRightDown, new ImageIcon("res/angle_RD.png").getImage());
        textures.put(Texture.angleRightUp, new ImageIcon("res/angle_RV.png").getImage());
        
        textures.put(Texture.point, new ImageIcon("res/point.png").getImage());
        textures.put(Texture.horizontal, new ImageIcon("res/horizontal.png").getImage());
        textures.put(Texture.vertical, new ImageIcon("res/vertical.png").getImage());
        textures.put(Texture.background, new ImageIcon("res/background.png").getImage());
        
        textures.put(Texture.arcDown, new ImageIcon("res/arc_down.png").getImage());
        textures.put(Texture.arcUp, new ImageIcon("res/arc_up.png").getImage());
        textures.put(Texture.arcLeft, new ImageIcon("res/arc_left.png").getImage());
        textures.put(Texture.arcRight, new ImageIcon("res/arc_right.png").getImage());

        textures.put(Texture.arc2Down, new ImageIcon("res/arc2_down.png").getImage());
        textures.put(Texture.arc2Up, new ImageIcon("res/arc2_up.png").getImage());
        textures.put(Texture.arc2Left, new ImageIcon("res/arc2_left.png").getImage());
        textures.put(Texture.arc2Right, new ImageIcon("res/arc2_right.png").getImage());
        
        textures.put(Texture.pacmanLeftOpen, new ImageIcon("res/pman_3_2.png").getImage());
        textures.put(Texture.pacmanRightOpen, new ImageIcon("res/pman_4_2.png").getImage());
        textures.put(Texture.pacmanDownOpen, new ImageIcon("res/pman_2_2.png").getImage());
        textures.put(Texture.pacmanUpOpen, new ImageIcon("res/pman_1_2.png").getImage());
        
        textures.put(Texture.pacmanLeftClose, new ImageIcon("res/pman_3.png").getImage());
        textures.put(Texture.pacmanRightClose, new ImageIcon("res/pman_4.png").getImage());
        textures.put(Texture.pacmanDownClose, new ImageIcon("res/pman_2.png").getImage());
        textures.put(Texture.pacmanUpClose, new ImageIcon("res/pman_1.png").getImage());
        
        textures.put(Texture.point, new ImageIcon("res/point.png").getImage());
        textures.put(Texture.bonus, new ImageIcon("res/bonus.png").getImage());
        textures.put(Texture.spiritDefence, new ImageIcon("res/spiritDefence.png").getImage());
        textures.put(Texture.spiritDefenceWhite, new ImageIcon("res/spiritDefence_2.png").getImage());
        textures.put(Texture.none, new ImageIcon("res/background.png").getImage());
        
        textures.put(Texture.blinkyDown, new ImageIcon("res/blinky_2.png").getImage());
        textures.put(Texture.blinkyLeft, new ImageIcon("res/blinky_3.png").getImage());
        textures.put(Texture.blinkyRight, new ImageIcon("res/blinky_4.png").getImage());
        textures.put(Texture.blinkyUp, new ImageIcon("res/blinky_1.png").getImage());
        
        textures.put(Texture.pinkyDown, new ImageIcon("res/pinky_2.png").getImage());
        textures.put(Texture.pinkyLeft, new ImageIcon("res/pinky_3.png").getImage());
        textures.put(Texture.pinkyRight, new ImageIcon("res/pinky_4.png").getImage());
        textures.put(Texture.pinkyUp, new ImageIcon("res/pinky_1.png").getImage());
        
        textures.put(Texture.clydeDown, new ImageIcon("res/clyde_2.png").getImage());
        textures.put(Texture.clydeLeft, new ImageIcon("res/clyde_3.png").getImage());
        textures.put(Texture.clydeRight, new ImageIcon("res/clyde_4.png").getImage());
        textures.put(Texture.clydeUp, new ImageIcon("res/clyde_1.png").getImage());
        
        textures.put(Texture.inkyDown, new ImageIcon("res/inky_2.png").getImage());
        textures.put(Texture.inkyLeft, new ImageIcon("res/inky_3.png").getImage());
        textures.put(Texture.inkyRight, new ImageIcon("res/inky_4.png").getImage());
        textures.put(Texture.inkyUp, new ImageIcon("res/inky_1.png").getImage());
        
        textures.put(Texture.orbDown, new ImageIcon("res/orb_2.png").getImage());
        textures.put(Texture.orbLeft, new ImageIcon("res/orb_3.png").getImage());
        textures.put(Texture.orbRight, new ImageIcon("res/orb_4.png").getImage());
        textures.put(Texture.orbUp, new ImageIcon("res/orb_1.png").getImage());
        
    }
    
    public Image get(WorldObject object) {
        return textures.get(object.getTexture());
    }
}
