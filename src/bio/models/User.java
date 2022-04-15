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
public class User {
    private int id;
    private String username;
    private String motdepasse;
    private String password;
    private String codecms;
    private String role;
    
    public User(){}
    
    public User(String username,String mdp,String role){
        this.username = username;
        this.motdepasse = mdp;
        this.role = role; 
    }
    
    public User(int id, String username,String mdp,String role){
        this.username = username;
        this.motdepasse = mdp;
        this.role = role;  
        this.id = id;
    }
     public User(int id, String username,String mdp,String codecms, String role){
        this.username = username;
        this.motdepasse = mdp;
        this.role = role;  
        this.codecms = codecms;
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public String getUsername(){
        return this.username;
    }
    
    public void setUsername(String username){
        this.username = username;
    }
       
    public String getPassword(){
        return this.password;
    }
    
    public void setPassword(String pass){
        this.password = pass;
    }
 
    public String getRole(){
        return this.role;
    }
    
    public void setRole(String role){
        this.role= role;
    }
    
    public static String getClassName(){
            return "Utilisateur";
    }
}
