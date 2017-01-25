package org.kie.dmn.validation;

import static java.util.stream.Collectors.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.kie.api.KieServices;
import org.kie.api.event.rule.DefaultRuleRuntimeEventListener;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.dmn.feel.model.v1_1.DMNModelInstrumentedBase;
import org.kie.dmn.feel.model.v1_1.DRGElement;
import org.kie.dmn.feel.model.v1_1.Definitions;
import org.kie.dmn.feel.model.v1_1.ItemDefinition;
import org.xml.sax.SAXException;

public class DMNValidatorImpl implements DMNValidator {
    static Schema schema;
    static {
        try {
            schema = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Problem> validateSchema(File xmlFile) {
        List<Problem> problems = new ArrayList<>();
        Source s = new StreamSource(xmlFile);
        try {
            schema.newValidator().validate(s);
        } catch (SAXException | IOException e) {
            problems.add(new Problem(P.FAILED_XML_VALIDATION, e));
        }
        // TODO detect if the XSD is not provided through schemaLocation, and validate against embedded?
        return problems;
    }
    
    @Override
    public List<Problem> validateModel(Definitions dmnModel) {
        
        KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
        StatelessKieSession kieSession = kieContainer.newStatelessKieSession();
        
        List<Problem> problems = new ArrayList<>();
        
        kieSession.addEventListener(new DefaultRuleRuntimeEventListener() {
            @Override
            public void objectInserted(ObjectInsertedEvent event) {
                if ( event.getObject() instanceof Problem ) {
                    problems.add((Problem) event.getObject());
                }
            }
        });
        
        kieSession.execute(allChildren(dmnModel).collect(toList()));
        
        return problems;
    }


    
    public static Stream<DMNModelInstrumentedBase> allChildren(DMNModelInstrumentedBase root) {
        return Stream.concat( Stream.of(root),
                              root.getChildren().stream().flatMap(DMNValidatorImpl::allChildren) );
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Stream<T> allChildren(DMNModelInstrumentedBase root, Class<T> clazz) {
        return (Stream<T>) allChildren(root)
                .filter(dmn -> clazz.isInstance(dmn) )
                ;
    }
    
    @Deprecated
    public static List<DMNModelInstrumentedBase> allChildrenClassic(DMNModelInstrumentedBase root) {
        List<DMNModelInstrumentedBase> result = new ArrayList<>();
        for ( DMNModelInstrumentedBase c : root.getChildren() ) {
            result.add(c);
            result.addAll(allChildrenClassic(c));
        }
        return result;
    }


}
