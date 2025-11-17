import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class WikiOpen {
    private String search;

    public WikiOpen(String a){
        this.search=a;
    }

    public void wiki() throws URISyntaxException, IOException {
        if (Desktop.isDesktopSupported()&&Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)){
            Desktop.getDesktop().browse(new URI(search));
        }
    }
}
