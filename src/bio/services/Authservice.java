/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bio.services;
import bio.models.User;
/**
 *
 * @author Diamondra
 */
public class Authservice {
    private String result;
    private User user;
    
    public Authservice(String res){
        this.result = res;
    }
    
    public Authservice(String res, User newuser){
        this.result = res;
        this.user = newuser;
    }
    
    public String getResult(){
        return this.result;
    }
    
    public void setResult(String res){
        this.result = res;
    }
    
    public User getUser(){
        return this.user;
    }
    
    public void setUser(User newuser){
        this.user= newuser;
    }
    
}
