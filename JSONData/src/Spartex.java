import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;


/*
	Program to get JSON data from URL.
	It converts JSON string to JSON objects.
*/
class Spartex{
	public static void main(String[] args) {
		getJsonData("path_to_the_url");
	}
	
	/*
		Endpoint: url
	*/
	static int getJsonData(String url) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = "";

        int total = 0;
        try{
            json = readUrl(url);
            
            JsonElement jsonElement = new JsonParser().parse(json);
            
//             String s = gson.toJson(jsonElement);
            
//             System.out.println(s.toString());
            
            Response re = gson.fromJson(jsonElement, Response.class);
            
            System.out.println(re.total);
            System.out.println(re.page);
            
            

            total = re.total;
        }catch(Exception e){
        	System.out.println(e);
        }

        return total;
    }
    private static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read); 

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }

    }

	/*
		Class to represent JSON Objects.
	*/
    static class Response{
        int page;
        int per_page;
        int total;
        int total_pages;
        List<Map<String, String>> data;
    }
}