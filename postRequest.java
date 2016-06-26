package gmap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class gmap {

	public static void main(String[] args) {

		String payload = "{'latitude': 123,'logitude': 345,'date': 765-56-45}";
		String requestUrl = "https://mapdata.herokuapp.com/map";
		sendPostRequest(requestUrl, payload);

	}

	public static String sendPostRequest(String requestUrl, String payload) {
		try{
		URL url = new URL(requestUrl);
		  HttpURLConnection conn =
		      (HttpURLConnection) url.openConnection();
		  conn.setRequestMethod("POST");
		  conn.setDoOutput(true);
		  conn.setDoInput(true);
		  conn.setUseCaches(false);
		  conn.setAllowUserInteraction(false);
		  conn.setRequestProperty("Content-Type",
		      "application/x-www-form-urlencoded");

		  // Create the form content
		  OutputStream out = conn.getOutputStream();
		  Writer writer = new OutputStreamWriter(out, "UTF-8");
		  
		  
          
		    writer.write("lat");
		    writer.write("=");
		    writer.write(URLEncoder.encode("2134", "UTF-8"));
		    writer.write("&");
		    writer.write("log");
		    writer.write("=");
		    writer.write(URLEncoder.encode("5675", "UTF-8"));
		    writer.write("&");
		    writer.write("date");
		    writer.write("=");
		    writer.write(URLEncoder.encode("5-6-75", "UTF-8"));
		    writer.write("&");
		    
		  
		  writer.close();
		  out.close();

		  if (conn.getResponseCode() != 200) {
		    throw new IOException(conn.getResponseMessage());
		  }

		  // Buffer the result into a string
		  BufferedReader rd = new BufferedReader(
		      new InputStreamReader(conn.getInputStream()));
		  StringBuilder sb = new StringBuilder();
		  String line;
		  while ((line = rd.readLine()) != null) {
		    sb.append(line);
		  }
		  rd.close();

		  conn.disconnect();
		
		  return sb.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
		}
		return payload;
		}}