import Models.Cost;
import Models.Flow;
import Models.InstanceName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class LoadDataTest {

    @DisplayName("LoadFlowDataTest")
    @ParameterizedTest(name = "{index} => instanceName=''{0}''")
    @EnumSource(InstanceName.class)
    public void loadFlow_test(InstanceName instanceName){
        var res = Flow.loadData(instanceName);
        Assertions.assertFalse(res.isEmpty());
    }

    @DisplayName("LoadCostDateTest")
    @ParameterizedTest(name = "{index} => instanceName=''{0}''")
    @EnumSource(InstanceName.class)
    public void loadCost_test(InstanceName instanceName){
        var res = Cost.loadData(instanceName);
        Assertions.assertFalse(res.isEmpty());
    }
}
