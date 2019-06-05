package test;

import com.chd.tank.PropertyMgr;
import org.junit.Test;
import static org.junit.Assert.*;
public class ConfigTest {
    @Test
    public void getConfig(){
        int count = Integer.parseInt((String) PropertyMgr.get("initTankCount"));
        assertEquals(count, 10);
    }
}
