package game.tank;

import game.*;
import game.Bullet.Bullet;
import game.Bullet.EnemyBullet;
import game.Bullet.PlayerBullet;
import game.maps.Map1;
import game.menu.SceneManager;
import game.menu.SceneVictory;
import game.physics.BoxCollider;
import game.renderer.Renderer;
import tklibs.AudioUtils;

import javax.sound.sampled.Clip;
import java.util.ArrayList;
import java.util.Random;

public class Enemy extends Tank {

    static boolean isStop = false;
    Clip explosionSound;
    ArrayList<EnemyBullet> bullets;
    public static int point=10;
    public Enemy() {
        hp = 3;
        renderer = new Renderer("assets/Tank/EnemyWhite/Level1");
        bullets = new ArrayList<>();
        this.position.set(0, 0);
        this.explosionSound = AudioUtils.loadSound("sound/EnemyExplosion.wav");
    }

    int fireCount;

    @Override
    public void run() {
        super.run();
        if (!isStop){
            //fire();
            movement();
            checkIntersects();
            checkIntersectPlayer();
            checkIntersectPart();
            checkIntersectEnemy();
        } else {
            this.velocity.set(0, 0);
        }
    }

    public void checkIntersectPlayer() {
        Player player = GameObject.findIntersects(Player.class, this);
        if (player != null) {
            Vector2D thisToPlayer = player.position.clone();
            thisToPlayer.substract(this.position);
            if (Math.abs(thisToPlayer.x) > Math.abs(thisToPlayer.y)) {
                if (thisToPlayer.x > 0) {
                    this.hitDirection = Setting.RIGHT;
                } else {
                    this.hitDirection = Setting.LEFT;
                }
            } else {
                if (thisToPlayer.y > 0) {
                    this.hitDirection = Setting.DOWN;
                } else {
                    this.hitDirection = Setting.UP;
                }
            }
            if (this.direction == this.hitDirection) this.velocity.set(0, 0);
        }
    }

    public void checkIntersectEnemy() {
        Enemy enemy = GameObject.findIntersects(Enemy.class,this);
        if (enemy != null) {
            Vector2D thisToEnemy = enemy.position.clone();
            thisToEnemy.substract(this.position);
            if (Math.abs(thisToEnemy.x) > Math.abs(thisToEnemy.y)) {
                if (thisToEnemy.x > 0) {
                    this.hitDirection = Setting.RIGHT;
                } else {
                    this.hitDirection = Setting.LEFT;
                }
            } else {
                if (thisToEnemy.y > 0) {
                    this.hitDirection = Setting.DOWN;
                } else {
                    this.hitDirection = Setting.UP;
                }
            }
            if (this.direction == this.hitDirection) this.velocity.set(0, 0);
        }
    }

    public void checkIntersectPart() {
        BoxCollider aheadHitBox = this.hitbox.shift(velocity.x, velocity.y);
        GamePart partAhead = GameObject.findIntersects(GamePart.class, aheadHitBox);
        if (partAhead != null) {
            Vector2D thisToPart = partAhead.position.clone();
            thisToPart.substract(this.position);
            if (Math.abs(thisToPart.x) > Math.abs(thisToPart.y)) {
                if (thisToPart.x > 0) {
                    this.hitDirection = Setting.RIGHT;
                } else {
                    this.hitDirection = Setting.LEFT;
                }
            } else {
                if (thisToPart.y > 0) {
                    this.hitDirection = Setting.DOWN;
                } else {
                    this.hitDirection = Setting.UP;
                }
            }
            switch (this.hitDirection) {
                case Setting.UP: {
                    this.position.y = partAhead.position.y + 32;
                    break;
                }
                case Setting.RIGHT: {
                    this.position.x = partAhead.position.x - 32;
                    break;
                }
                case Setting.DOWN: {
                    this.position.y = partAhead.position.y - 32;
                    break;
                }
                case Setting.LEFT: {
                    this.position.x = partAhead.position.x + 32;
                    break;
                }
            }
            this.velocity.set(0, 0);
        }
    }

    private void checkIntersects() {
        PlayerBullet bullet = GameObject.findIntersects(PlayerBullet.class, this);
        if (bullet != null) {
            this.takeDamage(Bullet.damage);
        }
    }

    int count;

    private void movement() {
        count++;
        if (count > 120) {
            this.direction = new Random().nextInt(4) + 1;
            if (this.direction == Setting.UP) this.velocity.set(0, -1);
            else if (this.direction == Setting.DOWN) this.velocity.set(0, 1);
            else if (this.direction == Setting.LEFT) this.velocity.set(-1, 0);
            else if (this.direction == Setting.RIGHT) this.velocity.set(1, 0);
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

    @Override
    public void deactive() {
        super.deactive();
        hp=3;
        Map1.waveCount--;
        Explosion explosion = GameObject.recycle(Explosion.class);
        explosion.position.set(this.position);
        GameObject.score+=point;
        AudioUtils.replay(this.explosionSound);
        if(GameObject.score==250){
            SceneManager.signNewSence(new SceneVictory());
        }
    }

    public static void Stop(){
        isStop = true;
    }

    public static void isNotStop(){
        isStop = false;
    }

}
