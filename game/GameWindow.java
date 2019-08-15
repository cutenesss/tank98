package game;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameWindow extends JFrame {
    public static boolean isUpPress;
    public static boolean isDownPress;
    public static boolean isLeftPress;
    public static boolean isRightPress;
    public static boolean isFirePress;
    public static boolean isSpecialPress;
    public static boolean isAnyPress;

    public GameWindow() {
        //bat su kien bam phim
        KeyAdapter keyHandler = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                isAnyPress=true;
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    isUpPress = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    isDownPress = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_A) {
                    isLeftPress = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_D) {
                    isRightPress = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    isFirePress = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_P){
                    isSpecialPress = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                isAnyPress=false;
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    isUpPress = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    isDownPress = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_A) {
                    isLeftPress = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_D) {
                    isRightPress = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    isFirePress = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_P){
                    isSpecialPress = false;
                }
            }
        };
        addKeyListener(keyHandler);
    }
}
