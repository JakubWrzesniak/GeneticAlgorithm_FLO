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
        facilityLayoutOptimization.assignRandomPositionsToMachines();

        var machinesList = facilityLayoutOptimization.getMachines();
        Assertions.assertEquals(instanceName.getMachines(), machinesList.size(), "Incorrect number of machines");

        var distinctPositionList = machinesList.stream().map(Machine::getPosition).distinct();
        Assertions.assertEquals(instanceName.getMachines(), distinctPositionList.count(), "Not all dimensions are uniq");

        machinesList.forEach(m ->{
                var pos = m.getPosition();
                Assertions.assertTrue(pos.x < instanceName.getDimension().width);
                Assertions.assertTrue(pos.y < instanceName.getDimension().height);
        });
        facilityLayoutOptimization.printMachines();
        facilityLayoutOptimization.assignRandomPositionsToMachines();
        facilityLayoutOptimization.printMatrix();
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
