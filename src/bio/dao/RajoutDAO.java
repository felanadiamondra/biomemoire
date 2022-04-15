/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bio.dao;
import bio.models.Rajout;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diamondra
 */
public class RajoutDAO extends IDAO<Rajout>{
    public Rajout create(Rajout obj) {
    try {
        PreparedStatement statement = this.connect.prepareStatement("INSERT INTO responsable_ajout(id_empreintes, id_authentifications,score,type_action) VALUES(?, ?, ?, ?)", 1);
      statement.setInt(1, obj.getIdEmpreintes());
      statement.setInt(2, obj.getIdAuth());
      statement.setInt(3, obj.getScore());
      statement.setString(4, obj.getType_action());
      statement.executeUpdate();
      ResultSet rs = statement.getGeneratedKeys();
      if (rs.next())
        obj.setId(rs.getInt(1)); 
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    } 
    return obj;
  }

    public Rajout update(Rajout obj) {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Rajout obj) {
        try {
            PreparedStatement statement = this.connect.prepareStatement("DELETE FROM responsable_ajout WHERE id=?");
            statement.setInt(1, obj.getId());
            statement.executeUpdate();
            return true;
          } catch (SQLException ex) {
            Logger.getLogger(RajoutDAO.class.getName()).log(Level.SEVERE, (String)null, ex);
            return false;
          } //To cha //To change body of generated methods, choose Tools | Templates. //To cha //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rajout get(int idEmp) {
         try {
            PreparedStatement statement = this.connect.prepareStatement("SELECT * FROM responsable_ajout WHERE id_empreintes=?");
            statement.setInt(1, idEmp);
            ResultSet rs = statement.executeQuery();
            if (rs.next())
              return new Rajout(rs
                  .getInt("id"), rs
                  .getInt("id_empreintes"), rs
                  .getInt("id_authentifications"), rs
                  .getInt("score"), rs
                    .getString("type_action")); 
          } catch (SQLException e) {
            System.out.println(e.getMessage());
          } 
          return null;
    }

    @Override
    public List<Rajout> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
