package ca.ece.ubc.cpen221.mp5;
import java.util.*;
import java.io.*;
import java.util.function.ToDoubleBiFunction;
import javax.json.*;

public class YelpDB<Business> implements MP5Db{

	private Map<String, Business> businesses;
	private Map<String, User> users;
	private Map<String, Review> reviews;

	
	public YelpDB(String restaurantFile, String reviewFile, String userFile) throws IOException{
		this.businesses = new HashMap<>();
		this.reviews = new HashMap<>();
		this.users = new HashMap<>();

		//parse each file to get list of JSON objects, then store those in a map name (or id?) --> object

		List<JsonObject> temp = new ArrayList<>();
		temp = jsonParse(restaurantFile);
		for(JsonObject obj : temp){
			Business business =  new Restaurant(obj);
			businesses.put(obj.getString("business_id"), business);
		}

		temp = jsonParse(reviewFile);
		for(JsonObject obj : temp){
			//reviews.put(obj.getString("review_id"), obj);
		}

		temp = jsonParse(userFile);
		for(JsonObject obj : temp){
			//users.put(obj.getString("user_id"), obj);
		}
	}
	
	//private List<String> specialTypes = Arrays.asList("open", "business_id", "name");
	
	private static List<JsonObject> jsonParse(String fileName) throws IOException {
		Scanner sc = new Scanner(new FileInputStream(fileName));
		JsonReader readJson;
		List<JsonObject> jsonList = new ArrayList<>();

		while(sc.hasNext()){
			readJson = Json.createReader(new StringReader(sc.nextLine()));
			JsonObject temp = readJson.readObject();
			jsonList.add(temp);
		}

		return jsonList;
	}
	
	public Set<Business> getMatches(String queryString){
		return null; //Change this
	}
	
	public String kMeansClusters_json(int k) {
		return null; //Change this
	}
	
	public ToDoubleBiFunction<MP5Db<Business>, String> getPredictorFunction(String user){
		return null; //Change this
	}
}
