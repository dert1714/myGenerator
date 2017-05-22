//package main.java;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by valera on 16.04.2017.
 */
public class DataBuilder {
    Random random = new Random(47);
    static int faultsCount = 0;
    public DataBuilder(){
        faultsCount = 0;
    }
    public Map<String, String> createData(Map<String,Integer> map){
        Map<String,String> res = new HashMap<>();
        Double temperature = createDoubleProperty(map.get("temperature_min"), map.get("temperature_max"));
        res.put("temperature", temperature.toString());
        Double pressure = createDoubleProperty(map.get("pressure_min"), map.get("pressure_max"),2);
        res.put("pressure", pressure.toString());
        Double damp = createDoubleProperty(map.get("damp_min"), map.get("damp_max"));
        res.put("damp", damp.toString());
        Double speed_wind = createDoubleProperty(map.get("speed_wind_min"), map.get("speed_wind_max"));
        res.put("speed_wind", speed_wind.toString());
        return res;
    }

    public Map<String,String> createRepairs(){
        return createRepairs(0);
    }

    public Map<String,String> createRepairs(int maxFaults){
        Map<String,String> map = new HashMap<>();
//        int repairTemperature = createRepair(maxFaults);
        map.put("repairTemperature", Integer.toString(createRepair(maxFaults)));
        map.put("repairPressure", Integer.toString(createRepair(maxFaults)));
        map.put("repairDamp", Integer.toString(createRepair(maxFaults)));
        map.put("repairWind", Integer.toString(createRepair(maxFaults)));
        int directing = random.nextInt(8);
        map.put("directing",Integer.toString(directing));
        return map;
    }

    private int createRepair(int maxFaults){
        /** maybe we need more difficult algorithm*/
        if(maxFaults <= faultsCount)
            return 1;
        else{
            int res = random.nextInt(2);
            if(res == 0)
                faultsCount++;
            return res;
        }
    }

    private double decimalDouble(double value, int dec){
        if(dec > 9)
            throw new IllegalArgumentException("dec should be < 10");
        int res = (int)(value * 10 * dec);
        return (double)res / 10 * dec;
    }

    private double createDoubleProperty(int min, int max, int dec){
        if(min == max)
            return (double)min;
        int tmp = DateCreator.createProperty(min,max);
        int sign = 1;
        if (tmp == max)
            sign = -1;
        return tmp + sign * decimalDouble(random.nextDouble(), dec);
    }

    private double createDoubleProperty(int min, int max){
        return createDoubleProperty(min, max, 1);
    }

}
