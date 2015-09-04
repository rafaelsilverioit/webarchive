package science.rafael.mirrors;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

import org.json.simple.JSONObject;

import science.rafael.utils.PropertyManager;

/**
 * Mirror any website in Internet (Web) Archive
 * (read more: https://archive.org/about/)
 * 
 * Basically Internet Archive is a "museum" of
 * the internet, since it's early days.
 * 
 * It crawls the web indexing websites in different
 * times and saves mirrors that you may see whenever
 * you want. The interesting point is that you can
 * tell then which site they need to index and when.
 * That's what we do, but since their API doens't let
 * us save a mirror, I've decided to create this simple
 * code to create our mirror and return it in JSON format.
 * 
 * @author Rafael Silvério Amaral
 * @email contato@rafael.science
 */
public class Mirrors {
	// result
	private static JSONObject rs;
	// configuration properties
	private static Properties confs;
	private static String newUrl;
	private static String SaveWArchive;
	private static String wArchive;
	private static String clean;
	private static String inputLine;

	/**
	 * Returns a JSONObject with the original and the mirror URLs
	 * 
	 * @param nUrl
	 *            - String url to be mirrored
	 * @return rs - JSONObject
	 */
	@SuppressWarnings("unchecked")
	public static JSONObject getSiteMirror(String nUrl) {

		try {
			// loads urls for web archive
			confs = PropertyManager.readProperties("confs.properties");
		} catch (FileNotFoundException nf) {
			nf.printStackTrace();
		}

		// set urls
		SaveWArchive = confs.getProperty("wb");
		wArchive = confs.getProperty("wa");
		// clean url (removes whitespace)
		clean = nUrl.contains(" ") ? nUrl.toLowerCase().replace(" ", "+")
				: nUrl;

		try {
			URL url = new URL(SaveWArchive + clean);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			// follow redirections (used to get the final url)
			conn.setInstanceFollowRedirects(true);
			conn.setDoOutput(true);
			// any user-agent
			conn.setRequestProperty(
					"User-Agent",
					"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_2) "
							+ "AppleWebKit/536.26.17 (KHTML, like Gecko) Version/6.0.2 "
							+ "Safari/536.26.17");
			// establish connection
			conn.connect();

			// reads each page source-code
			BufferedReader in = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));

			// while redirecting
			while ((inputLine = in.readLine()) != null) {
				// verifies if the mirror is created
				if (inputLine.contains("redirUrl")) {
					// if true, then look for it
					int begin = inputLine.indexOf("redirUrl") + 12;
					int end = inputLine.indexOf("\"", begin + 5);

					// and create a new url
					newUrl = wArchive + inputLine.substring(begin, end);
					break;
				}
			}

			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			// creates our JSONObject
			rs = new JSONObject();
			// and save the original and mirror urls
			rs.put("url", nUrl);
			rs.put("mirror", newUrl);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rs;
	}
}