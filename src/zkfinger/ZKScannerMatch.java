/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zkfinger;
import bio.models.Empreinte;
/**
 *
 * @author Diamondra
 */
public class ZKScannerMatch {
    public int score;
    
    public Empreinte empreinte;
    
    public ZKScannerMatch(int score, Empreinte empreinte) {
      this.score = score;
      this.empreinte = empreinte;
    }
}
