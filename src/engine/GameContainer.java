package engine;

public class GameContainer implements Runnable {

    private final int WIDTH = 320;
    private final int HEIGHT = 240;
    private float scale = 3f;
    private String title = "Engine Demo v1.0";

    private Window window;
    private Renderer renderer;
    private Input input;
    private AbstractGame game;

    private Thread thread;
    private boolean isRunning = false;
    private final double UPDATE_CAP = 1.0 / 60.0; // update 60 times in 1 second

    public GameContainer(AbstractGame game) {
        this.game = game;
    }

    public void start() {
        window = new Window(this);
        renderer = new Renderer(this);
        input = new Input(this);

        thread = new Thread(this);
        thread.run();
    }

    public void stop() {

    }

    public void run() {
        isRunning = true;
        boolean render;
        double firstTime;
        double lastTime = System.nanoTime() / 1000000000.0;
        double passedTime;
        double unprocessedTime = 0;

        double frameTime = 0;
        int frames = 0;
        int fps = 0;

        game.init(this);

        while (isRunning) {
            render = true;
            firstTime = System.nanoTime() / 1000000000.0;
            passedTime = firstTime - lastTime;
            lastTime = firstTime;

            unprocessedTime += passedTime;
            frameTime += passedTime;

            //freeze thread
            while (unprocessedTime >= UPDATE_CAP) {
                unprocessedTime -= UPDATE_CAP;
                render = true;

                game.update(this, (float) UPDATE_CAP);

                input.update();

                if (frameTime >= 1.0) {
                    frameTime = 0;
                    fps = frames;
                    frames = 0;
                }
            }

            if (render) {
                renderer.clear();
                game.render(this, renderer);
                renderer.process();
                renderer.drawText("FPS: " + fps, 0, 0,0xff00ffff);
                window.updateView();
                frames++;
            } else {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        dispose();
    }

    private void dispose() {

    }

    public Renderer getRenderer() {
        return renderer;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public float getScale() {
        return scale;
    }

    public String getTitle() {
        return title;
    }

    public Window getWindow() {
        return window;
    }

    public Input getInput() {
        return input;
    }
}
