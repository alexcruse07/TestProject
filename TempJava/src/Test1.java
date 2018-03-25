import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.TimeZone;

public class Test1 {

	private static final String USER_AGENT = "Mozilla/5.0";

	private static final String GET_URL = "http://localhost:9000/mts/get/time/crspng:device699:1";

	private static final String POST_URL = "http://localhost:9090/SpringMVCExample/home";

	private static final String POST_PARAMS = "userName=Pankaj";

	public static void main(String[] args) throws IOException, InterruptedException, ParseException {

		
		System.out.println(ZonedDateTime.now(ZoneId.of("Asia/Singapore")).toString());
		System.out.println("Test");
		String clientTime = "2017-07-14T13:26:28.582+03:00[Asia/Istanbul]";
		String lv_dateFormateInUTC="";
		Date date = Date.from(ZonedDateTime.parse(clientTime).toInstant());
		System.out.println(date);
		SimpleDateFormat lv_formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSz"); 
		lv_formatter.setTimeZone(TimeZone.getTimeZone("UTC"));  
		lv_dateFormateInUTC = lv_formatter.format(date);
		System.out.println(lv_dateFormateInUTC);
		Date d = lv_formatter.parse(lv_dateFormateInUTC);
		System.out.println(d);
		String zoneId = clientTime.substring(clientTime.indexOf("[")+1, clientTime.length()-1);
		System.out.println(zoneId);
		//String dateString = clientTime.substring(0, clientTime.indexOf("["));
		//System.out.println(OffsetDateTime.parse(dateString));
	}

	private static void sendGET() throws IOException {
		URL obj = new URL(GET_URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		int responseCode = con.getResponseCode();
		System.out.println("GET Response Code :: " + responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());
		} else {
			System.out.println("GET request not worked");
		}

	}

	private static void sendPOST() throws IOException {
		URL obj = new URL(POST_URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);

		// For POST only - START
		con.setDoOutput(true);
		OutputStream os = con.getOutputStream();
		os.write(POST_PARAMS.getBytes());
		os.flush();
		os.close();
		// For POST only - END

		int responseCode = con.getResponseCode();
		System.out.println("POST Response Code :: " + responseCode);

		if (responseCode == HttpURLConnection.HTTP_OK) { //success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());
		} else {
			System.out.println("POST request not worked");
		}
	}

}