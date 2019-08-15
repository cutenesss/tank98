package game;

import java.awt.*;

public class Background extends GameObject {

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Setting.BACKGROUND_WIDTH, Setting.BACKGROUND_HEIGHT);
    }
}
