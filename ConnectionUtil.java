package CurrencyReport;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConnectionUtil {
	public static final String URL_STRING = "http://data.bank.hexun.com/other/cms/fxjhjson.ashx?callback=PereMoreData";
	public static String getData() {
		StringBuffer html = new StringBuffer();
		String tmp = "";
		BufferedReader br = null;
		InputStreamReader isr = null;
		try {
			URL url = new URL(URL_STRING);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty(
					"User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			isr = new InputStreamReader(conn.getInputStream());
			br = new BufferedReader(isr);
			tmp = br.readLine();
			while (tmp != null) {
				html.append(tmp + "\n");
				tmp = br.readLine();
				
			}
		return html.toString();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String searcher(String data, String s) {
		StringBuffer sb = new StringBuffer(data);
		String countryName = s;
		int i = sb.indexOf("'" + countryName);
		int j = sb.indexOf(",code", i);
		return sb.substring(i, j);
	}
}
