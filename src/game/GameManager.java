package game;

import engine.AbstractGame;
import engine.GameContainer;
import engine.Renderer;
import engine.gfx.Image;

import java.util.ArrayList;

public class GameManager extends AbstractGame {
    public static final int TS = 16;

    private boolean[] collision;
    private int levelW;
    private int levelH;
    ArrayList<GameObject> objects = new ArrayList<>();

    public GameManager() {
        objects.add(new Player(7, 8));
        loadLevel("/level.png");
    }

    @Override
    public void init(GameContainer gc) {
        gc.getRenderer().setAmbientColor(-1);
    }

    @Override
    public void update(GameContainer gc, float dt) {
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).update(gc, this, dt);
            if (objects.get(i).isDead()) {
                objects.remove(i);
                i--;
            }
        }
    }

    @Override
    public void render(GameContainer gc, Renderer r) {
        for (int y = 0; y < levelH; y++) {
            for (int x = 0; x < levelW; x++) {
                if (collision[x + y * levelW]) {
                    r.drawFillRect(x * TS, y * TS, TS, TS, 0xff0f0f0f);
                } else {
                    r.drawFillRect(x * TS, y * TS, TS, TS, 0xfff9f9f9);
                }
            }
        }
        for (GameObject object : objects) {
            object.render(gc, r);
        }
    }

    public void loadLevel(String path) {
        Image levelImage = new Image(path);
        levelW = levelImage.getW();
        levelH = levelImage.getH();
        collision = new boolean[levelW * levelH];
        for (int y = 0; y < levelImage.getH(); y++) {
            for (int x = 0; x < levelImage.getW(); x++) {
                collision[x + y * levelW] = levelImage.getP()[x + y * levelW] == 0xff000000;
            }
        }
    }

    public boolean getCollision(int x, int y) {
        if (x < 0 || x >= levelW || y < 0 || y >= levelH) {
            return true;
        }
        return collision[x + y * levelW];
    }
}
