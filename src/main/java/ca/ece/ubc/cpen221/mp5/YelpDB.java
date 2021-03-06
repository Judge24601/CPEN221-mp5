package ca.ece.ubc.cpen221.mp5;
import java.util.*;
import java.util.concurrent.Future;
import java.io.*;
import javax.json.*;
import javax.json.stream.JsonParsingException;
import javax.swing.JFrame;

import ca.ece.ubc.cpen221.antlr.*;
import org.antlr.v4.gui.Trees;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;


public class YelpDB extends BusinessDB{


	/**
	 * Constructs the database from 3 Json Files
	 * @param restaurantFile - name of JSON file containing restaurant data
	 * @param reviewFile - name of JSON file containing review data
	 * @param userFile - name of JSON file containing user data
	 * @modifies this - creates business/review/user map, and creates the individual
	 * businesses/users/reviews as objects
	 */
	
	public YelpDB(String restaurantFile, String reviewFile, String userFile) throws IOException{  
		super(reviewFile, userFile);

		//parse each file to get list of JSON objects, then store those in a map name (or id?) --> object

		List<JsonObject> temp = new ArrayList<>();
		temp = jsonParse(restaurantFile);
		temp.stream()
			.map(x -> buildBusiness(x))
			.forEach((x -> businesses.put(x.getId(), x)));
		List<JsonObject>temp1 = jsonParse(reviewFile);
		for(JsonObject obj : temp1){
			String review = obj.getString("review_id");
			String businessID = obj.getString("business_id");
			Business currentBus = (Business) businesses.get(businessID);
			this.busLookup.put(review, businessID);
			currentBus.addReview(review, this.reviews.get(review).rating);
		}
	}
	
	/**
	 * Gets JSON details of restaurant with id
	 * @param id
	 * @return JSON details restaurant with matching id, err otherwise
	 */
	public String getRestaurant(String id) {
		try {
			return businesses.get(id.trim()).toString();
		}catch(NullPointerException e) {
			return "ERR: NO_SUCH_RESTAURANT";
		}
	}
	
	/**
	 * Adds a new restaurant to the database
	 * @param id - JSON details of restaurant minus stars, id, etc
	 * @return JSON details of added restaurant with stars, id, etc, error if cannot build
	 */
	public String addRestaurant(String id) {
		Reader strRead = new StringReader(id);
		JsonReader reader = Json.createReader(strRead);
		try {
		Restaurant rest = buildBusiness(reader.readObject());
		this.businesses.put(rest.getId(), rest);
		System.out.println(rest.toString());
		return rest.toString();
		}catch(JsonParsingException | NullPointerException e) {
			return "ERR: INVALID_RESTAURANT_STRING";
		}
	}
	/**
	 * Adds a new review to the database
	 * @param id - JSON details of review & parents minus id
	 * @return JSON details of added review with id
	 */
	public String addReview(String id) {
		Reader strRead = new StringReader(id);
		JsonReader reader = Json.createReader(strRead);
		try {
			JsonObject obj = reader.readObject();
			Review rev = new Review(obj);
			if(users.containsKey(obj.getString("user_id"))) {
				this.userLookup.put(rev.getId(), obj.getString("user_id"));
			}else {
				return "ERR: NO_SUCH_USER";
			}
			if(businesses.containsKey(obj.getString("business_id"))) {
				this.busLookup.put(rev.getId(), obj.getString("business_id"));
			}else {
				return "ERR: NO_SUCH_RESTAURANT";
			}
			this.reviews.put(rev.getId(), rev);
			System.out.println(rev.toString());
			return rev.toString();
		}catch(JsonParsingException | NullPointerException e) {
			return "ERR: INVALID_REVIEW_STRING";
		}
	}
	/**
	 * Returns the Set of Businesses matching the query.
	 * Parses the query with ANTLR, then the class
	 * YelpRecursiveListener does the work for us.
	 * @param queryString - the query
	 * @return the matched set
	 */
	@Override
	public Set<Business> getMatches(String queryString){
		@SuppressWarnings("deprecation")
		CharStream stream = new ANTLRInputStream(queryString);
		YelpLexer lexer = new YelpLexer(stream);
		TokenStream tokens = new CommonTokenStream(lexer);
		YelpParser parser = new YelpParser(tokens);
		ParseTree tree = parser.root();
		int numErrors = parser.getNumberOfSyntaxErrors();
		if(numErrors > 0) {
			Set<Business> badSet = new HashSet<Business>();
			badSet.add(null);
			return badSet;
		}
		ParseTreeWalker walker = new ParseTreeWalker();
		YelpRecursiveListener listener = new YelpRecursiveListener();
		listener.database = this;
		walker.walk(listener, tree);
		//Trees.inspect(tree, parser);
		return listener.getResult();
	}
	/**
	 * Adds a new user to the database
	 * @param id - JSON details of user minus reviewCount, id, etc
	 * @return JSON details of added user with reviewCount, id, etc
	 */
	public String addUser(String id) {
		Reader strRead = new StringReader(id);
		System.out.println(id);
		JsonReader reader = Json.createReader(strRead);
		try {
			User use = new User(reader.readObject());
			this.users.put(use.getId(), use);
			System.out.println(use.toString());
			return use.toString();
		}catch(JsonParsingException| NullPointerException e) {
			return "ERR: INVALID_USER_STRING";
		}
	}
	/**
	 * Calls constructor
	 * not really necessary at the moment but could be useful for expansion
	 * @param obj - JSON object
	 * @return the built Restaurant
	 */
	private Restaurant buildBusiness(JsonObject obj) {
		return new Restaurant(obj);
	}	
	
}
