package science.rafael.test;

import org.json.simple.JSONObject;

import science.rafael.mirrors.Mirrors;

/**
 * Mirror any website in Internet (Web) Archive
 * (read more: https://archive.org/about/)
 * 
 * THIS CLASS INTEND TO BE AN USAGE EXAMPLE!
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
 * @email  contato@rafael.science
 */
public class MirrorsMain {
	public static void main(String[] args) throws Exception {
		JSONObject fetchedUrl = Mirrors.getSiteMirror("http://"
				+ "www.avianca.com.br/amigo/seuamigo-nossoamigo-encerrada");
		System.out.println(fetchedUrl);
	}
}
