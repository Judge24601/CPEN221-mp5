package ca.ece.ubc.cpen221.mp5;

import javax.json.JsonObject;
import javax.print.attribute.standard.MediaSize.Other;

public class Review {
	public String id;
	public String text;
	public double rating;
	
	public Review(JsonObject info) {
		this.id = info.getString("review_id");
		this.text = info.getString("text");
		this.rating = info.getJsonNumber("stars").doubleValue();
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
