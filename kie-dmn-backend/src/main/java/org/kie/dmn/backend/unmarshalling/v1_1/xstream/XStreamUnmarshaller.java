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
import com.thoughtworks.xstream.io.xml.QNameMap;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import javanet.staxutils.StaxUtilsXMLOutputFactory;

import org.kie.dmn.feel.model.v1_1.Artifact;
import org.kie.dmn.feel.model.v1_1.Association;
import org.kie.dmn.feel.model.v1_1.AuthorityRequirement;
import org.kie.dmn.feel.model.v1_1.Binding;
import org.kie.dmn.feel.model.v1_1.BusinessContextElement;
import org.kie.dmn.feel.model.v1_1.BusinessKnowledgeModel;
import org.kie.dmn.feel.model.v1_1.Context;
import org.kie.dmn.feel.model.v1_1.ContextEntry;
import org.kie.dmn.feel.model.v1_1.DMNElement;
import org.kie.dmn.feel.model.v1_1.DMNElementReference;
import org.kie.dmn.feel.model.v1_1.DRGElement;
import org.kie.dmn.feel.model.v1_1.Decision;
import org.kie.dmn.feel.model.v1_1.DecisionRule;
import org.kie.dmn.feel.model.v1_1.DecisionService;
import org.kie.dmn.feel.model.v1_1.DecisionTable;
import org.kie.dmn.feel.model.v1_1.Definitions;
import org.kie.dmn.feel.model.v1_1.ElementCollection;
import org.kie.dmn.feel.model.v1_1.Expression;
import org.kie.dmn.feel.model.v1_1.FunctionDefinition;
import org.kie.dmn.feel.model.v1_1.Import;
import org.kie.dmn.feel.model.v1_1.ImportedValues;
import org.kie.dmn.feel.model.v1_1.InformationItem;
import org.kie.dmn.feel.model.v1_1.InformationRequirement;
import org.kie.dmn.feel.model.v1_1.InputClause;
import org.kie.dmn.feel.model.v1_1.InputData;
import org.kie.dmn.feel.model.v1_1.Invocation;
import org.kie.dmn.feel.model.v1_1.ItemDefinition;
import org.kie.dmn.feel.model.v1_1.KnowledgeRequirement;
import org.kie.dmn.feel.model.v1_1.KnowledgeSource;
import org.kie.dmn.feel.model.v1_1.LiteralExpression;
import org.kie.dmn.feel.model.v1_1.NamedElement;
import org.kie.dmn.feel.model.v1_1.OrganizationUnit;
import org.kie.dmn.feel.model.v1_1.OutputClause;
import org.kie.dmn.feel.model.v1_1.PerformanceIndicator;
import org.kie.dmn.feel.model.v1_1.Relation;
import org.kie.dmn.feel.model.v1_1.TextAnnotation;
import org.kie.dmn.feel.model.v1_1.UnaryTests;
import org.kie.dmn.unmarshalling.v1_1.Unmarshaller;
import org.xml.sax.InputSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.stream.StreamResult;

public class XStreamUnmarshaller
        implements Unmarshaller {

    private static StaxDriver staxDriver;
    static {
        QNameMap qmap = new QNameMap();
        qmap.setDefaultNamespace("http://www.omg.org/spec/DMN/20151101/dmn.xsd");
        qmap.registerMapping(new javax.xml.namespace.QName("http://www.omg.org/spec/FEEL/20140401", "feel", "feel"), "dddecision"); // FIXME boh.
        
        staxDriver = new StaxDriver() {

            private XMLOutputFactory outputFactory = null;

            public XMLOutputFactory getOutputFactory() {
                if (outputFactory == null) {
                    XMLOutputFactory factory = new StaxUtilsXMLOutputFactory(super.getOutputFactory());
                    factory.setProperty(StaxUtilsXMLOutputFactory.INDENTING, Boolean.TRUE);
                    outputFactory = factory;
                }
                return outputFactory;
            }
        };
        staxDriver.setQnameMap(qmap);
        staxDriver.setRepairingNamespace(false);
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
        marshal(o, System.out);
    }
    
    public void marshal(Object o, OutputStream out) {
        try {
            XStream xStream = newXStream();
            String xml = xStream.toXML(o);
            out.write(xml.getBytes());
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }
    
    /** 
     * Unnecessary as was a tentative UTF-8 preamble output but still not working.
     */
    @Deprecated
    public void marshalMarshall(Object o) {
        marshalMarshall(o, System.out);
    }
    /** 
     * Unnecessary as was a tentative UTF-8 preamble output but still not working.
     */
    @Deprecated
    public void marshalMarshall(Object o, OutputStream out) {
        try {
            XStream xStream = newXStream();
            out.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n".getBytes());
            OutputStreamWriter ows = new OutputStreamWriter(out, "UTF-8");
            xStream.toXML(o, ows);
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }
    
    /** 
     * Unnecessary as the stax driver custom anon as static definition is embedding the indentation.
     */
    @Deprecated
    public static String formatXml(String xml){
        try{
           Transformer serializer= SAXTransformerFactory.newInstance().newTransformer();
           serializer.setOutputProperty(OutputKeys.INDENT, "yes");         
           serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
           Source xmlSource=new SAXSource(new InputSource(new ByteArrayInputStream(xml.getBytes())));
           StreamResult res =  new StreamResult(new ByteArrayOutputStream());            
           serializer.transform(xmlSource, res);
           return new String(((ByteArrayOutputStream)res.getOutputStream()).toByteArray());
        }catch(Exception e){   
           return xml;
        }
     }
    
    private XStream newXStream() {
        XStream xStream = new XStream( staxDriver );
        xStream.alias( "artifact", Artifact.class );
        xStream.alias( "definitions", Definitions.class );
        xStream.alias( "inputData", InputData.class );
        xStream.alias( "decision", Decision.class );
        xStream.alias( "variable", InformationItem.class );
        xStream.alias( "informationRequirement", InformationRequirement.class );
        xStream.alias( "requiredInput", DMNElementReference.class );
        xStream.alias( "literalExpression", LiteralExpression.class );
        
        // TODO will need to remove dups and find missing element not captured?
        xStream.alias("DMNElement", DMNElement.class );
        xStream.alias("allowedValues", UnaryTests.class );
        xStream.alias("artifact", Artifact.class );
        xStream.alias("association", Association.class );
        xStream.alias("authorityRequirement", AuthorityRequirement.class );
        xStream.alias("authorityRequirement", AuthorityRequirement.class );
        xStream.alias("authorityRequirement", AuthorityRequirement.class );
        xStream.alias("binding", Binding.class );
        xStream.alias("businessContextElement", BusinessContextElement.class );
        xStream.alias("businessKnowledgeModel", BusinessKnowledgeModel.class );
        xStream.alias("column", InformationItem.class );
        xStream.alias("context", Context.class );
        xStream.alias("contextEntry", ContextEntry.class );
        xStream.alias("decision", Decision.class );
        xStream.alias("decisionMade", DMNElementReference.class );
        xStream.alias("decisionMaker", DMNElementReference.class );
        xStream.alias("decisionOwned", DMNElementReference.class );
        xStream.alias("decisionOwner", DMNElementReference.class );
        xStream.alias("decisionService", DecisionService.class );
        xStream.alias("decisionTable", DecisionTable.class );
        xStream.alias("defaultOutputEntry", LiteralExpression.class );
        xStream.alias("definitions", Definitions.class );
        xStream.alias("drgElement", DMNElementReference.class );
//        xStream.alias("drgElement", DRGElement.class ); ambiguity, also referring to top-level xsd element just under xsd root.
        xStream.alias("elementCollection", ElementCollection.class );
        xStream.alias("elementCollection", ElementCollection.class );
        xStream.alias("encapsulatedDecision", DMNElementReference.class );
        xStream.alias("encapsulatedLogic", FunctionDefinition.class );
        xStream.alias("expression", Expression.class );
        xStream.alias("formalParameter", InformationItem.class );
        xStream.alias("functionDefinition", FunctionDefinition.class );
        xStream.alias("impactedPerformanceIndicator", DMNElementReference.class );
        xStream.alias("impactingDecision", DMNElementReference.class );
        xStream.alias("import", Import.class );
        xStream.alias("import", Import.class );
        xStream.alias("importedElement", String.class ); // TODO where?
        xStream.alias("importedValues", ImportedValues.class );
        xStream.alias("informationItem", InformationItem.class );
        xStream.alias("informationRequirement", InformationRequirement.class );
        xStream.alias("input", InputClause.class );
        xStream.alias("inputData", DMNElementReference.class );
        xStream.alias("inputData", InputData.class );
        xStream.alias("inputDecision", DMNElementReference.class );
        xStream.alias("inputEntry", UnaryTests.class );
        xStream.alias("inputExpression", LiteralExpression.class );
        xStream.alias("inputValues", UnaryTests.class );
        xStream.alias("invocation", Invocation.class );
        xStream.alias("itemComponent", ItemDefinition.class );
        xStream.alias("itemDefinition", ItemDefinition.class );
        xStream.alias("itemDefinition", ItemDefinition.class );
        xStream.alias("knowledgeRequirement", KnowledgeRequirement.class );
        xStream.alias("knowledgeRequirement", KnowledgeRequirement.class );
        xStream.alias("knowledgeSource", KnowledgeSource.class );
        xStream.alias("literalExpression", LiteralExpression.class );
        xStream.alias("namedElement", NamedElement.class );
        xStream.alias("organizationUnit", OrganizationUnit.class );
        xStream.alias("output", OutputClause.class );
        xStream.alias("outputDecision", DMNElementReference.class );
        xStream.alias("outputEntry", LiteralExpression.class );
        xStream.alias("outputValues", UnaryTests.class );
        xStream.alias("owner", DMNElementReference.class );
        xStream.alias("parameter", InformationItem.class );
        xStream.alias("performanceIndicator", PerformanceIndicator.class );
        xStream.alias("relation", Relation.class );
        xStream.alias("requiredAuthority", DMNElementReference.class );
        xStream.alias("requiredDecision", DMNElementReference.class );
        xStream.alias("requiredDecision", DMNElementReference.class );
        xStream.alias("requiredInput", DMNElementReference.class );
        xStream.alias("requiredInput", DMNElementReference.class );
        xStream.alias("requiredKnowledge", DMNElementReference.class );
        xStream.alias("rule", DecisionRule.class );
        xStream.alias("sourceRef", DMNElementReference.class );
        xStream.alias("supportedObjective", DMNElementReference.class );
        xStream.alias("targetRef", DMNElementReference.class );
        xStream.alias("textAnnotation", TextAnnotation.class );
        xStream.alias("type", String.class ); // TODO where?
        xStream.alias("typeRef", org.kie.dmn.feel.model.v1_1.QName.class );
        xStream.alias("usingProcess", DMNElementReference.class );
        xStream.alias("usingTask", DMNElementReference.class );
        xStream.alias("variable", InformationItem.class );
        xStream.alias("variable", InformationItem.class );
        xStream.alias("variable", InformationItem.class );
        xStream.alias("variable", InformationItem.class );
//        xStream.alias("allowedAnswers", xsd:string.class );
//        xStream.alias("description", xsd:string.class );
//        xStream.alias("question", xsd:string.class );
//        xStream.alias("text", xsd:string.class );
//        xStream.alias("text", xsd:string.class );
//        xStream.alias("text", xsd:string.class );
        xStream.alias("row", org.kie.dmn.feel.model.v1_1.List.class );
        xStream.alias("list", org.kie.dmn.feel.model.v1_1.List.class );        

        // Manually imported TEXT = String
        xStream.alias( LiteralExpressionConverter.TEXT, String.class );
        xStream.alias( TextAnnotationConverter.TEXT, String.class );
        xStream.alias( UnaryTestsConverter.TEXT, String.class );
        xStream.alias( DecisionConverter.QUESTION, String.class );
        xStream.alias( DecisionConverter.ALLOWED_ANSWERS, String.class );
        xStream.alias( DMNElementConverter.DESCRIPTION, String.class );

        xStream.registerConverter(new AssociationConverter( xStream ) );
        xStream.registerConverter(new AuthorityRequirementConverter( xStream ) );
        xStream.registerConverter(new BindingConverter( xStream ) );
        xStream.registerConverter(new BusinessKnowledgeModelConverter( xStream ) );
        xStream.registerConverter(new ContextConverter( xStream ) );
        xStream.registerConverter(new ContextEntryConverter( xStream ) );
        xStream.registerConverter(new DecisionConverter( xStream ) );
        xStream.registerConverter(new DecisionRuleConverter( xStream ) );
        xStream.registerConverter(new DecisionTableConverter( xStream ) );
        xStream.registerConverter(new DefinitionsConverter( xStream ) );
        xStream.registerConverter(new DMNElementReferenceConverter( xStream ) );
        xStream.registerConverter(new FunctionDefinitionConverter( xStream ) );
        xStream.registerConverter(new ImportConverter( xStream ) );
        xStream.registerConverter(new ImportedValuesConverter( xStream ) );
        xStream.registerConverter(new InformationItemConverter( xStream ) );
        xStream.registerConverter(new InformationRequirementConverter( xStream ) );
        xStream.registerConverter(new InputClauseConverter( xStream ) );
        xStream.registerConverter(new InputDataConverter( xStream ) );
        xStream.registerConverter(new InvocationConverter( xStream ) );
        xStream.registerConverter(new ItemDefinitionConverter( xStream ) );
        xStream.registerConverter(new KnowledgeRequirementConverter( xStream ) );
        xStream.registerConverter(new KnowledgeSourceConverter( xStream ) );
        xStream.registerConverter(new LiteralExpressionConverter( xStream ) );
        xStream.registerConverter(new OrganizationUnitConverter( xStream ) );
        xStream.registerConverter(new OutputClauseConverter( xStream ) );
        xStream.registerConverter(new PerformanceIndicatorConverter( xStream ) );
        xStream.registerConverter(new RelationConverter( xStream ) );
        xStream.registerConverter(new TextAnnotationConverter( xStream ) );
        xStream.registerConverter(new UnaryTestsConverter( xStream ) );
        
        xStream.registerConverter(new DMNQNameConverter());
        xStream.registerConverter(new DMNListConverter( xStream ));
        xStream.registerConverter(new ElementCollectionConverter( xStream ));
        
        return xStream;
    }

}
