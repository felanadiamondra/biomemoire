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
public class AuthUser {
    private String adresse_mac;
    private String codeCms;
    private String username;
    
    private static AuthUser instance;

    public static AuthUser getAuthUser() {
       if (instance == null){
           instance = new AuthUser(); 
       }
         
       return instance;
    }
    public static String getClasseName() {
        return "AuthUser";
    }
    public void setAuthUser(AuthUser auth) {
        instance = auth;
    }
    public AuthUser(){};
    
    public AuthUser(String adresse, String username, String codecms){
        this.adresse_mac = adresse;
        this.username = username;
        this.codeCms = codecms;
    }
  
    public String getCodecms(){
        return this.codeCms;
    }
  
    public void setCodeCms(String code){
        this.codeCms = code;
    }
  
    public String getUsername(){
        return this.username;
    }
  
    public void setUsername(String user){
        this.username = user;
    }
  
    public String getAdresseId(){
        return this.adresse_mac;
    }
  
    public void setAdresseId(String adresse){
        this.adresse_mac = adresse;
    }
}
