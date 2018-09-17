package game;

import engine.GameContainer;
import engine.Renderer;

import java.awt.event.KeyEvent;


public class Player extends GameObject {

    private int tileX;
    private int tileY;
    private int offX;
    private int offY;

    private int speed = 150;
    private float fallSpeed = 10;
    private float fallDistance = 0;
    private float jump = -6;
    private boolean ground = false;

    public Player(int posX, int posY) {
        this.posX = posX * GameManager.TS;
        this.posY = posY * GameManager.TS;
        this.tileX = posX;
        this.tileY = posY;
        this.offX = 0;
        this.offY = 0;
        this.width = GameManager.TS;
        this.height = GameManager.TS;
        this.tag = "player";
    }

    @Override
    public void update(GameContainer gc, GameManager gm, float dt) {
        //left right
        if (gc.getInput().isKey(KeyEvent.VK_A)) {
            offX -= dt * speed;
        }
        if (gc.getInput().isKey(KeyEvent.VK_D)) {
            offX += dt * speed;
        }
        //jump + gravity
        fallDistance += dt * fallSpeed;
        if (gc.getInput().isKeyDown(KeyEvent.VK_W) && ground) {
            fallDistance = jump;
            ground = false;
        }
        offY += fallDistance;
        if (gm.getCollision(tileX, tileY + 1) && offY >= 0) {
            fallDistance = 0;
            offY = 0;
            ground = true;
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
        posX = tileX * GameManager.TS + offX;
        posY = tileY * GameManager.TS + offY;

    }

    @Override
    public void render(GameContainer gc, Renderer r) {
        r.drawFillRect((int) posX, (int) posY, width, height, 0xff00ff00);
    }
}
