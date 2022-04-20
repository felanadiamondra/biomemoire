/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bio.api;
import bio.services.Authservice;
import java.util.HashMap;
import java.util.Iterator;
import bio.services.Authservice;
import bio.models.User;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


/**
 *
 * @author Diamondra
 */
public class UserConnection {
    private static List<String> auth = new ArrayList<String>();
    
    public static Authservice authUser(String username, String password)
    {
        Authservice authresponse =null;
        try{
            URL url= new URL("http://192.168.222.17/api/login?username="+username+"&password="+password);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.connect();
            int responsecode = conn.getResponseCode();
            String  inline ="";
            String res = "";
            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            }
            BufferedReader br= new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while((inline = br.readLine()) != null){
                res= res + inline;
            }
            br.close();
           JSONParser parse = new JSONParser();
           JSONObject user_obj = (JSONObject)parse.parse(res);
           
           String auth_response= user_obj.get("success").toString().trim();
           if(auth_response.equals("true")){
               String user_info = user_obj.get("user").toString().trim();
               JSONObject all_user_info = (JSONObject)parse.parse(user_info);
               String userid = all_user_info.get("username").toString().trim();
               String codeCms = all_user_info.get("codeCms").toString().trim();
               String role = all_user_info.get("role").toString().trim();
               User newuser= new User(userid, codeCms, role);  
               authresponse= new Authservice(auth_response,newuser );
           }
           else{
               authresponse= new Authservice(auth_response);
           }
          // System.out.println(auth_response);
           
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return authresponse;
    }    
    
   

}
