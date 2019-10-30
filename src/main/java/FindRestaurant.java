import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class FindRestaurant {
	private ObjectMapper objectMapper = new ObjectMapper ();
	private List<Restaurant> restaurantList = new ArrayList<> ();
	private List<Restaurant> sortRestaurantList = new ArrayList<> ();

	/**
	 * Method to check open restaurant at current time
	 * - Finds open restaurants from the json obtained from API
	 * - Calls displayRestaurant method to  display open restaurants
	 */
	public void findOpenRestaurants (String json) {
		String startTime = null;
		String endTime = null;
		try {
			//Reads from json string and maps to a readable json tree
			JsonNode payloadJSON = objectMapper.readTree (json);
			// Gets current time
			Calendar rightNow = Calendar.getInstance ();
			int currentHour = rightNow.get (Calendar.HOUR_OF_DAY);

			//Checks currently opened restaurant where current time is between there start and end time
			for (int i = 0; i <= payloadJSON.size (); i++) {
				startTime = getValue ("start24", payloadJSON.get (i));
				endTime = getValue ("end24", payloadJSON.get (i));
				if (startTime != null && endTime != null) {
					if (Integer.parseInt (startTime.substring (0, 2)) <= currentHour && currentHour <= Integer.parseInt (endTime.substring (0, 2))) {
						Restaurant restaurant = new Restaurant ();
						restaurant.setName (getValue ("applicant", payloadJSON.get (i)));
						restaurant.setAddress (getValue ("location", payloadJSON.get (i)));
						restaurantList.add (restaurant);
					} else {
						System.out.println ("Current time " + currentHour + " is not between Start time" + startTime + "&& End time " + endTime);
					}
				} else {
					System.out.println ("Invalid start time" + startTime + " or end time " + endTime);
				}
			}
		} catch (IOException e) {
			System.out.println ("Error parsing json Node");
		} finally {
			//Displays restaurants
			displayRestaurant (restaurantList);
		}
	}

	/**
	 * Method to display opened restaurants
	 * - Calls sortRestaurants method to get alphabetically sorted restaurants
	 * - display result in groups of 10 considering user interest
	 * - displays restaurant name and address if open otherwise display closed restaurant status
	 */
	public void displayRestaurant (List<Restaurant> restaurantList) {
		sortRestaurantList = sortRestaurants (restaurantList);
		int count = 0, input = 0;
		if (!sortRestaurantList.isEmpty ()) {
			for (Restaurant restaurant : sortRestaurantList) {
				count++;
				if (count % 10 == 0) {
					System.out.println ("Please press 1 to see more open restaurants");
					Scanner in = new Scanner (System.in);
					input = in.nextInt ();
					if (input == 1) {
						count = 0;
						continue;
					} else {
						break;
					}
				}
				System.out.println ("Restaurant Name : " + restaurant.getName ());
				System.out.println ("Restaurant address : " + restaurant.getAddress ());
				System.out.println ("\n");
			}
		} else {
			System.out.println ("Restaurants are currently closed");
		}
	}

	/**
	 * Method to sort restaurants according to their name by alphabetical order
	 * - sorts restaurant by their name
	 */
	public List<Restaurant> sortRestaurants (List<Restaurant> restaurantList) {
		if (!restaurantList.isEmpty ()) {
			//streamer to sort restaurant in alphabetical order
			List<Restaurant> sortedRestaurants = restaurantList.stream ()
					.sorted (Comparator.comparing (Restaurant :: getName))
					.collect (Collectors.toList ());
			return sortedRestaurants;
		} else {
			System.out.println ("Restaurants currently not available");
		}
		return restaurantList;
	}

	/**
	 * Method to get a field value from JsonNode
	 * - gets node value from json
	 */
	public String getValue (String fieldName, JsonNode json) {
		JsonNode node = json.findValue (fieldName);
		if (node == null) {
			return null;
		}
		if (node.isValueNode ()) {
			return node.textValue ();
		} else {
			return node.toString ();
		}
	}
}
