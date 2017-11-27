package ca.ece.ubc.cpen221.mp5;
import java.util.*;
import java.io.*;
import java.util.function.ToDoubleBiFunction;
import javax.json.*;

public class YelpDB<Business> implements MP5Db{

	public static void main(String [] args){
		//YelpDB<Restaurant> myDB = new YelpDB<>("restaurants.json", "reviews.json", "users.json");

		try{
			List<JsonObject> myList = jsonParse("data/restaurants.json");
			for(JsonObject obj : myList){
				System.out.println(obj);
			}
		}catch (IOException e){
			System.out.println("whoopsie");
		}

		// new StringReader( docScan.nextLine() ) ;
	}
	
	public YelpDB(String restaurantFile, String reviewFile, String userFile){
		
	}
	
	private List<String> specialTypes = Arrays.asList("open", "business_id", "name");
	
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
