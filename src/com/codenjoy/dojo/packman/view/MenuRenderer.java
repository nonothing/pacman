package com.codenjoy.dojo.packman.view;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.codenjoy.dojo.packman.model.Menu;

@SuppressWarnings("serial")
public class MenuRenderer extends JPanel {

    private static final int HEIGHT = 760;
    private static final int WIDTH = 500;
    private Menu menu;
    private Graphics canvas;
    private JFrame frame;
    private Fonts font;


    public void init(Menu menu) {
        this.menu = menu;
        font = new Fonts();
        initFrame();
    }

    private void initFrame() {
        frame = new JFrame("Pacman");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.add(this);
        frame.setVisible(true);
    }

    public void setVisibleFrame(boolean visible) {
        frame.setVisible(visible);
    }

    public JFrame getFrame() {
        return frame;
    }

    public void paint(Graphics canvas) {
        this.canvas = canvas;
        drawName();
        drawLabel(menu.getPosition());
    }

    private void drawName() {
        canvas.fillRect(0, 0, WIDTH, HEIGHT);
        canvas.setColor(Color.yellow);
        
        canvas.setFont(font.getFont(FontName.pacfont, 52f));
        canvas.drawString("c-", 190, 100);
        canvas.setFont(font.getFont(FontName.emulogic, 62f));
        canvas.drawString("Pa", 55, 110);
        canvas.drawString("Man", 250, 110);
    }

    private void drawLabel(int menu) {
        String sound = "";
        canvas.setColor(Color.orange);
        canvas.setFont(font.getFont(FontName.emulogic, 35f));
        canvas.drawString("Play", 120, 270);
        if (this.menu.getSound()) {
            sound = "on";
        } else {
            sound = "off";
        }
        canvas.drawString("Sound " + sound, 120, 370);
        canvas.drawString("exit", 120, 470);

        switch (menu) {
        case 0:
            canvas.setColor(Color.red);
            canvas.drawString("Play", 120, 270);
            break;
        case 1:
            canvas.setColor(Color.red);
            canvas.drawString("Sound " + sound, 120, 370);
            break;
        case 2:
            canvas.setColor(Color.red);
            canvas.drawString("exit", 120, 470);
            break;

        }
    }

}
