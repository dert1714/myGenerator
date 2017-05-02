import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by valera on 02.05.2017.
 */
public class DateCreatorTest {
    Map<String,Map<String,Integer>> map = new HashMap<>();
    Map<String,Integer> tmp_map = new HashMap<>();
    DateCreator dateCreator = new DateCreator();

    @Before
    public void initMap(){
        tmp_map.put("year_min", 2015);
        tmp_map.put("year_max", 2018);
        tmp_map.put("month_min", 1);
        tmp_map.put("month_max", 11);
        tmp_map.put("day_min", 30);
        tmp_map.put("day_max", 30);
        tmp_map.put("hour_min", 0);
        tmp_map.put("hour_max", 0);
    }

    @Test
    public void testMinProperties(){
        int prop = DateCreator.createProperty(1,1);
        Assert.assertEquals(1, prop);
        prop = DateCreator.createProperty(6,8);
        System.out.println(prop);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionProperties(){
        int prop = DateCreator.createProperty(2,1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFebruaryOnly(){
        tmp_map.put("month_max", 1);
        map.put("Date", tmp_map);
        Date date = dateCreator.createRandomDate(map);
    }
}
