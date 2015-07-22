import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by andrew.g.wardrobe on 22/07/2015.
 */
public class Helpers {
    //Helpers
    public static String contentAsString(HttpURLConnection con) throws Exception{
        int responseCode = con.getResponseCode();
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

    public static String errAsString(HttpURLConnection con) throws Exception {
        int responseCode = con.getResponseCode();
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getErrorStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

    public static HttpURLConnection postXml(String url, String data) throws Exception{
        return post(url, data, "application/xml");
    }

    public static HttpURLConnection postJson(String url, String data) throws Exception{
        return post(url,data,"application/json");
    }

    public static HttpURLConnection post(String url, String data, String contentType) throws Exception{
        URL uri = new URL(url);
        HttpURLConnection con = (HttpURLConnection) uri.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type",contentType);
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(data);
        wr.flush();
        wr.close();



        return con;


    }
}
