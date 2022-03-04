import Models.Cost;
import Models.Flow;
import Models.InstanceName;
import Models.Machine;

import java.awt.*;
import java.util.*;
import java.util.List;

public class FacilityLayoutOptimization {
    private final List<Cost> costs;
    private final List<Flow> flows;
    private final List<Machine> machines;
    private final Machine[][]matrix;
    private final InstanceName instanceName;

    public FacilityLayoutOptimization(InstanceName instanceName) {
        this.costs = Cost.loadData(instanceName);
        this.flows = Flow.loadData(instanceName);
        ArrayList<Machine> machines1 = new ArrayList<>();
        for(int i = 1 ; i <= instanceName.getMachines(); i++){
            machines1.add(new Machine(i, 0,0));
        }
        this.machines = machines1;
        this.instanceName = instanceName;
        this.matrix = new Machine[instanceName.getDimension().width][instanceName.getDimension().height];
    }

    public void assignRandomPositionsToMachines(){
        clearMatrix();
        List<Dimension> dimensions = new ArrayList<>();
        for(int h = 0 ; h < instanceName.getDimension().height; h++){
            for(int w = 0; w < instanceName.getDimension().width; w++){
                dimensions.add(new Dimension(w, h));
            }
        }
        Collections.shuffle(dimensions);
        Stack<Dimension> stack = new Stack<>();
        stack.addAll(dimensions);
        machines.forEach(m -> {
            var dim = stack.pop();
            matrix[dim.width][dim.height] = m;
            m.setPosition(dim.width, dim.height);
        });
    }

    public void clearMatrix(){
        for (int w = 0; w < matrix.length; w++) {
            for (int h = 0; h < matrix[0].length; h++){
                if(matrix[w][h] != null) {
                    matrix[w][h].setPosition(-1, -1);
                    matrix[w][h] = null;
                }
            }
        }
    }

    public int objectiveFunction(Machine machine1, Machine machine2){
        return getFlow(machine1, machine2) + getCost(machine1, machine2) + getDistance(machine1, machine2);
    }
    
    public int getCost(Machine machineFrom, Machine machineTo){
       return costs.stream().filter(x -> x.getSource() == machineFrom.getNumber() && x.getDest() == machineTo.getNumber()).map(Cost::getCost).findFirst().orElse(-1);
    }

    public int getFlow(Machine machineFrom, Machine machineTo){
        return flows.stream().filter(x -> x.getSource() == machineFrom.getNumber() && x.getDest() == machineTo.getNumber()).map(Flow::getAmount).findFirst().orElse(-1);
    }

    public int getDistance(Machine machineFrom, Machine machineTo){
        int px = machineFrom.getPosition().x - machineTo.getPosition().x;
        int py = machineFrom.getPosition().y - machineTo.getPosition().y;
        return Math.abs(px) + Math.abs(py);
    }

    public List<Cost> getCosts() {
        return costs;
    }

    public List<Flow> getFlows() {
        return flows;
    }

    public List<Machine> getMachines() {
        return machines;
    }

    public InstanceName getInstanceName() {
        return instanceName;
    }

    public void printMachines(){
        int[][] matrix1 = new int[instanceName.getDimension().width][instanceName.getDimension().height];
        machines.forEach(m -> matrix1[m.getPosition().x][m.getPosition().y] = m.getNumber());
        for (int[] ints : matrix1) {
            for (int h = 0; h < matrix1[0].length; h++) {
                System.out.print("[" + ints[h] + "]");
            }
            System.out.println();
        }
    }

    public void printMatrix(){
        for(Machine[] value : matrix) {
            for (int h = 0; h < matrix[0].length; h++) {
                System.out.print("[" + (value[h] != null ? value[h].getNumber() : 0) + "]");
            }
            System.out.println();
        }
    }
}
