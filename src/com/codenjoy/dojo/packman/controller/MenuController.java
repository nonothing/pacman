package com.codenjoy.dojo.packman.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;

import com.codenjoy.dojo.packman.model.LevelReader;
import com.codenjoy.dojo.packman.model.Menu;
import com.codenjoy.dojo.packman.model.World;
import com.codenjoy.dojo.packman.view.MenuRenderer;
import com.codenjoy.dojo.packman.view.Sound;
import com.codenjoy.dojo.packman.view.Wave;
import com.codenjoy.dojo.packman.view.WorldRenderer;



public class MenuController implements KeyListener, ActionListener{
    
    private MenuRenderer view;
    private Menu menu;
    private Timer mainTimer ;
    private Sound sound; 
    private boolean isSound;
    WorldController worldController;
    
    public MenuController(Menu menu, MenuRenderer view){
        this.view = view;
        this.menu = menu;
        this.mainTimer = new Timer(50, this);
        isSound = true;
        menu.setSound(isSound);
        sound = new Sound();
    }
    
    public boolean isSound(){
        return isSound;
    }
    
    public void startGame(){
        if(isSound){
            sound.play(Wave.menuSound, true);
        }
        if(view.getFrame() == null){
        view.init(menu);
        this.view.addKeyListener(this);
        }
        mainTimer.start();  
        this.view.setFocusable(true);
        show();
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        view.repaint();  
    }

    public void hide(){
        view.setVisibleFrame(false);
        view.setFocusable(false);
    }
    
    public void show(){
        view.setVisibleFrame(true);
        view.setFocusable(true);
    }
    
    public void createGame() {
        sound.stop();
        hide();
 
        view.setVisibleFrame(false);
        if(worldController == null){
        worldController = new WorldController(
                          new World(
                          new LevelReader("res/lvl_1.txt")), 
                          new WorldRenderer(),
                          this,isSound);
        }
        worldController.startGame(isSound);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_UP:
            menu.onUp();
            break;
        case KeyEvent.VK_DOWN:
            menu.onDown();
            break;
        case KeyEvent.VK_ENTER:
            switch (menu.getPosition()) {
            case 0:
                createGame();
                break;
            case 1:
                playSound();
                break;
            case 2:
                System.exit(0);
                break;
            }
            break;
        }
        
    }

    private void playSound() {
        if (isSound) {
            isSound = false;
            sound.stop();
        } else {
            isSound = true;
            sound.play(Wave.menuSound, true);
        }
        menu.setSound(isSound);
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
    }

}
