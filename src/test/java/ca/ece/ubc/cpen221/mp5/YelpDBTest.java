package ca.ece.ubc.cpen221.mp5;

import org.junit.Test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Assert;
public class YelpDBTest {
	@Test
	public void initTest() {
		try {
			MP5Db<Business> db = new YelpDB("data/restaurants.json", "data/reviews.json", "data/users.json");
			}catch(IOException e) {
				fail();
		}
	}
}
