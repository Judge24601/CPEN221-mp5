package ca.ece.ubc.cpen221.mp5;

import static org.junit.Assert.*;

import java.net.BindException;
import org.junit.Test;

public class ServerTest {
	private static boolean started = false;
	
	@Test
	public void addUserSuccess(){
		String result = tester("ADDUSER {\"name\":\"Hi\"}");
		assertTrue(result.contains("\"name\":\"Hi\""));
	}
	
	@Test
	public void addUserFailure() {
		String result = tester("ADDUSER no");
		assertEquals("ERR: INVALID_USER_STRING", result);
	}
	
	@Test
	public void addRestaurantSuccess() {
		String result = tester("ADDRESTAURANT {\"open\": true, \"url\": \"http://www.yelp.com/biz/chianglai-berkeley\", \"longitude\": -122.2598181, \"neighborhoods\": [\"UC Campus Area\"], \"name\": \"ayyy\", \"categories\": [\"Thai\", \"Restaurants\"], \"state\": \"CA\", \"type\": \"business\", \"city\": \"Berkeley\", \"full_address\": \"2505 Hearst Ave\\nUC Campus Area\\nBerkeley, CA 94701\", \"photo_url\": \"http://s3-media3.ak.yelpcdn.com/bphoto/9fnX3IDOYN_ApzBZg3u0UA/ms.jpg\", \"schools\": [\"University of California at Berkeley\"], \"latitude\": 37.8751965, \"price\": 2}");
		assertTrue(result.contains("\"name\":\"ayyy\""));
	}
	
	@Test
	public void addRestaurantFailure() {
		String result = tester("ADDRESTAURANT {\"name\": \"john\"}");
		assertEquals("ERR: INVALID_RESTAURANT_STRING", result);
	}
	
	@Test
	public void addReviewSuccess() {
		String result = tester("ADDREVIEW {\"text\": \"yoo this place suuccks\", \"stars\": 5, \"user_id\": \"-w8H1G9raUC0Gg_zvlJDwg\", \"business_id\": \"XD5ybqI0BHcTj5cLQyIPLA\"}");
		assertTrue(result.contains("text\":\"yoo this place suuccks\""));
	}
	
	@Test
	public void addReviewUserFailure() {
		String result = tester("ADDREVIEW {\"text\": \"yoo this place suuccks\", \"stars\": 5, \"user_id\": \"none\", \"business_id\": \"XD5ybqI0BHcTj5cLQyIPLA\"}");
		System.out.println(result);
		assertEquals("ERR: NO_SUCH_USER", result);
	}
	
	@Test
	public void addReviewRestaurantFailure() {
		String result = tester("ADDREVIEW {\"text\": \"yoo this place suuccks\", \"stars\": 5, \"user_id\": \"-w8H1G9raUC0Gg_zvlJDwg\", \"business_id\": \"none\"}");
		System.out.println(result);
		assertEquals("ERR: NO_SUCH_RESTAURANT", result);
	}
	
	@Test
	public void addReviewOtherFailure() {
		String result = tester("ADDREVIEW {\"yo\"}");
		assertEquals("ERR: INVALID_REVIEW_STRING", result);
	}

	@Test
	public void getRestauarantSuccess() {
		String result = tester("GETRESTAURANT HXni0_SFPT1jAoH-Sm78Jg");
		assertTrue(result.contains("\"name\":\"Alborz\""));
	}
	
	@Test
	public void getRestauarantFailure() {
		String result = tester("GETRESTAURANT no");
		assertEquals("ERR: NO_SUCH_RESTAURANT", result);
	}
	
	private String tester(String input) {
		String result;
		if(!started) {
			Thread server = new Thread(new Runnable(){
				public void run() {
					try {
						YelpDBServer.test();
					}catch(BindException e) {
						
					}				
				}
			});
			started = true;
			server.start();
		}
		result = YelpDBClient.tester(input);
		return result;
	}
}
