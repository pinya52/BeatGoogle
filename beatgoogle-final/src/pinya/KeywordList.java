package pinya;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class KeywordList {

	private ArrayList<Keyword> keywordList = new ArrayList<Keyword>();

	private String originalURL;
	private String content;

	public KeywordList() {
	}

	public void add(Keyword keyword) {

		this.keywordList.add(keyword);
	}

	public Keyword get(int i) {
		Keyword keyword = this.keywordList.get(i);
		return keyword;

	}

	public int size() {
		int size = this.keywordList.size();
		return size;
	}

	public String generateSearchEngineURL() {

		this.originalURL = "https://www.google.com.tw/search?q=";

		for (int i = 0; i < keywordList.size(); i++) {

			this.originalURL += keywordList.get(i).getName();
			this.originalURL += "+";

		}

		this.originalURL += "&oe=utf8&num=30";

		return originalURL;
	}

	private String fetchContent() throws IOException {

		originalURL = generateSearchEngineURL();

		URL url = new URL(originalURL);
		URLConnection conn = url.openConnection();
		conn.setRequestProperty("User-Agent",
				"Mozilla/5.0(Macintosh;U;Intel Mac OS X 10.4; en-US;rv:1.9.2.2)Gecko/20100316 Firefox/3.6.2");
		conn.connect();
		InputStream in = conn.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in, "utf-8"));

		String line = null;

		while ((line = br.readLine()) != null) {
			content = content + line;
		}
		return content;
	}

	public HashMap<String, String> getSearchEngineResult() throws IOException {

		if (this.content == null) {
			this.content = fetchContent();
		}

		HashMap<String, String> retVal = new HashMap<String, String>();

		Document document = Jsoup.parse(this.content);
		Elements lis = document.select("div.g");
		for (Element li : lis) {

			try {

				Element h3 = li.select("h3.r").get(0);
				String title = h3.text();

				Element cite = li.select("cite").get(0);
				String citeUrl = cite.text();
				retVal.put(title, citeUrl);

			}

			catch (IndexOutOfBoundsException e) {
			}

		}

		return retVal;

	}

	public ArrayList<String> getRelativeKeyword() throws IOException {

		if (this.content == null) {
			this.content = fetchContent();
		}

		ArrayList<String> result = new ArrayList<String>();
		Document document = Jsoup.parse(this.content);
		Elements reK = document.select("p");

		for (Element re : reK) {

			try {

				String x = re.text();
				result.add(x);

			} catch (IndexOutOfBoundsException e) {
			}
		}
		result.remove(result.size() - 1);
		return result;
	}
}
