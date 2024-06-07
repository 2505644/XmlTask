import db.H2Factory;
import db.H2Repo;
import converter.ConverterToXml;
import converter.ConverterToXmlJAXB;
import transform.TransformXmlFile;

import java.sql.Connection;

public class Main {
    private static final String XML_TRANSFORM = "./src/main/resources/xmlTransform.xsl";
    private static final String CSV_TRANSFORM = "./src/main/resources/csvTransform.xsl";
    private static final String FIRST_TASK = "./src/main/resources/firstTaskResult.xml";
    private static final String SECOND_TASK = "./src/main/resources/secondTaskResult.xml";
    private static final String THIRD_TASK = "./src/main/resources/thirdTaskResult.txt";

    public static void main(String[] args) {
        Connection connection = H2Factory.getConnection();

        H2Repo h2Repo = new H2Repo(connection);
        h2Repo.createTable();
        h2Repo.insertValuesInTable(10);
        h2Repo.showDatabase(false);

        // First Task
        ConverterToXml converterToXml = new ConverterToXmlJAXB();
        converterToXml.convert(h2Repo.showDatabase(true));

        TransformXmlFile transformXmlFile = new TransformXmlFile();

        // Second Task
        transformXmlFile.transformXmlFile(XML_TRANSFORM, FIRST_TASK, SECOND_TASK, 4);

        // Third Task
        transformXmlFile.transformXmlFile(CSV_TRANSFORM, SECOND_TASK, THIRD_TASK, 0);

        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}