/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bio.dao;
import bio.models.Empreinte;
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
import bio.models.Collaborateur;
import dao.factory.DAOFactory;

/**
 *
 * @author Diamondra
 */
public class EmpreinteDAO extends IDAO<Empreinte>{
    public Empreinte create(Empreinte obj) {
    try {
      PreparedStatement statement = this.connect.prepareStatement("INSERT INTO empreintes(doigt, image, collaborateur_id) VALUES(?,AES_ENCRYPT(?,'key'), ?)", 1);
      statement.setString(1, obj.getFinger());
      statement.setBlob(2, new FileInputStream(new File("fingerprint.bmp")));
      statement.setInt(3, obj.getCollaborateur_id());
      statement.executeUpdate();
      ResultSet rs = statement.getGeneratedKeys();
      if (rs.next())
        obj.setId(rs.getInt(1)); 
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    } catch (FileNotFoundException ex) {
      Logger.getLogger(EmpreinteDAO.class.getName()).log(Level.SEVERE, (String)null, ex);
    } 
    return obj;
  }

    @Override
    public Empreinte update(Empreinte obj) {
        try {
            PreparedStatement statement = this.connect.prepareStatement("UPDATE empreintes SET doigt=?, image=?, collaborateur_id=?,  WHERE id=?");
            statement.setString(1, obj.getFinger());
            statement.setBlob(2, new FileInputStream(new File("fingerprint.bmp")));
            statement.setInt(3, obj.getCollaborateur_id());
            statement.setInt(4, obj.getId());
            statement.executeUpdate();
          } catch (SQLException e) {
            Logger.getLogger(EmpreinteDAO.class.getName()).log(Level.SEVERE, (String)null, e);
          } catch (FileNotFoundException ex) {
            Logger.getLogger(EmpreinteDAO.class.getName()).log(Level.SEVERE, (String)null, ex);
          } 
          return obj; //To change body of generated methods, choose Tools | Templates.
          }

    @Override
    public boolean delete(Empreinte obj) {
        try {
            PreparedStatement statement = this.connect.prepareStatement("DELETE FROM empreintes WHERE id=?");
            statement.setInt(1, obj.getId());
            statement.executeUpdate();
            return true;
          } catch (SQLException ex) {
            Logger.getLogger(EmpreinteDAO.class.getName()).log(Level.SEVERE, (String)null, ex);
            return false;
          } //To change body of generated methods, choose Tools | Templates. //To change body of generated methods, choose Tools | Templates.
    }
    
    public boolean deleteById(int id) {
        try {
            PreparedStatement statement = this.connect.prepareStatement("DELETE FROM empreintes WHERE collaborateur_id=?");
            statement.setInt(1, id);
            statement.executeUpdate();
            return true;
          } catch (SQLException ex) {
            Logger.getLogger(EmpreinteDAO.class.getName()).log(Level.SEVERE, (String)null, ex);
            return false;
          } //To change body of generated methods, choose Tools | Templates. //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Empreinte get(int paramInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Empreinte> getAll() {
        ArrayList<Empreinte> empreintes = new ArrayList<>();
        try {
            PreparedStatement statement = this.connect.prepareStatement("SELECT * FROM empreintes");
            statement.setFetchSize(200); // retourne la reponse en paquet de 500
            ResultSet rs = statement.executeQuery();
            while (rs.next())
                empreintes.add(new Empreinte(rs
                .getInt("id"), rs
                .getString("doigt"), rs
                .getInt("collaborateur_id"))); 
        } catch (SQLException e) {
        System.out.println(e.getMessage());
        } 
        return empreintes;
    }
    
    public void getImageEmpreinte(){
        int i=0;
         try {
            PreparedStatement statement = this.connect.prepareStatement("SELECT image FROM empreintes");
            statement.setFetchSize(200); // retourne la reponse en paquet de 500
            ResultSet rs = statement.executeQuery();
            while (rs.next())
                i=i+1; 
                
        } catch (SQLException e) {
        System.out.println(e.getMessage());
        } 
         
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Empreinte getByParams(int id, String doigt){
        try {
            PreparedStatement statement = this.connect.prepareStatement("SELECT * FROM empreintes WHERE collaborateur_id=? AND doigt=?");
            statement.setInt(1, id);
            statement.setString(2, doigt);
            ResultSet rs = statement.executeQuery();
            if (rs.next())
              return new Empreinte(rs
                  .getInt("id"), rs
                  .getString("doigt"), rs
                  .getInt("collaborateur_id")); 
          } catch (SQLException e) {
            System.out.println(e.getMessage());
          } 
          return null;
         }
    
    public Blob getImageByParams(int id, String doigt){
        try {
      PreparedStatement statement = this.connect.prepareStatement("SELECT AES_DECRYPT(image,'key')as blobImg FROM empreintes WHERE collaborateur_id=? AND doigt=?");
      statement.setInt(1, id);
      statement.setString(2, doigt);
      ResultSet rs = statement.executeQuery();
      if (rs.next())
        return rs.getBlob("blobImg"); 
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    } 
    return null;
    }
    
    public List<Empreinte> getAllById(int id){
        ArrayList<Empreinte> empreintes = new ArrayList<>();
        try {
            PreparedStatement statement = this.connect.prepareStatement("SELECT * FROM empreintes WHERE collaborateur_id=?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next())
                empreintes.add(new Empreinte(rs
                .getInt("id"), rs
                .getString("doigt"), rs
              .getInt("collaborateur_id"))); 
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    } 
    return empreintes;
    }
    
    public List<Empreinte> getAllEmpreinte(String matricule){
        ArrayList<Empreinte> empreintes = new ArrayList<>();
        List<Collaborateur> allc = new DAOFactory().getCollaborateurDAO().getBy(matricule);
        try {
            for (int i = 0; i < allc.size(); i++) {
            PreparedStatement statement = this.connect.prepareStatement("SELECT * FROM empreintes WHERE collaborateur_id=?");
            statement.setInt(1, ((Collaborateur)allc.get(i)).getId());
            ResultSet rs = statement.executeQuery();
            while (rs.next())
                empreintes.add(new Empreinte(rs
                .getInt("id"), rs
                .getString("doigt"), rs
                .getInt("collaborateur_id"))); 
      } 
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    } 
    return empreintes;
    }
}
