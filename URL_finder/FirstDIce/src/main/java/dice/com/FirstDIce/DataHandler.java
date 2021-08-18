package dice.com.FirstDIce;

import java.io.IOException;

import org.apache.hc.core5.http.HttpHeaders;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;

public class DataHandler {
	
	
	String data;
	String targetURL;
	
	
	public DataHandler(String targetURL) {
		this.targetURL = targetURL;
	}

	public  void HttpAccess() throws ClientProtocolException, IOException {
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
    	
        HttpGet request = new HttpGet(targetURL); // https://9gag.com/v1/group-posts/group/animals/type/hot/
        request.addHeader(HttpHeaders.USER_AGENT, "Googlebot");
        CloseableHttpResponse response = httpClient.execute(request);
        
        data = new BasicResponseHandler().handleResponse((org.apache.http.HttpResponse) response);
		
	}

	public  JSONArray getParsedData() throws Exception {
	   
		JSONObject jo =  new JSONObject(data);
		JSONObject data = (JSONObject) jo.get("data");
		JSONArray postsArray = (JSONArray) data.get("posts");
	 
	    return postsArray;
	    
	}
}
