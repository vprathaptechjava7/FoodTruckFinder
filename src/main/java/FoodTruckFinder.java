import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FoodTruckFinder {
	public static void main(String[] args) {
		   //The Socrata API
			String apiUrl = "http://data.sfgov.org/resource/bbb8-hzi6.json";

			//calls method getJsonStringFromAPI() to get json string from API
			FoodTruckFinder foodTruckFinder = new FoodTruckFinder ();
			String jsonStringFromAPI = String.valueOf (foodTruckFinder.getJsonStringFromAPI (apiUrl));

			//calls findOpenRestaurants() for checking on open restaurants if present display in alphabetical order
			FindRestaurant findRestaurant = new FindRestaurant ();
			findRestaurant.findOpenRestaurants (jsonStringFromAPI);
	}

	/**
	 * Method to get json string from the API
	 * - Calls API via HTTP connection
	 * - returns a json string from api
	 */
	public StringBuilder getJsonStringFromAPI(String apiUrl) {
		try {
			StringBuilder result = new StringBuilder ();
			URL url = new URL (apiUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection ();
			conn.setRequestMethod ("GET");
			BufferedReader rd = new BufferedReader (new InputStreamReader (conn.getInputStream ()));
			String line;
			while ((line = rd.readLine ()) != null) {
				result.append (line);
			}
			rd.close ();
			return result;
		} catch (Exception e) {
			System.out.println (e.getMessage ());
		}
		return null;
	}
}
