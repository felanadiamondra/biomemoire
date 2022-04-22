/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bio.dao;
import bio.models.Adresse;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author HP
 */
public class AdresseDAO extends IDAO<Adresse>{
    private static AdresseDAO instance;
  
  public static AdresseDAO getInstance() {
    if (instance == null)
      instance = new AdresseDAO(); 
    return instance;
  }
  
  public Adresse create(Adresse obj) {
    try {
      PreparedStatement statement = this.connect.prepareStatement("INSERT INTO adresses(adresse_mac) VALUES(?)", 1);
      statement.setString(1, obj.getAdresse_mac());
      statement.executeUpdate();
      ResultSet rs = statement.getGeneratedKeys();
      if (rs.next())
        obj.setId(rs.getInt(1)); 
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    } 
    return obj;
  }
  
  public Adresse update(Adresse obj) {
    try {
      PreparedStatement statement = this.connect.prepareStatement("UPDATE adresses SET adresse_mac=?  WHERE id=?");
      statement.setString(1, obj.getAdresse_mac());
      statement.setInt(2, obj.getId());
      statement.executeUpdate();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    } 
    return obj;
  }
  
  public boolean delete(Adresse obj) {
    try {
      PreparedStatement statement = this.connect.prepareStatement("update adresses  set status='inactif' WHERE id=?");
      statement.setInt(1, obj.getId());
      statement.executeUpdate();
      return true;
    } catch (SQLException e) {
      System.out.println(e.getMessage());
      return false;
    } 
  }
  
  public Adresse get(int id) {
    try {
      PreparedStatement statement = this.connect.prepareStatement("SELECT * FROM adresses WHERE id=?");
      statement.setInt(1, id);
      ResultSet rs = statement.executeQuery();
      if (rs.next())
        return new Adresse(rs
            .getInt("id"), rs
            .getString("adresse_mac")); 
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    } 
    return null;
  }
  
  public List<Adresse> getAll() {
    ArrayList<Adresse> adresses = new ArrayList<>();
    try {
      PreparedStatement statement = this.connect.prepareStatement("SELECT * FROM adresses where status='actif'");
      ResultSet rs = statement.executeQuery();
      while (rs.next())
        adresses.add(new Adresse(rs
              .getInt("id"), rs
              .getString("adresse_mac"))); 
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    } 
    return adresses;
  }
  
  public int size() {
    try {
      PreparedStatement statement = this.connect.prepareStatement("SELECT COUNT(*) AS nombre FROM adresses");
      ResultSet rs = statement.executeQuery();
      if (rs.next())
        return rs.getInt("nombre"); 
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    } 
    return 0;
  }
  
  
  
  public boolean getbyAdresseMac(String mac) {
     boolean adresse = false;
    try {
      PreparedStatement statement = this.connect.prepareStatement("SELECT * FROM adresses WHERE adresse_mac=?");
      statement.setString(1, mac);
      ResultSet rs = statement.executeQuery();
      if (rs.next()){
          adresse = true;
            System.out.println("Id adresse: " + rs.getInt("id"));
      }
      else{
          adresse= false;
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    } 
    return adresse;
  }
}
