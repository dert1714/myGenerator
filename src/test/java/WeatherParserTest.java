import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WeatherParserTest {
    @Test
    public void testGetData() throws IOException{
        WeatherParser w = new WeatherParser();
        Map<String, Map<String, Integer>> map = w.getData("src/main/resources/test1.json");
        Map<String, Map<String, Integer>> real_map = new HashMap<>();
        Map<String, Integer> tmp_map = new HashMap<>();
        Map<String, Integer> tmp_map1 = new HashMap<>();
        tmp_map.put("temperature_min", 0);
        tmp_map.put("temperature_max", 30);
        real_map.put("April", tmp_map);
        tmp_map1.put("temperature_min", 10);
        tmp_map1.put("temperature_max", 20);
        real_map.put("May", tmp_map1);
        Assert.assertEquals(real_map, map);
    }
}
