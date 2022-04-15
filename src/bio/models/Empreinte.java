/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bio.models;
import dao.factory.DAOFactory;
import java.sql.Blob;

/**
 *
 * @author Diamondra
 */
public class Empreinte {
    private int id;
   private String finger;
   private int collaborateur_id;

   public static String getClasseName() {
      return "Empreint";
   }

   public Empreinte() {
   }

   public Empreinte(String doigt, int collaborateur_id) {
      this.finger = doigt;
      this.collaborateur_id = collaborateur_id;
   }

   public Empreinte(int id, String doigt, int collaborateur_id) {
      this.id = id;
      this.finger = doigt;
      this.collaborateur_id = collaborateur_id;
   }

   public int getId() {
      return this.id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getFinger() {
      return this.finger;
   }

   public void setFinger(String doigt) {
      this.finger = doigt;
   }

   public int getCollaborateur_id() {
      return this.collaborateur_id;
   }

   public void setCollaborateur_id(int collaborateur_id) {
      this.collaborateur_id = collaborateur_id;
   }
   public Blob getImage() {
    return DAOFactory.getEmpreinteDAO().getImageByParams(this.collaborateur_id, this.finger);
  }
}
