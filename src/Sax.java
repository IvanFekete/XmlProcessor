import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.ArrayList;

public class Sax implements  XmlWorker {
    private String filename;

    public Sax(String filename) {
        this.filename = filename;
    }

    public ArrayList<Student> parse() {
        ArrayList<Student> students = new ArrayList<>();
        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxParserFactory.newSAXParser();
            Handler handler = new Handler();
            saxParser.parse(filename, handler);
            ArrayList<String> strings = handler.getTokens();
            for(int i = 0; i + 4 < strings.size(); i += 5) {
                String name = strings.get(i);
                String surname = strings.get(i + 1);
                String faculty = strings.get(i + 2);
                String department = strings.get(i + 3);
                String averagePoint = strings.get(i + 4);

                students.add(new Student(name, surname, faculty, department, averagePoint));
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    private boolean startsWith(String s, String t) {
        return t.length() <= s.length() && t.equals(s.substring(0, t.length()));
    }

    public ArrayList<Student> search(String tag, String param) {
        ArrayList<Student> allStudents = parse(),
            students = new ArrayList<>();


        try {
            for(Student s : allStudents) {
                String forCheck = "";
                if(tag.equals("Name")) {
                    forCheck = s.getName();
                }
                if(tag.equals("Surname")) {
                    forCheck = s.getSurname();
                }
                if(tag.equals("Faculty")) {
                    forCheck = s.getFaculty();
                }
                if(tag.equals("Department")) {
                    forCheck = s.getDepratment();
                }
                if(tag.equals("AveragePoint")) {
                    forCheck = s.getAveragePoint();
                }

                System.out.println(forCheck + " " + param);

                if(startsWith(forCheck, param)) {
                    students.add(s);
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return students;
    }

}
