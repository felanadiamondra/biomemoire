/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bio.dao;
import bio.models.Ident;
import bio.dao.IDAO;
import bio.models.AuthUser;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import bio.models.Empreinte;
import bio.models.Collaborateur;
import bio.models.Authentification;
import java.time.LocalDateTime;

/**
 *
 * @author HP
 */
public class IdentDAO extends IDAO<Ident>{
    public Ident create(Ident obj) {
    try {
      PreparedStatement statement = this.connect.prepareStatement("INSERT INTO ident(adresse_mac,user, auth_id, empreinte_id, score, doigt, collaborateur_id, code_cms, matricule,created) VALUES (?,?,?,?,?,?,?,?,?,?)");
      statement.setString(1, obj.getAdresseMac());
      statement.setString(2, obj.getUtilisateur());
      statement.setInt(3, obj.getId_auth());
      statement.setInt(4, obj.getEmpreintes());
      statement.setInt(5, obj.getScore());
      statement.setString(6, obj.getDoigt());
      statement.setInt(7, obj.getCollabId());
      statement.setString(8, obj.getCodecms());
      statement.setInt(9, obj.getMatricule());
      //LocalDateTime dateTime = LocalDateTime.parse("2018-05-05T11:50:55");
      statement.setString(10, obj.getDateCreated());
      statement.executeUpdate();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    } 
    return obj;
  }
   public List<Ident> getAll() {
       throw new UnsupportedOperationException("Not supported yet.");
   }
   
  /* public void create_identification(Identification obj, String poucentage) {
    try {
      PreparedStatement statement = this.connect.prepareStatement("INSERT INTO identifications(adresse_mac,empreinte_id,score,doigt,collaborateur_id,auth_id,user,code_cms,matricule,pourcentage)VALUES(?,?,?,?,?,?,?,?,?,?)");
      statement.setString(1, obj.getAdresse_mac());
      statement.setInt(2, obj.getId_empreintes());
      statement.setInt(3, obj.getScore());
      statement.setString(4, obj.getDoigt());
      statement.setInt(5, obj.getCollaborateur_id());
      statement.setInt(6, obj.getId_auth());
      statement.setString(7, obj.getNom_d_utilisateur());
      statement.setString(8, obj.getCode_Cms());
      statement.setInt(9, obj.getMatricule());
      statement.setString(10, poucentage);
      statement.executeUpdate();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
      
    } 
  } */
  
  
  public List<Ident> getAllTableIdentification(int indexMax) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
  
  public int getMAxTableIdentification() {
    int reslutlat = -1;
    try {
      PreparedStatement statement = this.connect.prepareStatement("SELECT max(id) as maxi FROM identification");
      ResultSet rs = statement.executeQuery();
      while (rs.next())
        reslutlat = rs.getInt("maxi"); 
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    } 
    return reslutlat;
  }
   @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Ident update(Ident paramP) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Ident paramP) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Ident get(int paramInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
