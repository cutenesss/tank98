package game.tank;

import game.GameObject;
import game.GameWindow;
import game.Setting;
import game.Vector2D;
import game.renderer.Renderer;

import java.util.ArrayList;

public class Player extends Tank {

    public ArrayList<PlayerBullet> bullets;

    public Player(){
        renderer = new Renderer("assets/Tank/Player/Lv.0");
        bullets = new ArrayList<>();
        position.set(300,300);
    }

    @Override
    public void run() {
        super.run();
        basicMove();
        fire();
        movement();
    }

    int fireCount;

    private void fire() {
        fireCount++;
        if (GameWindow.isFirePress && fireCount > 20) {
            PlayerBullet bullet = GameObject.recycle(PlayerBullet.class);
            bullet.position.set(position.x, position.y + anchor.y * 20);
            bullet.setDirection(this.direction);
            bullets.add(bullet);
            fireCount = 0;
        }
    }

    private void movement(){
        if(GameWindow.isUpPress) {
            this.velocity.set(0, -1);
            this.direction = Setting.UP;
        } else if(GameWindow.isRightPress) {
            this.velocity.set(1, 0);
            this.direction = Setting.RIGHT;
        } else if(GameWindow.isDownPress) {
            this.velocity.set(0, 1);
            this.direction = Setting.DOWN;
        } else if(GameWindow.isLeftPress) {
            this.velocity.set(-1, 0);
            this.direction = Setting.LEFT;
        }
    }

    //player control system
    private void basicMove() {
        int vx = 0;
        int vy = 0;
        if (GameWindow.isUpPress) {
            vy -= Setting.PLAYER_SPEED_1;
        }
        if (GameWindow.isDownPress) {
            vy += Setting.PLAYER_SPEED_1;
        }
        if (GameWindow.isLeftPress) {
            vx -= Setting.PLAYER_SPEED_1;
        }
        if (GameWindow.isRightPress) {
            vx += Setting.PLAYER_SPEED_1;
        }
        velocity.set(vx, vy);
        velocity.setLength(Setting.PLAYER_SPEED_1);
    }

}
