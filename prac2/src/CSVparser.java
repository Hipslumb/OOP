import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVparser {
    File file;
    public  CSVparser(File file){
        this.file = file;
    }

    public Map<String,Object> parseCSV() throws IOException {
        DataProcessor processor = new DataProcessor();

        Map<String,Object> result = new HashMap<>();
        List<String> lines = Files.readAllLines(file.toPath());
        if (lines.isEmpty()) {
            result.put("error", "Empty file");
            return result;
        }
        String[] heads = lines.getFirst().split(";");
        for (int i = 0; i < heads.length; i++){
            heads[i] = heads[i].trim().replace("\"", "");
        }

        Map<String,Map<String,Object>> rows = new HashMap<>();
        Map<String, Integer> count = new HashMap<>();
        Map<String, int[]> floorStats = new HashMap<>();

        for (int i = 1; i < lines.size(); i++){
            String line = lines.get(i).trim();
            String[] values = line.split(";");
            if (line.isEmpty()) continue;

            Map<String, Object> row = new HashMap<>();
            StringBuilder rowSignature= new StringBuilder();


            for (int j = 0; j < heads.length; j++){
                String value = values[j].trim().replace("\"", "");
                row.put(heads[j],value);
                rowSignature.append(value);
            }

            rows.put(rowSignature.toString(),row);
            int curCount = count.getOrDefault(rowSignature.toString(), 0);
            count.put(rowSignature.toString(), curCount + 1);

            processor.collectFloor(row, floorStats);
        }
        return processor.putResult(result, rows, count, floorStats);
    }
}
