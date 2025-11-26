import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Main {
    public static void main(String[] a) throws IOException, ParserConfigurationException, SAXException {
        while (true) {
            try {
                TimeTracker timer = new TimeTracker();

                FileInputReader input = new FileInputReader();
                File file = input.input();
                timer.startAll();
                if (file != null) {
                    FileReader reader = new FileReader(file);
                    Map<String, Object> map = reader.parseFile();
                    timer.endParse();

                    DuplicateTracker duplicateTracker = new DuplicateTracker(map);
                    duplicateTracker.printDuplicates();
                    timer.endDuplicates();

                    FloorStatistic statistic = new FloorStatistic(map);
                    statistic.printFloor();
                    timer.endFloors();

                    timer.endAll();
                    timer.printTime();
                }
            } catch (IOException e) {
                System.out.println("File error: " + e.getMessage());
            } catch (ParserConfigurationException e) {
                System.out.println("XML parser error: " + e.getMessage());
            } catch (SAXException e) {
                System.out.println("XML format error: " + e.getMessage());
            }
        }
    }
}
