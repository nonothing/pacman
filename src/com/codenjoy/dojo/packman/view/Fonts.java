package com.codenjoy.dojo.packman.view;


import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;



public class Fonts {
    HashMap<FontName,Font> fonts = new HashMap<FontName,Font>();
    
    private Font font = null;
    private Font createFont(String filename) {
        try {
            File file = new File("font/" + filename + ".ttf");
            font = Font.createFont(Font.TRUETYPE_FONT, file); 
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        return font;
    }
    
    
    Fonts(){
        fonts.put(FontName.emulogic, createFont("emulogic"));
        fonts.put(FontName.pacfont, createFont("pacfont"));
    }
    
    public Font getFont(FontName name ,float size){
        font = fonts.get(name);
        font = font.deriveFont(size);
        return font;
    }

}
    
