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
import zkfinger.ZKScannerMatch;
import zkfinger.ZKScanner;
/**
 *
 * @author Diamondra
 */
public class TrackThread extends Thread{
    private String matr;
 
    public void setMatr(String matricule) {
        this.matr = matricule;
    }

    public String getMatr() {
        return matr;
    }
    
    
    private boolean isCapture = false; 

    public boolean isIsCapture() {
        return isCapture;
    }
    
    public void run() { 
    while(true){
        try {
            this.isCapture = ZKScanner.getInstance().capture();
            System.out.println(this.isCapture);
            if(this.isCapture){
               this.setImageIcon();
               //System.out.println("exemple de test");
               String matr= this.getMatr();
               ZKScannerMatch match= ZKScanner.getInstance().match();
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
            this.libContent.setText("");
            InputStream is = new BufferedInputStream(new FileInputStream("fingerprint.bmp"));
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
