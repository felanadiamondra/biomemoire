/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bio.dao;
import bio.models.Authentification;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class AuthentificationDAO extends IDAO<Authentification>{
    private static AuthentificationDAO instance;
  
    public static AuthentificationDAO getInstance() {
      if (instance == null)
        instance = new AuthentificationDAO(); 
      return instance;
    }
    
    public Authentification create(Authentification obj) {
    try {
      PreparedStatement statement = this.connect.prepareStatement("INSERT INTO authentifications(datetime, utilisateur_id, adresse_id) VALUES(?, ?, ?)", 1);
      statement.setString(1, obj.getDatetime());
      statement.setInt(2, obj.getUtilisateur_id());
      statement.setInt(3, obj.getAdresse_id());
      statement.executeUpdate();
      ResultSet rs = statement.getGeneratedKeys();
      if (rs.next())
        obj.setId(rs.getInt(1)); 
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    } 
    return obj;
  }
    
  public Authentification update(Authentification obj) {
    return obj;
  }
  
  public boolean delete(Authentification obj) {
    try {
      PreparedStatement statement = this.connect.prepareStatement("DELETE FROM authentifications WHERE id=?");
      statement.setInt(1, obj.getId());
      statement.executeUpdate();
      return true;
    } catch (SQLException e) {
      System.out.println(e.getMessage());
      return false;
    } 
  }
  
  public Authentification get(int id) {
    try {
      PreparedStatement statement = this.connect.prepareStatement("SELECT * FROM authentifications WHERE id=?");
      statement.setInt(1, id);
      ResultSet rs = statement.executeQuery();
      if (rs.next())
        return new Authentification(rs
            .getInt("id"), rs
            .getString("datetime"), rs
            .getInt("utilisateur_id"), rs
            .getInt("adresse_id")); 
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    } 
    return null;
  }
  
  public List<Authentification> getAll() {
    ArrayList<Authentification> adresses = new ArrayList<>();
    try {
      PreparedStatement statement = this.connect.prepareStatement("SELECT * FROM authentifications");
      ResultSet rs = statement.executeQuery();
      while (rs.next())
        adresses.add(new Authentification(rs
              .getInt("id"), rs
              .getString("datetime"), rs
              .getInt("utilisateur_id"), rs
              .getInt("adresse_id"))); 
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    } 
    return adresses;
  }
  
  public int size() {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
