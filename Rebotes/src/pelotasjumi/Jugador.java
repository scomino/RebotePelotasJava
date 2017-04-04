package pelotasjumi;


import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Jugador {

    float posX;
    float posY;

    int lives=0;
    float speed;

    float width=20;
    float heigth=20;
    float size = 20;

    private static final Color DEFAULT_COLOR = Color.YELLOW;

    private Espacio space;

    public Jugador(Espacio space, float posX, float posY, float speed) {
        this.space = space;
        this.posX = posX;
        this.posY = posY;
        this.speed = speed;
    }

    public void moveRight() {
        if (!(posX + size + speed >= space.WIDTH)) {
            posX += speed;
        }
    }

    public void moveLeft() {
        if (!(posX - speed <= 0)) {
            posX -= speed;
        }
    }

    public void moveUp() {
        if (!(posY - speed <= 0)) {
            posY -= speed;
        }
    }

    public void moveLUp() {
        if (!(posY - speed <= 0 
                || posX - speed <= 0)) {
            posY -= (speed / 3) * 2;
            posX -= (speed / 3) * 2;
        }
    }

    public void moveRUp() {
        if (!(posY - speed <= 0 
                || posX + size + speed >= space.WIDTH)) {
            posY -= (speed / 3) * 2;
            posX += (speed / 3) * 2;
        }
    }

    public void moveBottom() {
        if (!(posY + size + speed >= space.HEIGHT)) {
            posY += speed;
        }
    }

    public void moveLBottom() {
        if (!(posY + size + speed >= space.HEIGHT 
                || posX - speed <= 0)) {
            posY += (speed / 3) * 2;
            posX -= (speed / 3) * 2;
        }
    }

    public void moveRBottom() {
        if (!(posY + size + speed >= space.HEIGHT 
                || posX + size + speed >= space.WIDTH)) {
            posY += (speed / 3) * 2;
            posX += (speed / 3) * 2;
        }
    }

    public void draw(Graphics g) {
        g.setColor(DEFAULT_COLOR);
        g.fillRect((int) posX, (int) posY, (int) size, (int) size);
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
    
    public void setLocation(){
        setPosX(100);
         setPosY(200);
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }
    
    

}
