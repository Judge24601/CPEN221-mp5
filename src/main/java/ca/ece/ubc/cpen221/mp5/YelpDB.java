package ca.ece.ubc.cpen221.mp5;
import java.util.*;
import java.io.*;
import java.util.function.ToDoubleBiFunction;
import javax.json.*;

public class YelpDB<Business> implements MP5Db{

	public static void main(String [] args){
		//YelpDB<Restaurant> myDB = new YelpDB<>("restaurants.json", "reviews.json", "users.json");

		try{
			System.out.println(jsonParse("data/restaurants.json"));
		}catch (IOException e){
			System.out.println("whoopsie");
		}

	}
	
	public YelpDB(String restaurantFile, String reviewFile, String userFile){
		
	}
	
	private List<String> specialTypes = Arrays.asList("open", "business_id", "name");
	
	private static Map<String, String> jsonParse(String fileName) throws IOException {
		Scanner sc = new Scanner(new FileInputStream(fileName));
		JsonReader
		Map<String, String> typeResultMap = new HashMap<String, String>();
		while(sc.hasNextLine()) {
			String line = sc.nextLine();
			String[] split = line.replaceAll("{}", "").split(",");
			for(int i = 0; i < split.length; i+=2) {
				typeResultMap.put(split[i], split[i+1]);
			}
		}
		return typeResultMap;
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
