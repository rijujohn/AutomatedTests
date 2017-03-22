package com.uktech.test.prepare;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.uktech.common.TestBase;
import com.uktech.helpers.excelReader.ODSReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

public class PrepareEnvForTests extends TestBase {
////"https://t2s-api.t2scdn.com/settings";
    private static final String BOT_URI = "https://peaceful-beach-96329.herokuapp.com/create/production";
    private static final String BASE_URI = "https://api.touch2success.com/settings"; 
    private static final String JSON_BODY_PATH = "src//main//resources//Test2.json";
    private static final String TEST_PROPERTIES = "src//main//resources//test.properties";
    private static final String TOKEN_KEY = "?api_token=";
    private static final String USERS_LEVEL = "users";
    private static final String TOKENS_LEVEL = "tokens";
    private static final String USER = "user";
    private static final String PASS = "pass";
    private static final String DOMAIN = "domain";
    private static final int TEN_SECONDS = 10000;
    private static final int TOKEN_ROW = 1;
    private static final int URL_ROW = 3;
    private static final int DOMAIN_ROW = 4;
    private static final int HTTP_OK = 200;
    private static String oFlag = "No";
    
    ODSReader oDs;
    File inputFile = new File(inputFileString);

    private static String rampUpProdEnv() {
        String token = null;
        String domain = null;
        try {
            Properties props = new Properties();
            JSONObject json = readJsonFromUrl(BOT_URI);
            JSONArray users = json.getJSONArray(USERS_LEVEL);
            JSONObject userEntity = (JSONObject) users.get(0);
            JSONArray tokens = json.getJSONArray(TOKENS_LEVEL);
            domain = json.getString(DOMAIN); //  .getJSONArray(DOMAIN);
            props.setProperty("domain", json.get(DOMAIN).toString());
            props.setProperty("user", userEntity.get(USER).toString());
            props.setProperty("pass", userEntity.get(PASS).toString());
            token = tokens.get(0).toString();
            File f = new File(TEST_PROPERTIES);
            OutputStream out = new FileOutputStream(f);
            props.store(out, "Made properties active for usage");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return token+";"+domain;
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    private static JSONObject readJsonFromUrl(String url) throws IOException {
        try (InputStream is = new URL(url).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            return new JSONObject(jsonText);
        }
    }

   // @Test
    public String prepare(String token,String JSONBodyPath) {
        String createDataScratch = "YES";
        
    	try {
        if (createDataScratch.equals("YES") && oFlag.equals("No"))	
        {
            String tokenDomain = rampUpProdEnv();
            String[] tokenDomainSplit = tokenDomain.split(";");
            token = tokenDomainSplit[0];
            String domainCustomer  = tokenDomainSplit[1];
            String domainurl  = "http://"+tokenDomainSplit[1];
            Thread.sleep(TEN_SECONDS);
            // Getting ID for API to execute PUT
            oDs = new ODSReader();
            oDs.setODSValue(inputFile, "Contact", token, TOKEN_ROW);
            oDs.setODSValue(inputFile, "Order", token, TOKEN_ROW);
            oDs.setODSValue(inputFile, "Contact", domainurl, URL_ROW);
            oDs.setODSValue(inputFile, "Order", domainurl, URL_ROW);
            oDs.setODSValue(inputFile, "Contact", domainCustomer, DOMAIN_ROW);
            oFlag = "Yes";
        }   
            JSONObject json = readJsonFromUrl(BASE_URI + TOKEN_KEY + token);
            JSONArray dataLevel = json.getJSONArray("data");
            JSONObject dataLevelJson = (JSONObject) dataLevel.get(0);
            String id = dataLevelJson.get("id").toString();

            // Putting the correct JSON file to the API
            Client putClient = Client.create();
            WebResource putWebResource = putClient.resource(BASE_URI + "/" + id + TOKEN_KEY + token);
            String putJSON = new String(Files.readAllBytes(Paths.get(JSONBodyPath)));
            ClientResponse putResponse = putWebResource.accept(APPLICATION_JSON)
                    .type(APPLICATION_JSON).put(ClientResponse.class, putJSON);

            if (putResponse.getStatus() != HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + putResponse.getStatus());
            }
                else
                {	
                return "Success";
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
            return "Error";
        }
    }
}