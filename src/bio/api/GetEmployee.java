/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bio.api;
import bio.models.Collaborateur;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Diamondra
 */
public class GetEmployee {
    public static List<Collaborateur> GetEmplArray(String id) {
      ArrayList collaborateurs = new ArrayList();

      try {
         URL url = new URL("http://192.168.222.17/api/employe/jde?q=" + id);
         HttpURLConnection conn = (HttpURLConnection)url.openConnection();
         conn.setRequestMethod("GET");
         conn.connect();
         int responsecode = conn.getResponseCode();
         String inline = "";
         if (responsecode != 200) {
            throw new RuntimeException("HttpResponseCode: " + responsecode);
         }

         Scanner scanner;
         for(scanner = new Scanner(url.openStream()); scanner.hasNext(); inline = inline + scanner.nextLine()) {
         }

         scanner.close();
         JSONParser parse = new JSONParser();
         JSONObject data_obj = (JSONObject)parse.parse(inline);
         JSONArray array_obj = (JSONArray)data_obj.get("results");

         for(int i = 0; i < array_obj.size(); ++i) {
            JSONObject objectResult = (JSONObject)array_obj.get(i);
            collaborateurs.add(new Collaborateur(objectResult.get("matricule").toString().trim(), objectResult.get("nom").toString().trim(),objectResult.get("typepat").toString().trim(), objectResult.get("datenais").toString().trim()));
         }
      } catch (Exception var11) {
          JOptionPane.showMessageDialog(null, var11.getMessage());
      }

      return collaborateurs;
   }
}
