package ca.ece.ubc.cpen221.mp5;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
/**
 * Rep Invariant:
 * Id cannot be null
 * Text cannot be null
 * Rating cannot be null
 * 
 *  Abstraction Function:
 * Represents a review as an id, a text, and a rating
 *
 *
 */
public class Review {
	private static Long baseId = 0l;
	private String id;
	public String text;
	public final double rating;
	
	public Review(JsonObject info) throws NullPointerException{
		try {
			this.id = info.getString("review_id");
		}catch(NullPointerException e) {
			System.out.println("added");
			this.id = baseId.toString();
			baseId++;
		}
		this.text = info.getString("text");
		this.rating = info.getJsonNumber("stars").doubleValue();
	}
	
	public String getId() {
		return new String(this.id);
	}
	
	@Override
	public String toString() {
		JsonObjectBuilder build = Json.createObjectBuilder();
		build.add("review_id", this.id);
		build.add("text", this.text);
		build.add("rating", this.rating);
		return build.build().toString();
	}
	
	@Override
	public boolean equals(Object other) {
		if(other instanceof Review) {
			if(((Review) other).id.equals(this.id)) {
				return true;
			}
		}
		return false;
	}
}
