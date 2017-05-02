//package main.java;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;
import java.util.Map;

public class WeatherGenerator {
    public StringWriter createWeather() throws IOException{
        WeatherParser parser = new WeatherParser();
        Map<String,Map<String,Integer>> map = parser.getData();
        DateCreator creator = new DateCreator();
        Date date = creator.createRandomDate(map);
        String month = stringMonth(date.getMonth());
        DataBuilder builder = new DataBuilder();
        Map<String, String> weatherData = builder.createData(map.get("June"));
        Map<String, String> repairs = builder.createRepairs(5);
        WeatherJSONBuilder weatherJson = new WeatherJSONBuilder();
        StringWriter writer = weatherJson.createJSON(weatherData, repairs);
        return writer;
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
    public static void main(String[] args) {
        WeatherGenerator generator = new WeatherGenerator();
        StringWriter writer = new StringWriter();
        try{
            writer = generator.createWeather();
            System.out.println("new commit");
        }catch(IOException e){
            e.printStackTrace();
        }
        finally{
            System.out.println(writer);
        }
    }
}
