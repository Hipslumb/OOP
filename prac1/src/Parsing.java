import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Parsing {
    String URL; private final ReadSearch readSearch;

    public Parsing (String a){
        this.URL = a;
        this.readSearch = new ReadSearch(new Scanner(System.in));
    }

    public String pars() throws IOException, InterruptedException {

        String json = makeHttp();
        JsonObject root = JsonParser.parseString(json).getAsJsonObject();
        JsonObject query = root.getAsJsonObject("query");
        JsonObject searchInfo = query.getAsJsonObject("searchinfo");
        int totalHits = searchInfo.get("totalhits").getAsInt();
        System.out.println("Results found: " + totalHits);
        if (totalHits==0) return "0";

        JsonArray search = query.getAsJsonArray("search");
        System.out.println("Search Results:");
        for(int i = 0; i < search.size(); i++){
            JsonObject result = search.get(i).getAsJsonObject();
            String title = result.get("title").getAsString();
            String pageid = result.has("pageid") ? result.get("pageid").getAsString(): "N/A";
            System.out.println((i+1)+". "+title+" (ID: "+pageid+")");
            if (result.has("snippet")) {
                String snippet = result.get("snippet").getAsString()
                        .replaceAll("<[^>]+>", "")
                        .replaceAll("&[^;]+;", "");
                System.out.println("   " + snippet.substring(0, Math.min(snippet.length(), 300)) + "...");
            }
            System.out.println();
        }

        System.out.println("Select and enter the website number");
        int num = readSearch.intCheck(search.size());

        JsonObject choice = search.get(num - 1).getAsJsonObject();

        return choice.get("pageid").getAsString();

    }

    private String makeHttp() throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .header("User-Agent", "MyBot/1.0")
                .GET().build();
        HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
        return response.body();

    }
}
