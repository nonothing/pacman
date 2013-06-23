package com.codenjoy.dojo.packman.controller;

import com.codenjoy.dojo.packman.model.World;
import com.codenjoy.dojo.packman.view.Sound;
import com.codenjoy.dojo.packman.view.Wave;

public class SoundController {


    private Sound sound;
    private Sound backgroundSound;
    private Sound menu;
    private boolean isSound;
   
    public SoundController() {
        sound = new Sound();
    }
    
    public void playBackground(){
        if(backgroundSound == null){
            backgroundSound = new Sound();
            backgroundSound.play(Wave.sirenSound, true, true);
        }
    }
        
    private void eatPoint(World world){
        if(world.eatPoint()){
            sound.play(Wave.chomp, false, isSound); 
        }
    }
    
    private void eatBonus(World world) {
        if (world.eatBonus()) {
            sound.play(Wave.eatFruit, false, isSound);
        }
    }
    
    private void collisionNPC(World world) {
        if (world.deadPlayer()) {
            sound.play(Wave.death, false, isSound);
        }
        if (world.deadSpirit()) {
            sound.play(Wave.eatSpirit, false, isSound);
        }
    }

    public void play(World world){
        eatPoint(world);
        eatBonus(world);
        collisionNPC(world);
    }
    
    public void stop() {
        if (backgroundSound != null) {
            backgroundSound.stop();
            backgroundSound = null;
        }
        if (sound != null) {
            sound.stop();
        }
        if (menu != null){
            menu.stop();
            menu = null;
            }
    }
    
    public void playMenu(){
        if(menu == null){
        menu = new Sound();
        menu.play(Wave.menuSound, true, isSound);
        }
    }
    
    public void setSound(boolean isSound){
        this.isSound = isSound;
    }
    
    public boolean getSound(){
        return isSound;
    }
}
