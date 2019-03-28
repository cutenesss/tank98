package game.tank;

import game.GameObject;
import game.Setting;
import game.physics.BoxCollider;
import game.renderer.Renderer;

import java.awt.*;
import java.util.Set;

public class Bullet extends GameObject {
    public static int damage;

    public Bullet() {
        renderer=new Renderer("assets/Bullets");
        hitbox = new BoxCollider(this, 8, 8);
        damage = 1;
        velocity.set(0,-Setting.BASIC_BULLET_SPEED);
    }

    @Override
    public void run() {
        super.run();
        movement();
        checkDeactiveIfNeeded();
        checkIntersects();
    }

    private void movement() {
        if(this.direction==Setting.UP){
            this.velocity.set(0,-Setting.BASIC_BULLET_SPEED);
        }
        if(this.direction==Setting.DOWN){
            this.velocity.set(0,Setting.BASIC_BULLET_SPEED);
        }
        if(this.direction==Setting.LEFT){
            this.velocity.set(-Setting.BASIC_BULLET_SPEED,0);
        }
        if(this.direction==Setting.RIGHT){
            this.velocity.set(Setting.BASIC_BULLET_SPEED,0);
        }
    }

    public void checkIntersects() {
        Tank tank = GameObject.findIntersects(Tank.class, this);
        if (tank != null) {
            this.deactive();
            tank.takeDamage(damage);
        }
    }

    public void setDirection(int direction1){
        this.direction = direction1;
    }

    private void checkDeactiveIfNeeded() {
        if (this.position.y < -30) {
            this.deactive();
        }
    }
}
