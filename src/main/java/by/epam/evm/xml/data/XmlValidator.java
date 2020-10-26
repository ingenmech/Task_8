package by.epam.evm.xml.data;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XmlValidator {

    private final static Logger LOGGER = LogManager.getLogger(XmlValidator.class);
    private final String schemaName;

    public XmlValidator(String schemaName) {
        this.schemaName = schemaName;
    }

    public boolean valid(String xmlName) {

        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        boolean isValid;
        try {
            Schema schema = factory.newSchema(new File(schemaName));
            Validator validator = schema.newValidator();
            Source source = new StreamSource(xmlName);
            validator.validate(source);
            isValid = true;
        } catch (SAXException | IOException e) {
            LOGGER.error(e.getMessage(), e);
            isValid = false;
        }
        return isValid;
    }
}
