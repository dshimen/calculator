package dshimen;

import org.junit.Assert;
import org.junit.Test;

public class CalculationTest {
    @Test
    public void checkCalculation(){
        Assert.assertEquals(Main.calculate("7*3*(4*3)*(((30-3*15)-20)-15)/7+14/7+3/2"), 7*3*(4*3)*(((30-3*15)-20)-15)/7.0+14/7.0+3/2.0);
        Assert.assertEquals(Main.calculate("(7*3*4*3)*(((30-3*15)-20)-15)/(7+14/7+3/2)"), (7*3*4*3)*(((30-3*15)-20)-15)/(7.0+14/7.0+3/2.0));
        Assert.assertEquals(Main.calculate("7*3*4*3*30-3*15-20-15/7+14/7+3/2"), 7*3*4*3*30-3*15-20-15/7.0+14/7.0+3/2.0);
        Assert.assertEquals(Main.calculate("7-3-4-3-30-3-15-20-15-7-14-7-3-2"), 7-3-4-3-30-3-15-20-15-7-14-7-3-2.0);
        Assert.assertEquals(Main.calculate("7/3/4/3/30/3/15/20/15/7/14/7/3/2"), (7.0/3.0/4.0/3.0/30.0/3.0/15.0/20.0/15.0/7.0/14.0/7.0/3.0/2.0));
        Assert.assertEquals(Main.calculate("(203+15)/34*(-127+-1)+1"), (203+15)/34.0*(-127+-1) + 1);
        Assert.assertEquals(Main.calculate("(203+15)/34*(-127+-1)"), (203+15)/34.0*(-127+-1));
        Assert.assertEquals(Main.calculate("203+15/34*-127+-1"), 203+15/34.0*-127+-1);
        Assert.assertEquals(Main.calculate("-203+-15/34*-127+-1"), -203+-15/34.0*-127+-1);
        Assert.assertEquals(Main.calculate("77+16+(14+23)"), 77+16.0+(14+23));
        Assert.assertEquals(Main.calculate("0"), 0.0);
        Assert.assertNull(Main.calculate("(77+16+(14+23))/0"));
    }
}
