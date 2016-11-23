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

package org.kie.dmn.feel.runtime;

import org.junit.runners.Parameterized;
import org.kie.dmn.feel.lang.Type;
import org.kie.dmn.feel.lang.impl.JavaBackedType;
import org.kie.dmn.feel.lang.impl.MapBackedType;
import org.kie.dmn.feel.lang.types.BuiltInType;
import org.kie.dmn.feel.runtime.impl.RangeImpl;

import test.Person;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class FEELStaticTypeTest
        extends BaseFEELCompilerTest {

    @Parameterized.Parameters(name = "{index}: {0} ({1} | {2}) = {3}")
    public static Collection<Object[]> data() {
        final Object[][] cases = new Object[][] {
            
                { "{ name : first name + last name }",
                    new HashMap<String, Type>() {{
                        put( "first name", BuiltInType.STRING );
                        put( "last name", BuiltInType.STRING );
                    }},
                    new HashMap<String, Object>() {{
                        put( "first name", "John " );
                        put( "last name", "Doe" );
                    }},
                    new HashMap<String,Object>() {{
                        put( "name", "John Doe" );
                    }} },
                
                { "{ name : person.first name + person.last name }",
                  new HashMap<String, Type>() {{
                      put( "person", new MapBackedType()
                                      .addField( "first name", BuiltInType.STRING )
                                      .addField( "last name", BuiltInType.STRING )
                      );
                  }},
                  new HashMap<String, Object>() {{
                      Map<String, String> person = new HashMap<>();
                      person.put("first name", "John ");
                      person.put("last name", "Doe");
                      put( "person", person ); 
                  }},
                  new HashMap<String,Object>() {{
                      put( "name", "John Doe" );
                  }} },
            
                { "{ myFeelVar : person.first name + person.last name }",
                  new HashMap<String, Type>() {{
                      put( "person", new JavaBackedType(Person.class) );
                  }},
                  new HashMap<String, Object>() {{
                      put( "person", new Person("John ", "Doe") ); 
                  }},
                  new HashMap<String,Object>() {{
                      put( "myFeelVar", "John Doe" );
                  }} }
                
        };
        return Arrays.asList( cases );
    }
}
