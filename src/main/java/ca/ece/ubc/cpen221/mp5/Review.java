package ca.ece.ubc.cpen221.mp5;

import javax.json.JsonObject;

public class Review {
	public String id;
	public String text;
	public int rating;
	
	public Review(JsonObject info) {
		this.id = info.getString("review_id");
		this.text = info.getString("text");
		this.rating = info.getInt("stars");
	}
}
