//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.game.timer;

import java.util.Timer;
import java.util.TimerTask;

public class Game_Timer {
    private static final int DEFAULT_DURATION = 1000;
    private int interval = 1000;
    Timer timer;

    public Game_Timer() { }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public void start() {
        this.timer = new Timer();
        this.timer.schedule(new Game_Timer.Task(), 0L, (long)this.interval);
    }

    public void stop() {
        this.timer.cancel();
    }

    static class Task extends TimerTask {
        Task() { }

        public void run() { }
    }
}
