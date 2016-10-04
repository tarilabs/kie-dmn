/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kie.dmn.backend.unmarshalling.v1_1.xstream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import org.kie.dmn.feel.model.v1_1.*;
import org.kie.dmn.unmarshalling.v1_1.Unmarshaller;

import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class XStreamUnmarshaller
        implements Unmarshaller {
    
    public static final Map<Class, String> ALIASES = new HashMap<>();
    static {
        ALIASES.put( Definitions.class, "definitions" );                        
        ALIASES.put( InputData.class, "inputData" );                            
        ALIASES.put( Decision.class, "decision" );                              
        ALIASES.put( InformationItem.class, "variable" );                       
        ALIASES.put( InformationRequirement.class, "informationRequirement" );  
        ALIASES.put( DMNElementReference.class, "requiredInput" );              
        ALIASES.put( LiteralExpression.class, "literalExpression" );            
        ALIASES.put( String.class, "text" );                                    
    }

    @Override
    public Definitions unmarshal(String xml) {
        return unmarshal( new StringReader( xml ) );
    }

    @Override
    public Definitions unmarshal(Reader isr) {
        try {
            XStream xStream = newXStream();

            Definitions def = (Definitions) xStream.fromXML( isr );

            return def;
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void marshal(Object o) {
        try {
            XStream xStream = newXStream();
            xStream.toXML(o, System.out);
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    private XStream newXStream() {
        XStream xStream = new XStream();
        for ( Entry<Class, String> alias : ALIASES.entrySet() ) {
            xStream.alias(alias.getValue(), alias.getKey());
        }

        xStream.registerConverter( new DefinitionsConverter( xStream ) );
        xStream.registerConverter( new DecisionConverter( xStream ) );
        xStream.registerConverter( new InputDataConverter( xStream ) );
        xStream.registerConverter( new InformationItemConverter( xStream ) );
        xStream.registerConverter( new InformationRequirementConverter( xStream ) );
        xStream.registerConverter( new DMNElementReferenceConverter( xStream ) );
        xStream.registerConverter( new LiteralExpressionConverter( xStream ) );
        return xStream;
    }

}
