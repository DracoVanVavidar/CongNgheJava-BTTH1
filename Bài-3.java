import java.util.HashSet;
import java.util.Set;

public class AlertStations {
    public static Set<String> findAlertStations(int[][] stations) {
        Set<String> alertStations = new HashSet<>();
        
        for (int i = 0; i < stations.length; i++) {
            int x1 = stations[i][0], y1 = stations[i][1];
            boolean isAlert = true;
            
            for (int j = 0; j < stations.length; j++) {
                if (i != j) {
                    int x2 = stations[j][0], y2 = stations[j][1];
                    if (Math.abs(x1 - x2) <= 1 && Math.abs(y1 - y2) <= 1) {
                        isAlert = false;
                        break;
                    }
                }
            }
            
            if (isAlert) {
                alertStations.add(x1 + " " + y1);
            }
        }
        return alertStations;
    }
    
    public static void main(String[] args) {
        int[][] stations = {{2, 1}, {1, 3}, {2, 3}, {2, 4}, {4, 1}, {4, 3}};
        Set<String> alertStations = findAlertStations(stations);
        
        System.out.println("Trạm cảnh báo:");
        for (String station : alertStations) {
            System.out.println(station);
        }
    }
}
