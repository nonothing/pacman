package com.codenjoy.dojo.packman.view;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import com.codenjoy.dojo.packman.model.Brick;
import com.codenjoy.dojo.packman.model.World;
import com.codenjoy.dojo.packman.model.WorldObject;
import com.codenjoy.dojo.packman.model.spirit.Spirit;
import com.codenjoy.dojo.packman.view.Images;



@SuppressWarnings("serial")
public class WorldRenderer extends JPanel {

   
    private static final int HEIGHT = 760;
    private static final int WIDTH = 500;
    
    private World world;
    private Images images;
    private Graphics canvas;
    private JFrame frame;
    private Fonts font;
    
    public WorldRenderer() {
        images = new Images();
        font = new Fonts();
    }

    public void init(World world){
        this.world = world;
        initFrame();
    }
    
    private void initFrame() {
        frame = new JFrame("Pacman");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.add(this);
        frame.setVisible(true);
    }
    
    public void setVisible(boolean visible){
        frame.setVisible(visible);
    }
    
    public JFrame getFrame(){
        return frame;
    }
    
    private void draw(WorldObject object) {
        object.animate();

        canvas.drawImage(images.get(object),
                 object.getPosition().getX(),
                 object.getPosition().getY(), null);
    }

    public void paint(Graphics canvas) {
        this.canvas = canvas;

        for (Brick brick : world.getBricks()) {
            draw(brick);
        }

        for (Spirit spirit : world.getSpirits()) {
            draw(spirit);
        }

        draw(world.getPlayer());
        
        drawVictory();
        drawGameOver();
    }

    private void drawVictory() {
        if (world.isVictory()) {
            canvas.setColor(Color.yellow);
            canvas.setFont(font.getFont(FontName.emulogic, 52f));
            canvas.drawString("You WIN!", WIDTH/10, HEIGHT/3);
            canvas.setFont(font.getFont(FontName.emulogic, 25f));
            canvas.drawString("play again? y/n", WIDTH/10, HEIGHT/2);
        }
    }
    
    private void drawGameOver() {
        if (world.isGameOver()) {
            canvas.setColor(Color.yellow);
            canvas.setFont(font.getFont(FontName.emulogic, 45f));
            canvas.drawString("game over!", WIDTH/15, HEIGHT/3);
            canvas.setFont(font.getFont(FontName.emulogic, 25f));
            canvas.drawString("play again? y/n", WIDTH/10, HEIGHT/2);
        }
    }

	
}
