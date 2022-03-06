package Models;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Specimen {
    private final Machine[][] specimen;
    private Integer[][] distances;
    private int numberOfMachines;

    public Specimen(InstanceName instanceName, List<Machine> machines){
        this.numberOfMachines = machines.size();
        List<Point> positions = new ArrayList<>();
        var matrix = new Machine[instanceName.getDimension().width][instanceName.getDimension().height];
        for(int h = 0 ; h < instanceName.getDimension().height; h++){
            for(int w = 0; w < instanceName.getDimension().width; w++){
                positions.add(new Point(w, h));
            }
        }
        Collections.shuffle(positions);
        Stack<Point> stack = new Stack<>();
        stack.addAll(positions);
        machines.forEach(m -> {
            var dim = stack.pop();
            matrix[dim.x][dim.y] = m;
        });
        this.specimen = matrix;
    }

    public Integer[][] getDistance(){
        if(distances == null) {
            setDistances();
        }
        return distances;
    }

    private void setDistances(){
        Map<Machine, Point> positions = new HashMap<>();
        distances = new Integer[numberOfMachines][numberOfMachines];
        for(int i = 0 ; i < specimen.length; i++){
            for(int j = 0 ;j < specimen[i].length ; j++){
                if(specimen[i][j] != null){
                    positions.put(specimen[i][j], new Point(i, j));
                }
            }
        }

        positions.forEach((k1,v1) -> {
            positions.forEach((k2, v2) -> {
                    distances[k1.getNumber()][k2.getNumber()] = getDistance(v1, v2);
            });
        });
    }

    private int getDistance(Point point1, Point point2){
        return Math.abs(point1.x - point2.x) + Math.abs(point1.y - point2.y);
    }

    public void printDistances(){
        StringBuilder sb = new StringBuilder();
        for(Integer[] value : distances) {
            for (int h = 0; h < distances[0].length; h++) {
                sb.append("[");
                sb.append((value[h] != null ? value[h] : -1));
                sb.append("]");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Machine[] value : specimen) {
            for (int h = 0; h < specimen[0].length; h++) {
                sb.append("[");
                sb.append((value[h] != null ? value[h].getNumber() : -1));
                sb.append("]");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
