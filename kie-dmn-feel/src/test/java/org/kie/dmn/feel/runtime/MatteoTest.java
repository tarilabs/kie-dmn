package org.kie.dmn.feel.runtime;

import java.util.Map;

import org.junit.Test;
import org.kie.dmn.feel.FEEL;
import org.kie.dmn.feel.lang.examples.ExamplesBaseTest;

public class MatteoTest extends ExamplesBaseTest {
    @Test
    public void testMatteo() {

        Object result = FEEL.newInstance().evaluate( " { x: { y z: \"ciao\"} , myCtx: { myY: x.y z }  } " );

        if ( result instanceof Map ) {
            System.out.println( printContext( (Map) result ) );
        } else {
            System.out.println( "Result: " + result );
        }
    }
}
