package org.kie.dmn.backend.unmarshalling.v1_1;

import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Test;
import org.kie.dmn.backend.unmarshalling.v1_1.xstream.XStreamUnmarshaller;
import org.kie.dmn.feel.model.v1_1.DMNElementReference;
import org.kie.dmn.feel.model.v1_1.Definitions;

public class MatteoTest {

    @Test
    public void asdTest() {
        XStreamUnmarshaller marshaller = new XStreamUnmarshaller();
//        marshaller.marshal("Ciao");
        
        final InputStream is = this.getClass().getResourceAsStream( "0001-input-data-string.dmn" );
        final InputStreamReader isr = new InputStreamReader( is );
        final Definitions def = marshaller.unmarshal( isr );
        
        marshaller.marshal(def);
    }
}
