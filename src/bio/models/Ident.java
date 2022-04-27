/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bio.models;

/**
 *
 * @author HP
 */
public class Ident {
  private int id_auth;
  
  private int id_empreintes;
  
  private String nom_d_utilisateur;
  private String adresse_mac;
  private int score;
  private String doigt;
  private int collabid;
  private String codecms;
  private int matricule;
  private String date;
  
  public Ident(String adressemac, int auth, int empreinte, String user, int score, String doigt, int collab, String code, int matr, String date) {
    this.adresse_mac = adressemac;
    this.id_auth = auth;
    this.id_empreintes = empreinte;
    this.nom_d_utilisateur = user;
    this.score = score;
    this.doigt = doigt;
    this.collabid = collab;
    this.codecms = code;
    this.matricule = matr;
    this.date = date;
  }
  
  
  public int getId_auth() {
    return this.id_auth;
  }
  
  public void setId_auth(int id_auth) {
    this.id_auth = id_auth;
  }
  
  public int getEmpreintes() {
    return this.id_empreintes;
  }
  
  public void setEmpreintes(int emp) {
    this.id_empreintes = emp;
  }
  
  public String getUtilisateur() {
    return this.nom_d_utilisateur;
  }
  
  public void setUtilisateur(String user) {
    this.nom_d_utilisateur = user;
  }
  
  public String getAdresseMac(){
      return this.adresse_mac;
  }
  
  public void setAdresseMac(String adrmac){
      this.adresse_mac = adrmac;
  }
  
  public int getScore(){
      return this.score;
  }
  
  public void setScore(int score){
      this.score = score;
  }
  
  public String getDoigt(){
      return this.doigt;
  }
  
  public void setDoigt(String doigt){
      this.doigt = doigt;
  }
  public int getCollabId(){
      return this.collabid;
  }
  public String getCodecms(){
      return this.codecms;
  }
  public int getMatricule(){
      return this.matricule;
  }
  
  public String getDateCreated(){
      return this.date;
  }
  
}
