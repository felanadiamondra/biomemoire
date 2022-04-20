/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bioedjir;
import bio.views.Login;
import javax.swing.JOptionPane;
import zkfinger.ZKScanner;
/**
 *
 * @author HP
 */
public class BIOEDJIR {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JOptionPane.showMessageDialog(null, ZKScanner.getInstance().open());
        new Login().setVisible(true);
    }
    
}
