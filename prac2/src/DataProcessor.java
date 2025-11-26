import java.util.Map;

public class DataProcessor {
    public Map<String,Object> putResult(Map<String,Object> result,
                                        Map<String,Map<String,Object>> rows,
                                        Map<String, Integer> count,
                                        Map<String, int[]> floorStats){
        result.put("rows", rows);
        result.put("counts", count);
        result.put("floorStats", floorStats);
        return result;
    }

    public void collectFloor(Map<String,Object> row, Map<String, int[]> floorStats){
        String city = row.get("city").toString();
        String floorStr = row.get("floor").toString();
        int floor = Integer.parseInt(floorStr);
        if (floor >= 1 && floor <= 5){
            if (!floorStats.containsKey(city)) {
                floorStats.put(city, new int[6]);
            }
            floorStats.get(city)[floor]++;
        }
    }
}
