package game.physics;

import game.GameObject;
import game.Vector2D;

public class BoxCollider {
    public Vector2D position;
    public Vector2D anchor;
    public int width;
    public int height;

    public BoxCollider(GameObject master, int width, int height) {
        this.position = master.position;
        this.width = width;
        this.height = height;
        this.anchor = master.anchor;
    }

    public double top() {
        return this.position.y - this.anchor.y * this.height;
    }

    public double bot() {
        return this.top() + this.height;
    }

    public double left() {
        return this.position.x - this.anchor.x * width;
    }

    public double right() {
        return this.left() + this.width;
    }

    public boolean intersect(BoxCollider other) {
        return other.right() >= this.left() && other.left() <= this.right()
                && other.top() <= this.bot() && other.bot() >= this.top();
    }
}
