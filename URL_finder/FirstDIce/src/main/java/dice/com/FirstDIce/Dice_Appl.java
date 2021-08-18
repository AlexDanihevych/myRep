package dice.com.FirstDIce;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.json.JSONArray;
import org.json.JSONObject;


public class Dice_Appl {
	
	public static void main(String[] args) throws Exception {
		
		DataHandler dh = new DataHandler("https://9gag.com/v1/group-posts/group/animals/type/hot/");
		dh.HttpAccess();
		JSONArray urlsArray = dh.getParsedData();
		DB.connectToDB();
		DB.createTable();
		
		for (int i = 0; i <10; i++) {
			JSONObject object = urlsArray.getJSONObject(i);
			String url = object.getString("url");		
			DB.addURLtoDB(url);
		}
		
		System.out.println("Some data was filled in DB...");
		DB.getAllUrls();
		
		
		try {
		while(true) {
		
		System.out.println("Enter the number of post which you wasnt to see, if you want to close app type '0' ");
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		int URLidx = Integer.parseInt(br.readLine());
		
		if(URLidx ==0) {
		System.exit(0);
		}else{
			String url = DB.getUrlAt(URLidx);
			Browser.openUrl(url);
		}
		}
		}catch (NumberFormatException e) {
		System.out.println("Enter the number, not a letter");
		}
		
		
	}

}
