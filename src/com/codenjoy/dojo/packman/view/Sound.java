package com.codenjoy.dojo.packman.view;

import java.util.HashMap;
import com.codenjoy.dojo.packman.model.PlayWave;


public class Sound {
    private PlayWave playWave;
    private HashMap<Wave,String> sounds = new HashMap<Wave,String>();
    
    public Sound(){
        sounds.put(Wave.eatFruit, "sound/eatFruit.wav");
        sounds.put(Wave.menuSound, "sound/pacman_song2.wav");
        sounds.put(Wave.chomp, "sound/pacman_coinin.wav");
        sounds.put(Wave.beginning, "sound/beginning.wav");
        sounds.put(Wave.death, "sound/death.wav");
        sounds.put(Wave.eatSpirit, "sound/eatSpirit.wav");
        sounds.put(Wave.sirenSound, "sound/sirenSound.wav");
    }
     
    public void play(Wave wave, boolean bool) {
        playWave = new PlayWave(sounds.get(wave));
        playWave.setLoop(bool);
        playWave.start();
    }

    @SuppressWarnings("deprecation")
    public void stop(){
        playWave.setLoop(false);
        playWave.stop();
    }


}
