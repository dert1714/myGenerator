//package main.java;

import org.apache.http.protocol.HttpDateGenerator;

import javax.ws.rs.client.*;
//import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;
import java.util.Map;

public class WeatherGenerator {
//    public StringWriter createWeather() throws IOException{
//        WeatherParser parser = new WeatherParser();
//        Map<String,Map<String,Integer>> map = parser.getData("src/main/resources/j.json");
//        DateCreator creator = new DateCreator();
////        Date date = creator.createRandomDate(map);
////        String month = stringMonth(date.getMonth());
//        DataBuilder builder = new DataBuilder();
//        Map<String, String> weatherData = builder.createData(map.get("June"));
//        Map<String, String> repairs = builder.createRepairs(5);
//        WeatherJSONBuilder weatherJson = new WeatherJSONBuilder();
//        StringWriter writer = weatherJson.createJSON(weatherData, repairs);
//        return writer;
//    }

    private Map<String,String> createWeatherPars() throws IOException {
        WeatherParser parser = new WeatherParser();
        Map<String, Map<String, Integer>> map = parser.getData("src/main/resources/j.json");
        DateCreator creator = new DateCreator();
        DataBuilder builder = new DataBuilder();
        Map<String, String> weatherData = builder.createData(map.get("June"));
        return weatherData;
    }

    private Map<String,String> createWeatherPars(String name, long timestamp) throws IOException{
        Map<String,String> weatherData = createWeatherPars();
        weatherData.put("name", name);
        weatherData.put("timestamp", new Long(timestamp).toString());
        return weatherData;
    }

    public StringWriter createWeather(String name, long timestamp) throws IOException {
        Map<String, String> weatherData = createWeatherPars(name, timestamp);
        DataBuilder builder = new DataBuilder();
        Map<String, String> repairs = builder.createRepairs(5);
        WeatherJSONBuilder weatherJson = new WeatherJSONBuilder();
        StringWriter writer = weatherJson.createJSON(weatherData, repairs);
        return writer;
    }

    private void sendJSON(StringWriter writer) throws IOException{
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/greeting");
        Invocation invocation = target.request().buildPost(Entity.entity(writer, MediaType.TEXT_PLAIN));
        Response response = invocation.invoke();
        String body = response.readEntity(String.class);
//        System.out.println(body);
        response.close();
    }

    public void sendMonthWeather(String name) throws IOException {
        DateCreator d = new DateCreator();
        long[] dates = d.createMonthDate(4);
        for(int i = 0; i < dates.length; i++) {
            StringWriter weather = createWeather(name, dates[i]);
            sendJSON(weather);
        }
    }

    private String stringMonth(int number){
        switch(number){
            case 0 : return "January";
            case 1 : return "February";
            case 2 : return "March";
            case 3 : return "April";
            case 4 : return "May";
            case 5 : return "June";
            case 6 : return "July";
            case 7 : return "Augest";
            case 8 : return "September";
            case 9 : return "October";
            case 10 : return "November";
            case 11 : return "December";
            default : return "";
        }
    }
    public static void main(String[] args) throws Throwable {
        WeatherGenerator generator = new WeatherGenerator();
        generator.sendMonthWeather("SB");
    }
}
