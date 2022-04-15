/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bio.dao;
import bio.models.Collaborateur;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Diamondra
 */
public class CollaborateurDAO extends IDAO<Collaborateur>{
    public Collaborateur create(Collaborateur obj) {
    try {
      PreparedStatement statement = this.connect.prepareStatement("INSERT INTO collaborateurs(matricule, nom, date_de_naissance, type) VALUES(?, ?, ?, ?)", 1);
      statement.setString(1, obj.getMatricule());
      statement.setString(2, obj.getName());
      statement.setString(3, obj.getDateNaiss());
      statement.setString(4, obj.getType());
      statement.executeUpdate();
      ResultSet rs = statement.getGeneratedKeys();
      if (rs.next())
        obj.setId(rs.getInt(1)); 
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    } 
    return obj;
  }

    @Override
    public Collaborateur update(Collaborateur obj) {
         try {
            PreparedStatement statement = this.connect.prepareStatement("UPDATE collaborateurs SET type=?, matricule=?, nom=?, date_de_naissance=?  WHERE id=?");
            statement.setString(1, obj.getMatricule());
            statement.setString(2, obj.getName());
            statement.setString(3, obj.getDateNaiss());
            statement.setString(4, obj.getType());
            statement.setInt(5, obj.getId());
            statement.executeUpdate();
          } catch (SQLException e) {
            System.out.println(e.getMessage());
          } 
          return obj;
    }
    public Collaborateur isthere(Collaborateur obj) {
        try {
          PreparedStatement statement = this.connect.prepareStatement(" select * from collaborateurs where matricule=? and nom=? and date_de_naissance=? and type=?");
          statement.setString(1, obj.getMatricule());
          statement.setString(2, obj.getName());
          statement.setString(3, obj.getDateNaiss());
          statement.setString(4, obj.getType());
          ResultSet rs = statement.executeQuery();
          if (rs.next()) {
            obj.setId(rs.getInt(1));
            return obj;
          } 
        } catch (SQLException e) {
          System.out.println(e.getMessage());
        } 
        return null;
  }
    @Override
    public boolean delete(Collaborateur obj) {
        try {
            PreparedStatement statement = this.connect.prepareStatement("DELETE FROM collaborateurs WHERE id=?");
            statement.setInt(1, obj.getId());
            statement.executeUpdate();
            return true;
          } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
          } 
    }

    @Override
    public Collaborateur get(int id) {
        try {
        PreparedStatement statement = this.connect.prepareStatement("SELECT * FROM collaborateurs WHERE id=?");
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        if (rs.next())
          return new Collaborateur(rs
              .getInt("id"), rs
              .getString("matricule"), rs
              .getString("nom"), rs
              .getString("date_de_naissance"), rs
              .getString("type")); 
      } catch (SQLException e) {
        System.out.println(e.getMessage());
      } 
      return null;
     }

    @Override
    public List<Collaborateur> getAll() {
        ArrayList<Collaborateur> personnes = new ArrayList<>();
        try {
          PreparedStatement statement = this.connect.prepareStatement("SELECT * FROM collaborateurs ORDER BY id DESC ");
          ResultSet rs = statement.executeQuery();
          while (rs.next())
            personnes.add(new Collaborateur(rs
                  .getInt("id"), rs
                  .getString("matricule"), rs
                  .getString("nom"), rs
                  .getString("date_de_naissance"), rs
                  .getString("type"))); 
        } catch (SQLException e) {
          System.out.println(e.getMessage());
        } 
        return personnes;
    }
    
    public Collaborateur getByParamas(String matr, String type){
        try {
        PreparedStatement statement = this.connect.prepareStatement("SELECT * FROM collaborateurs WHERE matricule=? AND matricule=?");
        statement.setString(1, matr);
        statement.setString(2, type);
        ResultSet rs = statement.executeQuery();
        if (rs.next())
          return new Collaborateur(rs
              .getInt("id"), rs
              .getString("matricule"), rs
              .getString("nom"), rs
              .getString("date_de_naissance"), rs
              .getString("type")); 
      } catch (SQLException e) {
        System.out.println(e.getMessage());
      } 
      return null;
      }
    
    public List<Collaborateur> getBy(String matr){
        List<Collaborateur> rep = new ArrayList<>();
        try {
          PreparedStatement statement = this.connect.prepareStatement("SELECT * FROM collaborateurs WHERE matricule=?");
          statement.setString(1, matr);
          ResultSet rs = statement.executeQuery();
          while (rs.next())
            rep.add(new Collaborateur(rs
                  .getInt("id"), rs
                  .getString("matricule"), rs
                  .getString("nom"), rs
                  .getString("date_de_naissance"), rs
                  .getString("type"))); 
        } catch (SQLException e) {
          System.out.println(e.getMessage());
        } 
        return rep;
    }
    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
