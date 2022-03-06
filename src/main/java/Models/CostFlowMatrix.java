package Models;


import javafx.util.Pair;

import java.util.List;

public class CostFlowMatrix {
    private int machinesNumber;
    private Integer[][][] matrix;

    public CostFlowMatrix(List<Cost> costs, List<Flow> flows, int machinesNumber) {
        this.machinesNumber = machinesNumber;
        this.matrix = new Integer[machinesNumber][machinesNumber][2];
        costs.forEach(c -> {
            matrix[c.getSource()][c.getDest()][0] = c.getCost();
            matrix[c.getDest()][c.getSource()][0] = c.getCost();
        });
        flows.forEach(f -> {
            matrix[f.getSource()][f.getDest()][0] = f.getDest();
            matrix[f.getDest()][f.getSource()][0] = f.getDest();
        });
    }

    public void setMachinesNumber(int machinesNumber) {
        this.machinesNumber = machinesNumber;
    }

    public Integer getCost(Machine machine1, Machine machine2){
        return matrix[machine1.getNumber()][machine2.getNumber()][0];
    }

    public Integer getFlow(Machine machine1, Machine machine2){
        return matrix[machine1.getNumber()][machine2.getNumber()][1];
    }
}
