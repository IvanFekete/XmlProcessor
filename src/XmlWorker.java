import java.util.ArrayList;

public interface XmlWorker {
    public ArrayList<Student> parse();
    public ArrayList<Student> search(String tag, String param);

}
