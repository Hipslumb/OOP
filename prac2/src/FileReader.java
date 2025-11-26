import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class FileReader {
    File file;

    public FileReader(File file) {
        this.file = file;
    }
    public String getFileExtension() {
        String fileName = file.getName().toLowerCase(Locale.ROOT);
        if (fileName.endsWith(".csv")) return "csv";
        else if (fileName.endsWith(".xml")) return "xml";
        return "no extension";
    }

    public Map<String,Object> parseFile() throws IOException, ParserConfigurationException, SAXException {
        String extension = getFileExtension();
        CSVparser CSVparser = new CSVparser(file);
        XMLparser XMLparser = new XMLparser(file);
        return switch (extension) {
            case "csv" -> CSVparser.parseCSV();
            case "xml" -> XMLparser.parseXML();
            default -> throw new IOException("Unsupported file format: " + extension);
        };
    }
}
