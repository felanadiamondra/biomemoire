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
public class Rajout {

  private int id;
  
  private int id_empreintes;
  
  private int id_authentifications;
  
  private int score;
  
  private String type_action;
  
  public Rajout(int n, int m, int r, int s, String ac) {
    this.id = n;
    this.id_empreintes = m;
    this.id_authentifications = r;
    this.score = s;
    this.type_action = ac;
  }
  
  public Rajout(int m, int r, int s, String ac) {
    this.id_empreintes = m;
    this.id_authentifications = r;
    this.score = s;
    this.type_action = ac;
  }
  public int getId() {
    return this.id;
  }
  
  public void setId(int id) {
    this.id = id;
  }
  
  public String getType_action() {
    return this.type_action;
  }
  
  public void setType_action(String id) {
    this.type_action = id;
  }
  
  public int getScore() {
    return this.score;
  }
  
  public void setScore(int id) {
    this.score = id;
  }
  
  public int getIdEmpreintes() {
    return this.id_empreintes;
  }
  
  public void setIdEmpreintes(int nom_d_utilisateur) {
    this.id_empreintes = nom_d_utilisateur;
  }
  
  public int getIdAuth() {
    return this.id_authentifications;
  }
  
  public void setIdAuth(int mot_de_passe) {
    this.id_authentifications = mot_de_passe;
  }
}
