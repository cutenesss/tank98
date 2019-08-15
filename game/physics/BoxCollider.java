package game.physics;

import game.GameObject;
import game.Vector2D;

import javax.swing.*;

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

    public BoxCollider(Vector2D position, Vector2D anchor, int width, int height) {
        this.position = position;
        this.width = width;
        this.height = height;
        this.anchor = anchor;
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

    public BoxCollider shift(double x, double y) {
        Vector2D shiftedPosition = this.position.clone();
        shiftedPosition.add(x, y);
        return new BoxCollider(shiftedPosition, this.anchor, this.width, this.height);
    }

    public BoxCollider shift(Vector2D velocity) {
        return this.shift(velocity.x, velocity.y);
    }
}
