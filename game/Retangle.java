package game;

public class Retangle {
    public Vector2D position;
    public int width;
    public int height;

    public Retangle(Vector2D position, int width, int height) {
        this.position = position;
        this.width = width;
        this.height = height;
    }

    public double top() {
        return this.position.y;
    }

    public double bot() {
        return this.top() + this.height;
    }

    public double left() {
        return this.position.x;
    }

    public double right() {
        return this.left() + this.width;
    }

    public boolean intersect(Retangle other) {
        return other.right() >= this.left() && other.left() <= this.right()
                && other.top() <= this.bot() && other.bot() >= this.top();
    }
}
