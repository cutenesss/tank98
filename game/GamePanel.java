package game;

import game.tank.Enemy;
import game.tank.Player;
import game.tank.Tank;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel {
    Tank player;
    Background background;
    Tank enemy;

    public GamePanel() {
        background = new Background();
        player = new Player();
        enemy = new Enemy();
    }

    public void gameLoop() {
        long lastLoop = 0;
        long delay = 1000 / 60;
        while (true) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastLoop > delay) {
                runAll();        //logic game
                renderAll();     //render game
                lastLoop = currentTime;
            }
        }
    }

    private void renderAll() {
        repaint();  // goi lai ham paint()
    }

    @Override
    public void paint(Graphics g) {
        for (int i = 0; i < GameObject.objects.size(); i++) {
            GameObject object = GameObject.objects.get(i);
            if(object.active){
                object.render(g);
            }
        }
    }

    private void runAll() {
        for (int i = 0; i < GameObject.objects.size(); i++) {
            GameObject object = GameObject.objects.get(i);
            if(object.active){
                object.run();
            }
        }
        summonEnemies();
    }

    int summonCount;
    int waveCount;
    int enemyCount;
    Random random = new Random();
    int enemyX = 100 + random.nextInt(200);

    private void summonEnemies() {

    }
}