package engine;


import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Window {
    private JFrame frame;
    private Canvas canvas;
    private Graphics2D graphics2D;
    private BufferedImage bfImage;
    private BufferStrategy bs;

    public Window(GameContainer gc) {
        canvas = new Canvas();
        Dimension dimension = new Dimension((int)(gc.getWIDTH()*gc.getScale()),(int)(gc.getHEIGHT()*gc.getScale()));
        canvas.setPreferredSize(dimension);
        canvas.setMaximumSize(dimension);
        canvas.setMinimumSize(dimension);

        frame = new JFrame(gc.getTitle());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(canvas,BorderLayout.CENTER);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        canvas.createBufferStrategy(2);
        bfImage = new BufferedImage(gc.getWIDTH(),gc.getHEIGHT(),BufferedImage.TYPE_INT_RGB);
        bs = canvas.getBufferStrategy();
        graphics2D = (Graphics2D) bs.getDrawGraphics();
    }

    public void updateView(){
        graphics2D.drawImage(bfImage,0,0,canvas.getWidth(),canvas.getHeight(),null);
        bs.show();
    }

    public BufferedImage getBfImage() {
        return bfImage;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public JFrame getFrame() {
        return frame;
    }
}
