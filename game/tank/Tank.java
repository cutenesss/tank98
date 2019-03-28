package game.tank;

import game.GameObject;
import game.GameWindow;
import game.Setting;
import game.physics.BoxCollider;
import game.renderer.Renderer;

import java.awt.*;
import java.util.ArrayList;

public class Tank extends GameObject {
    int hp;

    public Tank() {
        hitbox = new BoxCollider(this, 32, 32);
    }

    @Override
    public void run() {
        super.run();
        limit();
    }

    public void takeDamage(int damage) {
        hp -= damage;
        if (hp <= 0) {
            hp = 0;
            this.deactive();
        }
    }

    //player limitation movement
    private void limit() {
        if (position.y < Setting.PLAYER_HEIGHT * this.anchor.y)
            position.set(position.x, Setting.PLAYER_HEIGHT * this.anchor.y);                                                               //limit for run up
        if (position.y > Setting.GAME_HEIGHT - Setting.PLAYER_HEIGHT * this.anchor.y)
            position.set(position.x, (double) Setting.GAME_HEIGHT - Setting.PLAYER_HEIGHT * this.anchor.y);                          //limit for run down
        if (position.x < Setting.PLAYER_WIDTH * this.anchor.x)
            position.set(Setting.PLAYER_WIDTH * this.anchor.x, position.y);                                                               //limit for run left
        if (position.x > Setting.GAME_WIDTH - Setting.PLAYER_WIDTH * this.anchor.x)
            position.set(Setting.GAME_WIDTH - Setting.PLAYER_WIDTH * this.anchor.x, position.y);             //limit for run right
    }

}
