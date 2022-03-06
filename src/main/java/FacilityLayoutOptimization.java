import Models.*;

import java.awt.*;
import java.text.MessageFormat;
import java.util.*;
import java.util.List;

public class FacilityLayoutOptimization {
    private final List<Cost> costs;
    private final List<Flow> flows;
    private final List<Machine> machines;
    private final List<Machine[][]> population;
    private final CostFlowMatrix costFlowMatrix;
    private final InstanceName instanceName;

    public FacilityLayoutOptimization(InstanceName instanceName) {
        this.costs = Cost.loadData(instanceName);
        this.flows = Flow.loadData(instanceName);
        ArrayList<Machine> machines1 = new ArrayList<>();
        for(int i = 0 ; i < instanceName.getMachines(); i++){
            machines1.add(new Machine(i));
        }
        this.machines = machines1;
        this.instanceName = instanceName;
        this.population = new ArrayList<>();
        this.costFlowMatrix = new CostFlowMatrix(costs, flows, instanceName.getMachines());
    }

    public List<Specimen> getPopulation(int n){
        var population = new ArrayList<Specimen>();
        for(int i = 0 ; i < n ; i++){
            population.add(new Specimen(instanceName, machines));
        }
        return population;
    }

    public int objectiveFunction(Machine machine1, Machine machine2){
        return -1;//costFlowMatrix.getFlow(machine1, machine2) + costFlowMatrix.getCost(machine1, machine2) + getDistance(machine1, machine2);
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


    public void printPopulation(List<Specimen> population){
        for(int i = 0 ; i <population.size(); i++){
            System.out.println(MessageFormat.format("\n##### Population nr: {0} #####", i));
            System.out.println(population.get(i));
        };
    }
}
