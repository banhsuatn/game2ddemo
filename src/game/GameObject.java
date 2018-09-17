package game;

import engine.GameContainer;
import engine.Renderer;

public abstract class GameObject {
    protected float posX, posY;
    protected int width, height;
    protected String tag;
    protected boolean isDead = false;

    public abstract void update(GameContainer gc, GameManager gm, float dt);

    public abstract void render(GameContainer gc, Renderer r);

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }
}
