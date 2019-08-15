package game.tank;

import game.*;
import game.Bullet.Bullet;
import game.Bullet.EnemyBullet;
import game.Bullet.PlayerBullet;
import game.Items.ItemFireUp;
import game.Items.ItemTime;
import game.menu.SceneGameover;
import game.menu.SceneManager;
import game.physics.BoxCollider;
import game.renderer.Renderer;
import tklibs.AudioUtils;
import javax.sound.sampled.Clip;

public class Player extends Tank {

    static int amountBullet;
    Clip fireSound;
    int countEnemyStop;

    public Player() {
        amountBullet = 1;
        hp=5;
        renderer = new Renderer("assets/Tank/Player/Lv.0");
        position.set(208, 592);
        this.fireSound = AudioUtils.loadSound("sound/PlayerFire.wav");
        countEnemyStop = 0;
    }

    @Override
    public void run() {
        super.run();
        fire();
        movement();
        checkIntersectEnemy();
        checkIntersectPart();
        checkIntersectBullet();
        checkIntersectItem();
        checkEnemyStop();
    }

    private void checkEnemyStop() {
        if(countEnemyStop > 0) {
            countEnemyStop--;
            if(countEnemyStop < 1) {
                countEnemyStop = 0;
                Enemy.isNotStop();
            }
        }
    }

    public void checkIntersectEnemy() {
        Enemy enemy = GameObject.findIntersects(Enemy.class, this);
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
            if(this.direction == this.hitDirection ) this.velocity.set(0,0);
        }
    }

    public void checkIntersectPart() {
        BoxCollider aheadHitBox = this.hitbox.shift(velocity.x, velocity.y);
        GamePart partAhead = GameObject.findIntersects(GamePart.class, aheadHitBox);
        if (partAhead != null) {
            Vector2D thisToBlock = partAhead.position.clone();
            thisToBlock.substract(this.position);
            if (Math.abs(thisToBlock.x) > Math.abs(thisToBlock.y)) {
                if (thisToBlock.x > 0) {
                    this.hitDirection = Setting.RIGHT;
                } else {
                    this.hitDirection = Setting.LEFT;
                }
            } else {
                if (thisToBlock.y > 0) {
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

    int fireCount;

    private void fire() {
        fireCount++;
        if (GameWindow.isFirePress && fireCount > 30) {
            for (int i = 0; i < amountBullet; i++) {
                PlayerBullet bullet = GameObject.recycle(PlayerBullet.class);
//                bullet.position.set(position.x, position.y + anchor.y * 20); // edited
                bullet.position.set(position); // edited
                bullet.setDirection(this.direction);
                fireCount = 0;
                AudioUtils.replay(this.fireSound);
            }
        }
    }



    private void movement() {
        if (GameWindow.isUpPress) {
            this.velocity.set(0, -1);
            this.direction = Setting.UP;
        } else if (GameWindow.isRightPress) {
            this.velocity.set(1, 0);
            this.direction = Setting.RIGHT;
        } else if (GameWindow.isDownPress) {
            this.velocity.set(0, 1);
            this.direction = Setting.DOWN;
        } else if (GameWindow.isLeftPress) {
            this.velocity.set(-1, 0);
            this.direction = Setting.LEFT;
        }
        if (!GameWindow.isUpPress
                && !GameWindow.isDownPress
                && !GameWindow.isLeftPress
                && !GameWindow.isRightPress) {
            this.velocity.set(0, 0);
        }
    }

    public void checkIntersectBullet(){
        EnemyBullet enemyBullet = GameObject.findIntersects(EnemyBullet.class,this);
        if (enemyBullet != null) {
            this.takeDamage(Bullet.damage);
        }
    }

    public void checkIntersectItem(){
        ItemFireUp item1 = GameObject.findIntersects(ItemFireUp.class,this);
        ItemTime item2 = GameObject.findIntersects(ItemTime.class,this);
        if(item1 != null){
            item1.playerEatItem();
            amountBullet++;
        }
        if(item2 != null ){
            item2.playerEatItem();
            Enemy.Stop();
            countEnemyStop = 300;
        }
    }

    @Override
    public void deactive() {
        super.deactive();
        SceneManager.signNewSence(new SceneGameover());
    }
}
