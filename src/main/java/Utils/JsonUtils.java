package Utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import static Utils.Log.getLogger;

public class JsonUtils {
    private static ObjectMapper mapper = new ObjectMapper();

    public static <T> List<T> getListFromJsonString(String jsonString, Class<T> clazz) {
        try {
            return mapper.readValue(jsonString, mapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch(Exception e) {
            getLogger().error("Failed to create List object from json String.");
            throw new RuntimeException(e);
        }
    }

    public static <T> T getObjectFromJsonString(String jsonString, Class<T> clazz) {
        try {
            return mapper.readValue(jsonString, clazz);
        } catch(Exception e) {
            getLogger().error("Failed to get Object from json String.");
            throw new RuntimeException(e);
        }
    }

    public static <T> String getJsonStringFromObject(T obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch(Exception e) {
            getLogger().error("Failed to get json String from object.");
            throw new RuntimeException(e);
        }
    }
}
