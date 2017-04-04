package pelotasjumi;

import java.awt.*;

public class Obstaculo implements Runnable{

    int posX;
    int posY;
    Espacio space;
    int width;
    int height;

    public Obstaculo(Espacio space, int posX, int posY, int width, int height) {
        this.space=space;
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
    }
    
    public Rectangle getLimits() {
        return new Rectangle(posX, posY, width, height);
    }

    public void draw(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fill3DRect(posX, posY, width, height, true);
    }

    @Override
    public String toString() {
        return "Obstacle{" + "posX=" + posX + ", posY=" + posY + ", space=" + space + ", width=" + width + ", height=" + height + '}';
    }

    @Override
    public void run() {
        while(true){
            
        }
    }
    
    
    
    
    
    
}
