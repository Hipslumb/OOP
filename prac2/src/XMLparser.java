import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class XMLparser {
    File file;
    public XMLparser(File file){
        this.file = file;
    }

    public Map<String,Object> parseXML() throws IOException, SAXException, ParserConfigurationException {
        DataProcessor processor = new DataProcessor();

        Map<String,Object> result = new HashMap<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(file);
        NodeList nodeList = document.getElementsByTagName("item");

        Map<String, Map<String, Object>> rows = new HashMap<>();
        Map<String, Integer> count = new HashMap<>();
        Map<String, int[]> floorStats = new HashMap<>();

        for(int i = 0; i <nodeList.getLength(); i++){
            Element item = (Element) nodeList.item(i);
            Map<String, Object> row = new HashMap<>();

            String rowSignature = putInRow(row,item);

            rows.put(rowSignature,row);
            int curCount = count.getOrDefault(rowSignature, 0);
            count.put(rowSignature, curCount + 1);
            processor.collectFloor(row, floorStats);

        }
        return processor.putResult(result, rows, count, floorStats);
    }

    private String putInRow(Map<String, Object> row, Element item){
        String city = item.getAttribute("city");
        String street = item.getAttribute("street");
        String house = item.getAttribute("house");
        String floor = item.getAttribute("floor");

        row.put("city", city);
        row.put("street", street);
        row.put("house", house);
        row.put("floor", floor);

        StringBuilder signature = new StringBuilder();
        signature.append(city).append(street).append(house).append(floor);
        String rowSignature = signature.toString();
        return rowSignature;
    }

}
