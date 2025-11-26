import java.util.ArrayList;
import java.util.Map;

public class DuplicateTracker {
    Map<String, Object> map;
    public  DuplicateTracker(Map<String, Object> map){
        this.map = map;
    }
    public void printDuplicates(){
        System.out.println("\n\tDuplicates\n");
        Map<Integer, Map<String, Object>> rows = (Map<Integer, Map<String, Object>>) map.get("rows");
        Map<String, Integer> counts = (Map<String, Integer>) map.get("counts");

        ArrayList<String> signatures = new ArrayList(rows.keySet());

        for (String signature : signatures) {
            int count = counts.get(signature);
            if (count > 1)
                System.out.println(rows.get(signature) + " quantity = " + count);
        }
    }
}
