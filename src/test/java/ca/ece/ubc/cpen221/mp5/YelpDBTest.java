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

	@Test
	public void kMeansTest() {
		try {
			MP5Db<Business> db = new YelpDB("data/restaurants.json", "data/reviews.json", "data/users.json");
			System.out.println(db.kMeansClusters_json(3));
		}catch(IOException e) {
			fail();
		}
	}
	@Test
	public void baseGetMatches() throws InterruptedException {
		try {
			MP5Db<Business> db = new YelpDB("data/restaurants.json", "data/reviews.json", "data/users.json");
			assertFalse( db.getMatches("in(Telegraph Ave) && (category(Chinese) || category(Italian)) && price <= 2").isEmpty());
			assertFalse( db.getMatches("name(Happy Valley) && (category(Chinese) || category(Italian)) && rating >= 3").isEmpty());
		}catch(IOException e) {
			fail();
		}
	}
	@Test
	public void errorGetMatches() throws InterruptedException {
		try {
			MP5Db<Business> db = new YelpDB("data/restaurants.json", "data/reviews.json", "data/users.json");
			assertTrue(db.getMatches("in(Telegraph Ave) |||| && (category(Chinese) || category(Italian)) && price <= 2").contains(null));
		}catch(IOException e) {
			fail();
		}
	}
	
	@Test
	public void getNoMatches() throws InterruptedException {
		try {
			MP5Db<Business> db = new YelpDB("data/restaurants.json", "data/reviews.json", "data/users.json");
			assertTrue(db.getMatches("in(SathishLand) && (category(Chinese) || category(Italian)) && price <= 2").isEmpty());
		}catch(IOException e) {
			fail();
		}
	}
}
