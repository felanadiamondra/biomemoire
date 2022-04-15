 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zkfinger;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import zkfinger.ZKScanner;
/** Définit, par héritage de la classe Thread, un nouveau processus léger
    qui affiche cinq messages en attendant 1/2 seconde entre chaque. */
/**
 *
 * @author Diamondra
 */
public class FingerThread extends Thread{
    private String finger;
    private String matr;
    private String typat;
 
    public void setFinger(String doigt) {
        this.finger = doigt;
    }

    public String getFinger() {
        return finger;
    }
    
    public void setMatr(String matricule) {
        this.matr = matricule;
    }

    public String getMatr() {
        return matr;
    }
    
    public void setTypat(String typep) {
        this.typat = typep;
    }

    public String getTypat() {
        return typat;
    }
    
    private boolean isCapture = false; 

    public boolean isIsCapture() {
        return isCapture;
    }
    
    public void run() { 
    while(!this.isCapture){
        try {
            this.isCapture = ZKScanner.getInstance().capture(finger, matr);
            if(this.isCapture){
               this.setImageIcon();
               System.out.println("exemple de test");
            }
            Thread.sleep(500);
         } catch (InterruptedException ie) {
          ie.printStackTrace();
        }
    }
  }
    
    private JLabel libContent;

    public void setLibContent(JLabel libContent) {
        this.libContent = libContent;
    }
 
 public void setImageIcon(){
        try {
            InputStream is = new BufferedInputStream(new FileInputStream(finger+"-fingerprint.bmp"));
            BufferedImage imag;
            imag = ImageIO.read(is);
            Image image = imag;
            ImageIcon icon = new ImageIcon(image);
     
            this.libContent.setIcon(icon);
        } catch (IOException ex) {
            Logger.getLogger(FingerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
 }
}
