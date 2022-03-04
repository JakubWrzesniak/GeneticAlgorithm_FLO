package Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class Cost {
    private static final String FILE_NAME_PATTER = "{0}_cost.json";
    @JsonProperty("source")
    private int source;
    @JsonProperty("dest")
    private int dest;
    @JsonProperty("cost")
    private int cost;

    public int getSource() {
        return source;
    }

    public int getDest() {
        return dest;
    }

    public int getCost() {
        return cost;
    }

    private static String getFileName(InstanceName instanceName){
        return MessageFormat.format(FILE_NAME_PATTER, instanceName.name());
    }

    public static List<Cost> loadData(InstanceName instanceName) {
        String fileName = getFileName(instanceName);
        try {
            return new ObjectMapper().readValue(Paths.get("flo_dane_v1.2/" + fileName).toFile(), new TypeReference<>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Cost{" +
                "source=" + source +
                ", dest=" + dest +
                ", cost=" + cost +
                '}';
    }
}
