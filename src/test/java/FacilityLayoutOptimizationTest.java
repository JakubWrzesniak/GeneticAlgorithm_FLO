import Models.InstanceName;
import Models.Machine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class FacilityLayoutOptimizationTest {

    @DisplayName("LoadCostDateTest")
    @ParameterizedTest(name = "{index} => instanceName=''{0}''")
    @EnumSource(InstanceName.class)
    void randomPositionsForMachines_test(InstanceName instanceName){
        FacilityLayoutOptimization facilityLayoutOptimization = new FacilityLayoutOptimization(instanceName);
        var population = facilityLayoutOptimization.getPopulation(2);
        facilityLayoutOptimization.printPopulation(population);
        for(var p : population){
            p.getDistance();
            p.printDistances();
        }
    }

    @DisplayName("LoadCostDateTest")
    @ParameterizedTest(name = "{index} => instanceName=''{0}''")
    @EnumSource(InstanceName.class)
    void objectiveFunction_test(InstanceName instanceName){
//        FacilityLayoutOptimization facilityLayoutOptimization = new FacilityLayoutOptimization(instanceName);
//        facilityLayoutOptimization.assignRandomPositionsToMachines();
//        var firstObjectiveFunctionVal = facilityLayoutOptimization.objectiveFunction();
//        System.out.println(firstObjectiveFunctionVal);
//        Assertions.assertEquals(firstObjectiveFunctionVal, facilityLayoutOptimization.objectiveFunction());
//        facilityLayoutOptimization.assignRandomPositionsToMachines();
//        var secondObjectiveFunctionVal = facilityLayoutOptimization.objectiveFunction();
//        System.out.println(secondObjectiveFunctionVal);
//        Assertions.assertNotEquals(firstObjectiveFunctionVal, secondObjectiveFunctionVal);
    }
}
