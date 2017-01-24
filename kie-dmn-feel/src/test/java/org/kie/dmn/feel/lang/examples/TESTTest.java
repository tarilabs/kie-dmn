package org.kie.dmn.feel.lang.examples;

import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.kie.dmn.feel.FEEL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TESTTest extends ExamplesBaseTest {
    
    private static final Logger logger = LoggerFactory.getLogger(TESTTest.class);
    private static FEEL feel;

    @BeforeClass
    public static void setupTest() {
        feel = FEEL.newInstance();
    }

    @Ignore("BROKEN TEST")
    @Test
    public void testMain() {
        String expression = loadExpression("TEST.feel");
        Map context = (Map) feel.evaluate(expression);

        System.out.println(printContext(context));

    }
}
