import java.util.ArrayList;
import java.util.Map;

public class FloorStatistic {
    Map<String, Object> map;
    public FloorStatistic(Map<String, Object> map){
        this.map = map;
    }
    public void printFloor(){
        Map<String, int[]> floorStats = (Map<String, int[]>) map.get("floorStats");
        ArrayList<String> cities = new ArrayList<>(floorStats.keySet());

        for (String city : cities) {
            System.out.println("\n");
            int[] floors = floorStats.get(city);
            System.out.println("City - " + city);

            for (int floor = 1; floor < 6; floor++) {
                System.out.println(floor + " floor: " + floors[floor]);
            }
        }
    }

}
