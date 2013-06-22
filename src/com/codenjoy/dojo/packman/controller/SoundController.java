package com.codenjoy.dojo.packman.controller;

import com.codenjoy.dojo.packman.model.World;
import com.codenjoy.dojo.packman.view.Sound;
import com.codenjoy.dojo.packman.view.Wave;

public class SoundController {


    private Sound sound;
    private Sound backgroundSound;
    private boolean isSound;
   
    public SoundController() {
        sound = new Sound();
        backgroundSound = new Sound();
    }
    
    public void playBackground(){
        backgroundSound.play(Wave.sirenSound, true, isSound);
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
        }
        if (sound != null) {
            sound.stop();
        }
    }
    
    public void playMenu(){
        sound.play(Wave.menuSound, true, isSound);
    }
    
    public void setSound(boolean isSound){
        this.isSound = isSound;
    }
}
