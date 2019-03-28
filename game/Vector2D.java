package game;

public class Vector2D {
    public double x;
    public double y;

    public Vector2D(Vector2D other) {
        this(other.x, other.y);
    }

    public Vector2D() {
        this(0, 0);
    }

    public void add(double x, double y) {
        this.x += x;
        this.y += y;
    }

    public void add(Vector2D other) {
        this.add(other.x, other.y);
    }

    public void substract(double x, double y) {
        this.x -= x;
        this.y -= y;
    }

    public void substract(Vector2D other) {
        this.add(other.x, other.y);
    }

    public void scale(double rate) {
        this.x *= rate;
        this.y *= rate;
    }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void set(Vector2D other) {
        this.set(other.x, other.y);
    }

    public Vector2D clone() {
        return new Vector2D(this.x, this.y);
    }

    public double getLength() {
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
    }

    public void setLength(double length) {
        if (this.getLength() != 0) {
            double changingScale = length / this.getLength();
            this.x *= changingScale;
            this.y *= changingScale;
        }
    }

    public double getAngle() {
        return Math.atan(this.y / this.x);
    }

    public void setAngle(double angle) {
        double length = this.getLength();
        if (length != 0) {
            this.x = length * Math.cos(angle);
            this.y = length * Math.sin(angle);
        }
    }

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
