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

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import org.junit.runners.Parameterized;

public class FEELListsTest extends BaseFEELTest {

    @Parameterized.Parameters(name = "{index}: {0} ({1}) = {2}")
    public static Collection<Object[]> data() {
        final Object[][] cases = new Object[][] {

                // list
                { "[ 5, 10+2, \"foo\"+\"bar\", true ]", Arrays.asList( BigDecimal.valueOf( 5 ), BigDecimal.valueOf( 12 ), "foobar", Boolean.TRUE ) },

                // filters
                {"[\"a\", \"b\", \"c\"][1]", "a" },
                {"[\"a\", \"b\", \"c\"][-1]", "c" },
                {"[\"a\", \"b\", \"c\"][5]", null },
                {"\"a\"[1]", "a" },
                {"\"a\"[-1]", "a" },
                {"{ a list : [10, 20, 30, 40], second : a list[2] }.second", BigDecimal.valueOf( 20 ) },
                {"[1, 2, 3, 4][item > 2]", Arrays.asList( BigDecimal.valueOf( 3 ), BigDecimal.valueOf( 4 ) ) },
                {"[ {x:1, y:2}, {x:2, y:3} ][x = 1]", new HashMap<String, Object>(  ) {{ put("x", BigDecimal.valueOf( 1 )); put("y", BigDecimal.valueOf( 2 ));}} },
                {"[ {x:1, y:2}, {x:2, y:3} ].y", Arrays.asList( BigDecimal.valueOf( 2 ), BigDecimal.valueOf( 3 ) ) }
        };
        return Arrays.asList( cases );
    }
}
