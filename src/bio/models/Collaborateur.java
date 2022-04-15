/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bio.models;

/**
 *
 * @author Diamondra
 */
public class Collaborateur {
    private String matricule;
    private String type;
    private String datenaiss;
    private String name;
    private int id;
    
    public Collaborateur(String matricule,String name, String type, String datenaiss){
        this.matricule = matricule;
        this.type = type;
        this.datenaiss = datenaiss;
        this.name = name;
    }
     public Collaborateur(int id,String matricule,String name, String type, String datenaiss){
        this.id = id;
        this.matricule = matricule;
        this.type = type;
        this.datenaiss = datenaiss;
        this.name = name;
    }
    public String getMatricule(){
        return this.matricule;
    }
    
    public void setMatricule(String matr){
        this.matricule = matr;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getType(){
        return this.type;
    }
    
    public void setType(String type){
        this.type = type;
    }
    
    public String getDateNaiss(){
        return this.datenaiss;
    }
    
    public void setDateNaiss(String datenaiss){
        this.datenaiss = datenaiss;
    }
    
    public int getId() {
    return this.id;
  }
  
  public void setId(int id) {
    this.id = id;
  }
}
