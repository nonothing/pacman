package com.codenjoy.dojo.model;

import static org.junit.Assert.*;

import javax.sound.sampled.UnsupportedAudioFileException;

import org.junit.Before;
import org.junit.Test;

import com.codenjoy.dojo.packman.model.PlayWave;

public class TestPlayWave {

    private PlayWave playWave;
    
    @Before
        public void initialization(){
        playWave = new PlayWave("sound/eatFruit.wav");
    }
    @Test
    public void testIsPlayWave() {
        assertNotNull(playWave);
    }
    
    @Test
    public void testPlayIsNotLoop() {
        playWave = new PlayWave("sound/eatFruit.wav");
        playWave.setLoop(false);
        playWave.start();
    }
    
    @Test
    public void testPlayIsLoop() {
        playWave = new PlayWave("sound/eatFruit.wav");
        playWave.setLoop(true);
        playWave.start();
    }
    
    @SuppressWarnings("deprecation")
    @Test
    public void testPlayAndStopLoop() {
        playWave = new PlayWave("sound/eatFruit.wav");
        playWave.setLoop(true);
        playWave.start();
        
        playWave.setLoop(false);
        playWave.stop(); 
    }
    
    @Test
    public void testRun() {
        playWave = new PlayWave("sound/eatFruit.wav");
        playWave.run();
    }
    
    @Test
    public void testNotFoundFile() throws UnsupportedAudioFileException {
        playWave = new PlayWave("");
        playWave.run();
    }
    
}
