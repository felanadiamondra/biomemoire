/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bio.dao;
import bio.services.*;
import java.util.List;
import bio.jdbc.DatabaseConnection;
import bio.jdbc.LocalDatabaseConnection;
import java.sql.Connection;

/**
 *
 * @author Diamondra
 * @param <P>
 */
public abstract class IDAO<P> {
  public Connection connect= LocalDatabaseConnection.getConnection();

  public abstract P create(P paramP);
  
  public abstract P update(P paramP);
  
  public abstract boolean delete(P paramP);
  
  public abstract P get(int paramInt);
  
  public abstract List<P> getAll();
  
  public abstract int size();
}
