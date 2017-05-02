//package main.java;
import javax.json.Json;
import javax.json.stream.JsonParser;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by valera on 15.04.2017.
 */
public class WeatherParser {
    Map<String,Map<String,Integer>> getData() throws IOException{
        JsonParser parser = Json.createParser(new FileReader("src/main/resources/j.json"));
        int tmp = 0;
        Map<String,Integer> m = new HashMap<>();
        Map<String,Map<String,Integer>> map = new HashMap<>();
        String month = "";
        String key = "";
        int value = 0;
        while (parser.hasNext()) {
            JsonParser.Event event = parser.next();
            if (event.equals(JsonParser.Event.START_OBJECT))
                tmp++;
            if (event.equals(JsonParser.Event.END_OBJECT)) {
                tmp--;
                if (tmp == 1) {
                    map.put(month, m);
                    m = new HashMap<>();
                }
            }
            if (event.equals(JsonParser.Event.KEY_NAME)) {
                if (tmp == 1)
                    month = parser.getString();
                else
                    key = parser.getString();
            }
            if (event.equals(JsonParser.Event.VALUE_NUMBER)) {
                value = parser.getInt();
                m.put(key, value);
            }
        }
        return map;
    }
}
