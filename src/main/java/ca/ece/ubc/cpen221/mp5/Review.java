package ca.ece.ubc.cpen221.mp5;

import javax.json.JsonObject;

public class Review {
	public String idStr;
	public String text;
	public int rating;
	public String businessId;
	public String userId;
	public String date;
	
	public Review(JsonObject info) {
		this.idStr = info.getString("review_id");
		this.text = info.getString("text");
		this.rating = info.getInt("stars");
		this.date = info.getString("date");
		this.userId = info.getString("user_id");
		this.businessId = info.getString("business_id");
	}
}
