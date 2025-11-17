import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class MakeRequest {
    private String search;
    private static final String  wiki = "https://ru.wikipedia.org/w/api.php";

    public MakeRequest(String search){
       this.search = search;
    }

    public String BuildResearch() {
        String request = URLEncoder.encode(search.trim(), StandardCharsets.UTF_8);

        return wiki + "?action=query" + "&list=search" +
                "&utf8=" + "&format=json" + "&srsearch=" + request;
    }
}
