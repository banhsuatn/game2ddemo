package game;

import engine.GameContainer;
import engine.Renderer;

public class Bullet extends GameObject {


    private int tileX;
    private int tileY;
    private float offX;
    private float offY;

    private int direction;
    private float speed = 200;

    public Bullet(int posX, int posY, int direction) {
        this.direction = direction;
        this.posX = posX;
        this.posY = posY;
        this.tileX = posX / GameManager.TS + 1;
        this.tileY = posY / GameManager.TS + 1;
        this.offX = posX % GameManager.TS - GameManager.TS / 2;
        this.offY = posY % GameManager.TS - GameManager.TS / 2;
        this.tag = "bullet";
    }

    @Override
    public void update(GameContainer gc, GameManager gm, float dt) {
        switch (direction) {
            case 0:
                offY -= speed * dt;
                break;
            case 1:
                offX += speed * dt;
                break;
            case 2:
                offY += speed * dt;
                break;
            case 3:
                offX -= speed * dt;
                break;
            default:
                break;
        }
        if (offY > GameManager.TS / 2) {
            tileY++;
            offY -= GameManager.TS;
        }
        if (offY < -GameManager.TS / 2) {
            tileY--;
            offY += GameManager.TS;
        }
        if (offX > GameManager.TS / 2) {
            tileX++;
            offX -= GameManager.TS;
        }
        if (offX < -GameManager.TS / 2) {
            tileX--;
            offX += GameManager.TS;
        }

        if (gm.getCollision(tileX, tileY)) {
            this.isDead = true;
        }

        posX = tileX * GameManager.TS + offX;
        posY = tileY * GameManager.TS + offY;
    }

    @Override
    public void render(GameContainer gc, Renderer r) {
        r.drawFillRect((int) posX - GameManager.TS / 8, (int) posY - GameManager.TS / 8, GameManager.TS / 4, GameManager.TS / 4, 0xffff0000);

    }
}
