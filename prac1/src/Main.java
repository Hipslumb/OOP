import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] a) throws IOException, InterruptedException, URISyntaxException {
        System.out.println("Enter the name of the topic to search on Wikipedia");
        ReadSearch search = new ReadSearch(new Scanner(System.in));
        String topic = search.getSearch();
        System.out.println("Search topic: " + topic);
        MakeRequest request = new MakeRequest(topic);
        String URL = request.BuildResearch();
        System.out.println("URL: " + URL);

        Parsing pars = new Parsing(URL);
        String pageid = pars.pars();

        if (Objects.equals(pageid, "0")) return;
        WikiOpen wiki = new WikiOpen("https://ru.wikipedia.org/w/index.php"+"?curid="+pageid);
        wiki.wiki();
    }
}

