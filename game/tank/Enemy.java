package game.tank;

import game.GameObject;
import game.Setting;
import game.renderer.Renderer;

import java.util.ArrayList;
import java.util.Random;

public class Enemy extends Tank{

    ArrayList<EnemyBullet> bullets;

    public Enemy(){
        renderer= new Renderer("assets/Tank/EnemyWhite/Light");
        bullets = new ArrayList<>();
        this.position.set(100,100);
    }

    int fireCount;

    @Override
    public void run() {
        super.run();
        fire();
        movement();
        //changeDirection();
        checkIntersects();
    }

    private void checkIntersects() {
        PlayerBullet bullet = GameObject.findIntersects(PlayerBullet.class,this);
        if(bullet != null){
            this.takeDamage(Bullet.damage);
        }
    }

    int count;
    private void movement() {
        count++;
        if(count > 120){
            this.direction = new Random().nextInt(4) + 1;
            if(this.direction == Setting.UP) this.velocity.set(0, -1);
            else if(this.direction == Setting.DOWN) this.velocity.set(0, 1);
            else if(this.direction == Setting.LEFT) this.velocity.set(-1, 0);
            else if(this.direction == Setting.RIGHT) this.velocity.set(1, 0);
            count = 0;
        }
    }

    private void fire() {
        fireCount++;
        if (fireCount > 60) {
            EnemyBullet bullet = GameObject.recycle(EnemyBullet.class);
            bullet.position.set(position.x, position.y + anchor.y * 20);
            bullet.setDirection(this.direction);
            bullets.add(bullet);
            fireCount = 0;
        }
    }

}
