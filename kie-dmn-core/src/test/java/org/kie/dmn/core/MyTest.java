package org.kie.dmn.core;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.acme.BasicPersonPojo;
import org.junit.Test;
import org.kie.dmn.api.core.DMNContext;
import org.kie.dmn.api.core.DMNMessage;
import org.kie.dmn.api.core.DMNModel;
import org.kie.dmn.api.core.DMNResult;
import org.kie.dmn.api.core.DMNRuntime;
import org.kie.dmn.core.api.DMNFactory;
import org.kie.dmn.core.util.DMNRuntimeUtil;
import org.kie.dmn.feel.lang.FEELType;

public class MyTest {
    @Test
    public void testMyTest() {
        DMNRuntime runtime = DMNRuntimeUtil.createRuntime( "MyTest.dmn", this.getClass() );
        DMNModel dmnModel = runtime.getModel( "https://github.com/kiegroup/kie-dmn/MyTest", "MyTest" );
        assertThat( dmnModel, notNullValue() );

        DMNContext inputCtx = runtime.newContext();
        inputCtx.set("Input Person", new BasicPersonPojo("Matteo", "Mortari"));
        
        System.out.println(inputCtx);
        
        DMNResult dmnResult = runtime.evaluateAll(dmnModel, inputCtx);
        assertThat( formatMessages( dmnResult.getMessages() ), dmnResult.hasErrors(), is( false ) );
        DMNContext result = dmnResult.getContext();
        
        System.out.println( result );
        
        assertThat( result.get( "Greet Person" ), is( "Ciao Matteo Mortari" ) );
    }
    
    private String formatMessages(List<DMNMessage> messages) {
        return messages.stream().map( m -> m.toString() ).collect( Collectors.joining( "\n" ) );
    }

}
