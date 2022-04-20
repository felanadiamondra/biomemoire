/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bio.dao;
import bio.models.Identification;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author HP
 */
public class Identification1DAO extends IDAO<Identification>{
 
  public Identification create(Identification obj) {
    try {
      PreparedStatement statement = this.connect.prepareStatement("INSERT INTO identifications(adresse_mac,empreinte_id,score,doigt,collaborateur_id,auth_id,user,code_cms,matricule,pourcentage)VALUES(?,?,?,?,?,?,?,?,?,?)");
      statement.setInt(1, obj.getId_identification());
      statement.setInt(2, obj.getId_auth());
      statement.setString(3, obj.getDatetime());
      statement.setInt(4, obj.getUtilisateur_id());
      statement.setInt(5, obj.getAdresse_id());
      statement.setInt(6, obj.getId_empreintes());
      statement.setDouble(7, obj.getScore());
      statement.setString(8, obj.getDoigt());
      statement.setInt(9, obj.getCollaborateur_id());
      statement.setString(10, obj.getNom_d_utilisateur());
      statement.setString(11, obj.getCode_Cms());
      statement.setString(12, obj.getAdresse_mac());
      statement.setInt(13, obj.getMatricule());
      statement.executeUpdate();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    } 
    return obj;
  }
   public List<Identification> getAll() {
       throw new UnsupportedOperationException("Not supported yet.");
   }
   
  public void create_identification(Identification obj, String poucentage) {
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
      System.out.println("erreur ato e indray zalaha a!!!!!!!!!!!!!!!!");
    } 
  }
  
  public List<Identification> getAllTableIdentification(int indexMax) {
    ArrayList<Identification> identificationListe = new ArrayList<>();
    try {
      PreparedStatement statement = this.connect.prepareStatement("SELECT * FROM identification WHERE id='" + indexMax + "'");
      ResultSet rs = statement.executeQuery();
      while (rs.next())
        identificationListe.add(new Identification(rs
              .getInt("id"), rs
              .getInt("id_auth"), rs
              .getString("datetime"), rs
              .getInt("utilisateur_id"), rs
              .getInt("adresse_id"), rs
              .getInt("id_empreintes"), rs
              .getInt("score"), rs
              .getString("doigt"), rs
              .getInt("collaborateur_id"), rs
              .getString("nom_d_utilisateur"), rs
              .getString("code_Cms"), rs
              .getString("adresse_mac"), rs
              .getInt("matricule"))); 
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    } 
    return identificationListe;
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
    public Identification update(Identification paramP) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Identification paramP) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Identification get(int paramInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  
}
