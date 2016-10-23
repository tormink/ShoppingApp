package com.lntormin.javaee.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author lntormin
 */
public class MyRestApp {
    public static void searchById(int id){
        try{
            String baseURL = "http://localhost:8080/FrontView-war/UserManagement/users/";
            URL url = new URL(baseURL+"user/"+id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/xml");
            if(connection.getResponseCode()!=200){
                throw new RuntimeException("Failure : HTTP error code: "+connection.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String output;
            System.out.println("Output from server ...");
            while((output=br.readLine())!=null){
                System.out.println(output);
            }
            connection.disconnect();
        } catch(MalformedURLException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }
        
    }
    public static void main(String[] args) {
        searchById(1);
    }
}
