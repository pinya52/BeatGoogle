package pinya;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HTMLHandler {

	private String urlStr;
	private String content;
	private ArrayList<String> subLinkArray = new ArrayList<String>();

	public HTMLHandler(String urlStr) {

		this.urlStr = urlStr;
	}

	private String fetchContent() {

		String retVal = "";

		try {

			URL url = new URL(this.urlStr);
			URLConnection conn = url.openConnection();
			conn.setRequestProperty("User-Agent",
					"Mozilla/5.0(Macintosh;U;Intel Mac OS X 10.4; en-US;rv:1.9.2.2)Gecko/20100316 Firefox/3.6.2");
			conn.connect();
			InputStream in = conn.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			String line = null;

			while ((line = br.readLine()) != null) {

				retVal = retVal + line + "\n";

			}

			return retVal;
		}

		catch (IOException e) {

			retVal = "  ";
			return retVal;
		}

		catch (IllegalArgumentException e) {

			retVal = "  ";
			return retVal;
		}

	}

	public int countKeyword(String keyword) throws IOException {

		if (content == null) {
			content = fetchContent();
		}

		content = content.toUpperCase();
		keyword = keyword.toUpperCase();

		int times = 0;
		int from = 0;

		while (content.indexOf(keyword, from) != -1) {

			from = content.indexOf(keyword, from) + keyword.length();
			times = times + 1;
		}

		return times;

	}

	public int countImage() throws IOException {

		if (content == null) {
			content = fetchContent();
		}
		int times = 0;
		int from = 0;

		while (content.indexOf("<img", from) != -1) {

			from = content.indexOf("<img", from) + 4;
			times = times + 1;
		}

		return times;
	}

	public ArrayList<String> getSubLink() throws IOException {

		if (content == null) {
			content = fetchContent();
		}

		int indexOfOpen = 0;

		while ((indexOfOpen = content.indexOf("a href=\"", indexOfOpen)) != -1) {

			indexOfOpen = indexOfOpen + 8;

			int indexOfClose = content.indexOf("\"", indexOfOpen);

			String subLink = content.substring(indexOfOpen, indexOfClose);

			subLinkArray.add(subLink);

			indexOfOpen = indexOfClose;
		}

		return subLinkArray;

	}

}
