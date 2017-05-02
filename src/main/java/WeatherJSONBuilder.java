//package main.java;

import javax.json.Json;
import javax.json.stream.JsonGenerator;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class WeatherJSONBuilder{
    public StringWriter createJSON(Map<String,String> data, Map<String,String> rapair){
        StringWriter writer = new StringWriter();
        JsonGenerator generator = Json.createGenerator(writer);
        generator.writeStartObject();
        for(Map.Entry<String,String> m : data.entrySet())
            generator.write(m.getKey(),m.getValue());
        for(Map.Entry<String,String> m : rapair.entrySet())
            generator.write(m.getKey(),m.getValue());
        generator.writeEnd();
        generator.close();
        System.out.println(writer);
        return writer;
    }

}
