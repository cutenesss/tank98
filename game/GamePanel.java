package game;

import game.menu.SceneManager;
import game.menu.SceneWelcome;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel {

    public GamePanel() {
        SceneManager.signNewSence(new SceneWelcome());
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
            if (object.active) {
                object.render(g);
            }
        }


    }

    private void runAll() {
        for (int i = 0; i < GameObject.objects.size(); i++) {
            GameObject object = GameObject.objects.get(i);
            if (object.active) {
                object.run();
            }
        }

//        summonEnemies();
    }




}
