/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pelotasjumi;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author sergi
 */
public class Anegro {
    int posX;
    int posY=0;
    Espacio space;
    int width=100;
    int height=700;
    
    
    public Anegro(Espacio space, int posX) {
        this.space=space;
        this.posX = posX;
        this.width = width;
        this.height = height;
    }
    
    public Rectangle getLimits() {
        return new Rectangle(posX, posY, width, height);
    }

    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fill3DRect(posX, posY, width, height, true);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
