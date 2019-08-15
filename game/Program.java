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
        window.setTitle("Tank");
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit toolkit=Toolkit.getDefaultToolkit();
        Dimension screenSize=toolkit.getScreenSize();
        window.pack();
        window.setLocation(screenSize.width/2-window.getSize().width/2,
                screenSize.height/2-window.getSize().height/2);
        panel.gameLoop();
    }
}