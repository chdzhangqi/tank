package test;

import com.chd.designpattern.Singleton;
import org.junit.Test;

import static org.junit.Assert.*;

public class SingletonTest {

    @Test
    public void singleTest() {
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();
        assertEquals(s1, s2);
    }
}
