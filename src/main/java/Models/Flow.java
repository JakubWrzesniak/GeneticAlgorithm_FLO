package Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class Flow {
    private static final String FILE_NAME_PATTER = "{0}_flow.json";

    @JsonProperty("source")
    private int source;
    @JsonProperty("dest")
    private int dest;
    @JsonProperty("amount")
    private int amount;

    public int getSource() {
        return source;
    }

    public int getDest() {
        return dest;
    }

    public int getAmount() {
        return amount;
    }

    private static String getFileName(InstanceName instanceName){
        return MessageFormat.format(FILE_NAME_PATTER, instanceName.name());
    }

    public static List<Flow> loadData(InstanceName instanceName) {
        String fileName = getFileName(instanceName);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            var file = Paths.get("flo_dane_v1.2/" + fileName).toFile();
            return objectMapper.readValue(file, new TypeReference<>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Flow{" +
                "source=" + source +
                ", dest=" + dest +
                ", amount=" + amount +
                '}';
    }
}
