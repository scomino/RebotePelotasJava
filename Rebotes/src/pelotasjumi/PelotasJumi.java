/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pelotasjumi;

import java.awt.Container;
import java.awt.GridBagLayout;
import java.io.IOException;
import javax.swing.JFrame;

/**
 *
 * @author sergio
 */
public class PelotasJumi extends JFrame{
private Espacio espacio;
    
    
   public PelotasJumi() throws IOException{
       espacio=new Espacio(1000,700);
       createFrame();
       new Thread(espacio).start();
       
   }
    private void createFrame() {
        Container panel;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setResizable(false);
        panel = getContentPane();
        panel.add(espacio);
        pack();
        setVisible(true);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
    // TODO code application logic here
    PelotasJumi bb = new PelotasJumi();
    }
    
}
