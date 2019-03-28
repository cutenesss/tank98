package game;

import javax.swing.*;
import java.awt.*;

/**
 * Created by huynq on 7/4/17.
 */

public class Program {
    public static void main(String[] args) {
        GameWindow window = new GameWindow();

        GamePanel panel = new GamePanel();
        panel.setBackground(Color.BLACK);
        panel.setPreferredSize(new Dimension(Setting.GAME_WIDTH, Setting.GAME_HEIGHT));
        window.add(panel);
        window.setVisible(true);
        window.setTitle("Battle city");
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        panel.gameLoop();

    }
}