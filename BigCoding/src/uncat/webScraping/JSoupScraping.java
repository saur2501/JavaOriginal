package uncat.webScraping;

import java.io.IOException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JSoupScraping {

	public static void main(String[] args) throws IOException {
		getLinks();
		bestMoviesScraping();
	}

	private static void bestMoviesScraping() throws IOException {
		/*System.setProperty("http.proxyHost", "194.138.0.25"); // or the IP
		System.setProperty("http.proxyPort", "9400");*/
		Document doc = Jsoup.connect("http://www.imdb.com/search/title?groups=top_250&sort=user_rating").userAgent("Mozilla/45.0").get();
		Elements links = doc.select("div.lister-item-content");
		//System.out.println(links.size());
		int i = 0;
		for(Element link: links) {
			i++;
			System.out.println(link.getElementsByTag("h3").first().text());
		}
	}

	private static void getLinks() {
		Document doc;
		try {
			/*URL u =new URL("http://www.google.com");
		    URLConnection usn=u.openConnection();
		    usn.connect();*/

			//System.setProperty("http.proxyHost", "194.138.0.25"); // or the IP
			//System.setProperty("http.proxyPort", "9400");
			doc = Jsoup.connect("http://www.jsoup.org/").timeout(60*1000).get();	//.userAgent("Mozilla/45.0").timeout(k ms)
			Elements links = doc.select("a");										//div.query
			for(Element e : links) {
				System.out.println(e.attr("abs:href"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
//Ref- https://www.youtube.com/watch?v=TIdF6_MvHzM&index=1&list=PL2npUq4XqJkyN3sr7MHuAiaZFB-74aqqB
//jars- jsoup-1.10.2.jar