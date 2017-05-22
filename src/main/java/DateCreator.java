//package main.java;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Random;

public class DateCreator {
    static Random random = new Random(47);
    public Date createRandomDate(Map<String,Map<String,Integer>> map){
        int min = 0;
        int max = 0;
        Map<String,Integer> m = map.get("Date");
        int year = createProperty(m.get("year_min"), m.get("year_max")) - 1900;
        int month = createProperty(m.get("month_min"), m.get("month_max"));
        int day = createProperty(m.get("day_min"), maxDay(m.get("month_max"),maxLegalDay(year, month)));
        int hour = createProperty(m.get("hour_min"), m.get("hour_max"));
        return new Date(year,month,day,hour,0);
    }

    public long[] createMonthDate(int month) {
        Date d = new Date(117,month,1,10,0);
        long timestamp = d.getTime();
        long res[] = new long[31];
        res[0] = timestamp;
        for(int i = 1; i < 31; i++) {
            timestamp += 86400000;
            res[i] = timestamp;
        }
        return res;
    }

    static int createProperty(int min, int max){
        if(min > max)
            throw new IllegalArgumentException("logic mistake in config file");
        if(min == max)
            return min;
        else
            return min + random.nextInt(max - min + 1);
    }

    private int maxLegalDay(int year, int month){
        if(month < 0 || month > 11)
            throw new IllegalArgumentException("month");
        if(month == 0 || month == 2 || month == 4 || month == 6 || month == 7 || month == 9 || month == 11)
            return 31;
        if(month == 3 || month == 5 || month == 8 || month == 10)
            return 30;
        if(month == 1){
            if(year % 400 == 0)
                return 29;
            if(year % 100 == 0)
                return 28;
            if(year % 4 == 0)
                return 29;
            return 28;
        }
        return 0;
    }

    private int maxDay(int day_max, int maxLegal){
        if(day_max <= maxLegal)
            return day_max;
        else
            return maxLegal;
    }

    public static void main(String[] args) {
//        DateCreator dc = new DateCreator();
        Date dc = new Date(117, 4,22,3,45,0);
        System.out.println(dc);
        System.out.println(dc.getTime());
//        1495435497421
//        long[] r = dc.createMonthDate(4);
//        for(long l : r)
//            System.out.println(l);
    }
}
