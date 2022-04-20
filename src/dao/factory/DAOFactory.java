/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.factory;
import bio.dao.EmpreinteDAO;
import bio.dao.CollaborateurDAO;
import bio.dao.RajoutDAO;
import bio.dao.AuthentificationDAO;
import bio.dao.AdresseDAO;
import bio.dao.Identification1DAO;
/**
 *
 * @author Diamondra
 */
public class DAOFactory {
  
  
  public static CollaborateurDAO getCollaborateurDAO() {
    return new CollaborateurDAO();
  }
  
  public static EmpreinteDAO getEmpreinteDAO() {
    return new EmpreinteDAO();
  }
  
  public static RajoutDAO getRajoutDAO(){
      return new RajoutDAO();
  }
  
  public static  AuthentificationDAO getAuthentificationDAO(){
      return new AuthentificationDAO();
  }
  
  public static AdresseDAO getAdresseDao(){
      return new AdresseDAO();
  }
  
   public static Identification1DAO getIdentificationDAO(){
      return new Identification1DAO();
  }
}
