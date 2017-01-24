package org.kie.dmn.feel.lang.examples;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasEntry;
import static org.junit.Assert.assertThat;

import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.dmn.feel.FEEL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MoreDecisionTablesTest extends ExamplesBaseTest {

    private static final Logger logger = LoggerFactory.getLogger(ExamplesTest.class);
    private static FEEL feel;

    @BeforeClass
    public static void setupTest() {
        feel = FEEL.newInstance();
    }

    @Test
    public void testMain() {
        String expression = loadExpression("more_decision_tables.feel");
        Map context = (Map) feel.evaluate(expression);

        System.out.println(printContext(context));

    }

}
