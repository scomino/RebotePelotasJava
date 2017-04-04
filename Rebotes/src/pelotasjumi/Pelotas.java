package pelotasjumi;

import java.awt.*;
import java.util.Formatter;

public class Pelotas implements Runnable {

    float posX;
    float posY;

    float speedX;
    float speedY;

    float radius;
    float mass;

    private Color color;
    private static final Color DEFAULT_COLOR = Color.PINK;

    private Espacio space;

    public Pelotas(Espacio space, float posX, float posY, float radius, float mass,
        float speed, float angle, Color color) {
        this.space = space;
        this.posX = posX;
        this.posY = posY;
        // Convert (speed, angle) to (posX, posY), with posY-axis inverted
        this.speedX = (float) (speed * Math.cos(Math.toRadians(angle)));
        this.speedY = (float) (-speed * Math.sin(Math.toRadians(angle)));
        this.radius = radius;
        this.mass = mass;
        this.color = color;
        this.checkValidColor();
    }

    public Pelotas(Espacio space, float posX, float posY, float radius, float mass,
            float speed, float angle) {
        this(space, posX, posY, radius, mass, speed, angle, DEFAULT_COLOR);
    }

    private void checkValidColor() {
        if (color == Color.BLACK) {
            System.out.println("Color de bola no válido, estableciendo color por defecto.");
            color = DEFAULT_COLOR;
        }
    }

    public Color getColor() {
        return this.color;
    }

    // Temporal
    public Rectangle getLimits() {
        return new Rectangle((int) (posX - radius), (int) (posY - radius), (int) (radius * 2), (int) (radius * 2));
    }

    private void move() {
        CollisionPhysics.checkBall2WallCollision(this, space);
        CollisionPhysics.checkBall2BallCollision(this, space.getBalls());
        CollisionPhysics.checkBall2ObstacleCollision(this, space.getObstaculos());
        CollisionPhysics.checkBall2PlayerCollision(this, space.getPlayer());
        posX += speedX;
        posY += speedY;
    }

    public void bounceX() {
        this.speedX *= -1;
    }

    public void bounceY() {
        this.speedY *= -1;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval((int) (posX - radius), (int) (posY - radius), (int) radius * 2, (int) radius * 2);
    }

    public float getSpeed() {
        return (float) Math.sqrt(speedX * speedX + speedY * speedY);
    }

    public float getAngle() {
        return (float) Math.toDegrees(Math.atan2(-speedY, speedX));
    }

    private StringBuilder sb = new StringBuilder();
    private Formatter formatter = new Formatter(sb);

    @Override
    public String toString() {
        sb.delete(0, sb.length());
        formatter.format("@(%3.0f,%3.0f) r=%3.0f V=(%3.0f,%3.0f) speed=%4.1f angle=%4.0fº",
                posX, posY, radius, speedX, speedY, getSpeed(), getAngle());  // \u0398 is theta
        return sb.toString();
    }

    @Override
    public void run() {
        while (true) {
            this.move();
                try {
                    this.move();
                      Thread.sleep(20);
                } catch (InterruptedException ex) {
                }
            }
        }

    }

