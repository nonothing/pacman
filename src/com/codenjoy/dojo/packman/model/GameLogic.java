package com.codenjoy.dojo.packman.model;

import java.util.Timer;
import java.util.TimerTask;

public class GameLogic {

    private int leftSpirit;
    private World world;
    private int second;
    private boolean isPause;
    private boolean leftDefenceSpirit;
    
    public void setPause(boolean isPause){
        this.isPause = isPause;
    }

    public boolean getPause(){
        return isPause;
    }
    public GameLogic(World world) {
        this.world = world;
        leftSpirit = 4;
        second = 0;
        startTimer();
    }

    public void startTimer() {
        timer.schedule(speedAtack, 1, 60);
        timer.schedule(spiritInGame, 1, 3000);
        timer.schedule(taskBonus, 1, 1000);
        timer.schedule(speedDefence, 1, 80);
        timer.schedule(speedDead, 1, 30);
    }

    Timer timer = new Timer();

    TimerTask speedAtack = new TimerTask() {
        public void run() {
            if (!isPause) {
                
                for (int i = 0; i < world.getSpirits().size() - leftSpirit; i++) {
                    if (world.getSpirits().get(i).getState() == State.ATTACK)
                        world.getSpirits().get(i).go(world);
                }
                if (world.getPlayer().getState() == State.ATTACK){
                    leftTime = true;
                }
            }
        }
    };

    TimerTask speedDefence = new TimerTask() {
        public void run() {
            if (!isPause) {
                for (int i = 0; i < world.getSpirits().size() - leftSpirit; i++) {
                    if (world.getSpirits().get(i).getState() == State.DEFENCE){
                        world.getSpirits().get(i).go(world);
                        world.getSpirits().get(i).setDefence(leftDefenceSpirit);
                    }
                }
            }
        }
    };

    TimerTask speedDead = new TimerTask() {
        public void run() {
            if (!isPause) {
                for (int i = 0; i < world.getSpirits().size() - leftSpirit; i++) {
                    if (world.getSpirits().get(i).getState() == State.DEAD)
                        world.getSpirits().get(i).go(world);
                }
            }
        }
    };

    boolean leftTime = false;

    TimerTask spiritInGame = new TimerTask() {
        public void run() {
            if (!isPause) {
                if (leftSpirit > 0) {
                    leftSpirit--;
                }
            }
        }
    };
    
    TimerTask taskBonus = new TimerTask() {
        public void run() {
            if (!isPause) {
                if (leftTime) {
                    second++;
                    if(second >=8 && second % 2 == 0){
                        leftDefenceSpirit=true;
                    }else{
                        leftDefenceSpirit=false;
                    }
                    
                    if (second == 12) {
                        world.attackNPC();
                        leftDefenceSpirit=false;
                        second = 0;
                        leftTime = false;
                    }
                }
            }
        }
    };
    

    public void startSpirit() {
        leftSpirit = 4;
    }

}
