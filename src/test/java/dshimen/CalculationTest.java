package dshimen;

import org.junit.Assert;
import org.junit.Test;

public class CalculationTest {
    @Test
    public void checkCalculation(){
        Assert.assertEquals(Main.calculate("3*7/7 + 1 - 2 + 1 + (2+3)"), 3);
    }
}
