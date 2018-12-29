import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPath;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.util.ArrayList;

public class Dom implements XmlWorker {

    private String filename;

    public Dom(String filename) {
        this.filename = filename;
    }

    private ArrayList<Student> getStudents(NodeList nList) {
        ArrayList<Student> students = new ArrayList<>();
        for(int i = 0; i < nList.getLength(); i++) {
            Node curNode = nList.item(i);
            if(curNode.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) curNode;

                String name = element.getElementsByTagName("Name").item(0).getTextContent();
                String surname = element.getElementsByTagName("Surname").item(0).getTextContent();
                String faculty = element.getElementsByTagName("Faculty").item(0).getTextContent();
                String department = element.getElementsByTagName("Department").item(0).getTextContent();
                String averagePoint = element.getElementsByTagName("AveragePoint").item(0).getTextContent();

                students.add(new Student(name, surname, faculty, department, averagePoint));
            }
        }
        return students;
    }

    public ArrayList<Student> parse() {
        ArrayList<Student> students = new ArrayList<>();
        try {
            File inputFile = new File(filename);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("Student");
            students = getStudents(nList);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    public ArrayList<Student> search(String tag, String param) {
        ArrayList<Student> students = new ArrayList<>();

        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

            Document document = documentBuilder.parse(filename);

            String expression = "/*/Student[starts-with("+tag+",'" + param +"')]";

            System.out.println(expression);


            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xPath = xPathFactory.newXPath();
            XPathExpression xPathExpression = xPath.compile(expression);


            NodeList nList = (NodeList) xPathExpression.evaluate(document, XPathConstants.NODESET);

            students = getStudents(nList);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return students;
    }
}
