import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class Handler extends DefaultHandler {
    boolean name = false;
    boolean surname = false;
    boolean faculty = false;
    boolean department = false;
    boolean average = false;
    boolean student = false;
    ArrayList<String> tokens = new ArrayList<>();


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(qName.equalsIgnoreCase("Student")) {
            student = true;
        }
        if(qName.equalsIgnoreCase("Name")) {
            name = true;
        }
        if(qName.equalsIgnoreCase("Surname")) {
            surname = true;
        }
        if(qName.equalsIgnoreCase("Faculty")) {
            faculty = true;
        }
        if(qName.equalsIgnoreCase("Department")) {
            department = true;
        }
        if(qName.equalsIgnoreCase("AveragePoint")) {
            average = true;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String sName = "", sSurname = "",
                sFaculty = "", sDepartment = "", sAverage = "";
        if(student) {
            student = false;
        }
        if(name) {
            sName = new String(ch, start, length);
            name = false;
        }
        if(surname) {
            sSurname = new String(ch, start, length);
            surname = false;
        }
        if(faculty) {
            sFaculty = new String(ch, start, length);
            faculty = false;
        }
        if(department) {
            sDepartment = new String(ch, start, length);
            department = false;
        }
        if(average) {
            sAverage = new String(ch, start, length);
            average = false;
        }

        if(!sName.equals("")) {
            tokens.add(sName);
        }
        if(!sSurname.equals("")) {
            tokens.add(sSurname);
        }
        if(!sFaculty.equals("")) {
            tokens.add(sFaculty);
        }
        if(!sDepartment.equals("")) {
            tokens.add(sDepartment);
        }
        if(!sAverage.equals("")) {
            tokens.add(sAverage);
        }


    }

    public ArrayList<String> getTokens() {
        return tokens;
    }



}
