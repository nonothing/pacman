package com.codenjoy.dojo.packman.controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;

import com.codenjoy.dojo.packman.model.Direction;
import com.codenjoy.dojo.packman.model.GameLogic;
import com.codenjoy.dojo.packman.model.State;
import com.codenjoy.dojo.packman.model.World;

import com.codenjoy.dojo.packman.view.WorldRenderer;



public class WorldController implements KeyListener, ActionListener {

    private Direction direction;
    private WorldRenderer view;
    private World world;
    private GameLogic logic;
    private Timer mainTimer;
    private MenuController menuController;
    private SoundController soundController;

    public WorldController(World world, WorldRenderer view, MenuController menuController, SoundController soundController) {
        direction = world.getPlayer().getDirection();
        this.view = view;
        logic = new GameLogic(world);
        this.menuController = menuController;
        this.world = world;
        this.mainTimer = new Timer(50, this);
        this.soundController = soundController; 
     }
     
    public void startGame() {
        if (view.getFrame() == null) {
            view.init(world);
            this.view.addKeyListener(this);
        }
        world.getPlayer().setLife(3);
        mainTimer.start();
        this.view.setFocusable(true);
        this.view.setVisible(true);
        logic.setPause(false);
        soundController.playBackground();
        if (world.isVictory()) {
            world.newGame();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_UP:
            direction = Direction.UP;
            break;
        case KeyEvent.VK_DOWN:
            direction = Direction.DOWN;
            break;
        case KeyEvent.VK_LEFT:
            direction = Direction.LEFT;
            break;
        case KeyEvent.VK_RIGHT:
            direction = Direction.RIGHT;
            break;
        case KeyEvent.VK_Y:
            if (world.isGameOver() || world.isVictory()) {
                world.newGame();
                startGame();
            }
            break;
        case KeyEvent.VK_N:
            if (world.isGameOver() || world.isVictory()) {
                System.exit(0);
            }
            break;

        case KeyEvent.VK_ESCAPE:
            pause();
            hide();
            showMenu();
            break;
        }
    }

    private void pause() {
        soundController.stop();
        logic.setPause(true);
    }

    private void hide(){
        view.setVisible(false);
    }
    
    private void showMenu(){
        menuController.startGame();
    }

    private void newGame() {
        world.createSpirit();
        world.startPointPlayer();
        logic.startSpirit();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if( !logic.getPause()){
        world.tryToPlayerGo(direction);
 
        soundController.play(world);
        }
        if(world.getPlayer().getState() == State.DEAD){
            newGame();
        }
        if(world.isGameOver() || world.isVictory()){
            pause();
        }
        
        view.repaint();
    }
     
    @Override
    public void keyReleased(KeyEvent e) {        
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

}
