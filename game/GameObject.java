package game;

import game.physics.BoxCollider;
import game.renderer.Renderer;

import java.awt.*;
import java.net.PortUnreachableException;
import java.util.ArrayList;

public class GameObject {
    //Quan li doi tuong
    public static int score = 0;

    public static ArrayList<GameObject> objects = new ArrayList<>();


    public static <E extends GameObject> E recycle(Class<E> cls) {
        E result = null;
        //tim kiem phan tu da ton tai de recycle
        for (int i = 0; i < objects.size(); i++) {
            GameObject object = objects.get(i);
            if (!object.active && object.getClass().isAssignableFrom(cls)) {
                result = (E) object;
                break;
            }
        }
        //
        if (result != null) {
            result.reset();
            return result;
        }
        try {
            result = cls.getConstructor().newInstance();
            return result;
        } catch (Exception ex) {
            return null;
        }
    }

    //find
    public static <E extends GameObject> E find(Class<E> cls) {
        for (int i = 0; i < objects.size(); i++) {
            GameObject object = objects.get(i);
            if (object.getClass().isAssignableFrom(cls) && object.active) return (E) object;
        }
        return null;
    }

    public static <E extends GameObject> E findIntersects(Class<E> cls, GameObject source) {
        for (int i = 0; i < objects.size(); i++) {
            GameObject object = objects.get(i);
            if (object.getClass().isAssignableFrom(cls)
                    && object.active
                    && object.intersects(source)
                    && !source.equals(object))
                return (E) object;
        }
        return null;
    }

    public static <E extends GameObject> E findIntersects(Class<E> cls, BoxCollider source) {
        for (int i = 0; i < objects.size(); i++) {
            GameObject object = objects.get(i);
            if (object.getClass().isAssignableFrom(cls) && object.active && object.hitbox.intersect(source))
                return (E) object;
        }
        return null;
    }

    public static <E extends GameObject> E findIntersects(Class<E> cls, BoxCollider source, GameObject sourceObject) {
        for (int i = 0; i < objects.size(); i++) {
            GameObject object = objects.get(i);
            if (object.getClass().isAssignableFrom(cls)
                    && object.active
                    && object.hitbox.intersect(source)
                    && !sourceObject.equals(object))
                return (E) object;
        }
        return null;
    }



    //Dinh nghia doi tuong
    public Renderer renderer;
    public Vector2D position;
    public Vector2D velocity;
    public Vector2D anchor;
    public boolean active;
    public BoxCollider hitbox;
    public int direction;
    //

    public GameObject() {
        objects.add(this);
        this.position = new Vector2D(-20,-20);
        this.velocity = new Vector2D();
        this.anchor = new Vector2D(0.5, 0.5);
        this.active = true;
    }

    public void render(Graphics g) {
        if (renderer != null) {
            renderer.render(g, this);
        }
    }

    public static void clearAll(){
        objects.clear();
        score = 0;
    }

    public void run() {
        position.add(this.velocity);
    }

    public void deactive() {
        active = false;
    }

    public void reset() {
        active = true;
    }

    public boolean intersects(GameObject other) {
        if (this.hitbox != null && other.hitbox != null) {
            return this.hitbox.intersect(other.hitbox);
        }
        return false;
    }
}