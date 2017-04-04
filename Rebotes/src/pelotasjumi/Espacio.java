/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pelotasjumi;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import sockets.SocketServer;

/**
 *
 * @author sergio
 */
public class Espacio extends Canvas implements Runnable,ActionListener,KeyListener{
int WIDTH;
    int HEIGHT;
    private ArrayList<Pelotas> balls;
    private ArrayList<Obstaculo> obstaculos;
    private ArrayList<Anegro> negros;
      private Jugador player;
      private SocketServer cliente;

        
        
        
   public Espacio(int w, int h) throws IOException{
       this.WIDTH=w;
       this.HEIGHT=h;
       this.setPreferredSize(new Dimension(this.WIDTH, this.HEIGHT));
        setFocusable(true);
        requestFocus();
        addKeyListener(this);
          player = new Jugador(this, 100, 100, 5);
          cliente = new SocketServer(player);
        new Thread(cliente).start();
          this.initObstacles();
        this.initBalls();
        this.initANegros();
   }     
   
   private void initObstacles(){
        obstaculos = new ArrayList<>();
        for(int i=0;i<3;i++){
       int posX = ThreadLocalRandom.current().nextInt(23, this.WIDTH - 23);
        int posY = ThreadLocalRandom.current().nextInt(23, this.HEIGHT - 23);
        int w = ThreadLocalRandom.current().nextInt(100);
        int d = ThreadLocalRandom.current().nextInt(100);
        Obstaculo obs = new Obstaculo(this,posX,posY,w,d);
        obstaculos.add(obs);
        new Thread(obs).start();
        System.out.println(obs.toString());
        }
   }
   
   
   private void initANegros(){
        negros = new ArrayList<>();
        for(int i=0;i<1;i++){
       int posX = ThreadLocalRandom.current().nextInt(23, this.WIDTH - 23);

        Anegro negs = new Anegro(this,posX);
        negros.add(negs);
        }
   }
   
   
    private void initBalls() {
        balls = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
        float posX = ThreadLocalRandom.current().nextInt(23, this.WIDTH - 23);
        float posY = ThreadLocalRandom.current().nextInt(23, this.HEIGHT - 23);
        float radius = ThreadLocalRandom.current().nextInt(4, 25);
        float mass = radius;
        float speed = ThreadLocalRandom.current().nextInt(2, 4);
        float angle = ThreadLocalRandom.current().nextInt(0, 359); 
        Random randomGenerator = new Random();
        int red = randomGenerator.nextInt(256);
        int green = randomGenerator.nextInt(256);
        int blue = randomGenerator.nextInt(256);
        Color randomColour = new Color(red,green,blue);
        Pelotas b = new Pelotas(this, posX, posY, radius, mass, speed, angle,randomColour);
        balls.add(b);
        new Thread(b).start();
        }
    }
    
    
     public synchronized void paint() {
        BufferStrategy bs;

        bs = this.getBufferStrategy();
        if (bs == null) {
            return; // Error en la recuperación del BufferStrategy =======================================================>>
        }

        if ((this.WIDTH <= 0) || (this.HEIGHT <= 0)) {
            System.out.println("Map size error: (" + this.WIDTH + "x" + this.HEIGHT + ")");
            return; // Error en los tamaños del mapa ===================================================>>
        }

        Graphics gr = bs.getDrawGraphics();

        // Pinta el fondo, mejor hacerlo con una BufferedImage
        gr.setColor(Color.BLACK);
        gr.fillRect(0, 0, this.WIDTH, this.HEIGHT);
        
        for (int i=0;i<negros.size();i++){
           negros.get(i).draw(gr);
        }
        
           for (int i=0;i<obstaculos.size();i++){
           obstaculos.get(i).draw(gr);
        }
           
        for (int i=0;i<balls.size();i++){
           balls.get(i).draw(gr);
        }
        
     
        
   
        
        player.draw(gr);
        
        bs.show();
        gr.dispose();
    }

    public ArrayList<Pelotas> getBalls() {
        return balls;
    }
     
    public ArrayList<Obstaculo> getObstaculos() {
        return obstaculos;
    }

    public Jugador getPlayer() {
        return player;
    }
    
    
    
 
    @Override
    public void run() {
        this.createBufferStrategy(2);
        while (true) {
            try {
                this.paint();
                Thread.sleep(20);
                if(player.lives>20){
                    player.setLocation();
                    player.lives=0;
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Espacio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       
    }

    @Override
    public void keyTyped(KeyEvent e) {
       
    }

    @Override
    public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_UP:
                        player.moveUp();
                        break;
                    case KeyEvent.VK_DOWN:
                        player.moveBottom();
                        break;
                    case KeyEvent.VK_RIGHT:
                        player.moveRight();
                        break;
                    case KeyEvent.VK_LEFT:
                        player.moveLeft();
                        break;
                }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}
