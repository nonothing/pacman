package com.codenjoy.dojo.view;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.codenjoy.dojo.packman.view.Sound;
import com.codenjoy.dojo.packman.view.Wave;

public class TestSound {

    private Sound sound;
    
    @Before
    public void init(){
        sound = new Sound();
    }
    
    @Test
    public void testIsSoundNotNull() {
        assertNotNull(sound);
    }
    
    @Test
    public void testSoundPlayNotLoop() {
        sound.play(Wave.beginning, false, true);
    }
    
    @Test
    public void testSoundPlayLoop() {
        sound.play(Wave.beginning, true, true);
    }

    @Test
    public void testNotSoundPlayNotLoop() {
        sound.play(Wave.beginning, false, false);
    }
    
    @Test
    public void testNotSoundPlayLoop() {
        sound.play(Wave.beginning, true, false);
    }
    
    @Test
    public void testSoundStopLoop() {
        sound.play(Wave.beginning, true, true);
        sound.stop();
    }
    
    @Test
    public void testSoundStop() {
        sound.play(Wave.beginning, false, true);
        sound.stop();
    }
}
