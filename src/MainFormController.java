import javax.swing.*;
import javax.swing.event.ListDataListener;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class MainFormController {
    private XmlWorker xmlWorker;
    final int DOM_MODE = 0, SAX_MODE = 1;
    private int mode = DOM_MODE;
    String path = "NOT_DEFINED";

    public void setSaxMode(JRadioButton domButton, JRadioButton saxButton) {
        domButton.setSelected(false);
        mode = SAX_MODE;
        xmlWorker = new Sax(path);

    }
    public void setDomMode(JRadioButton domButton, JRadioButton saxButton) {
        saxButton.setSelected(false);
        mode = DOM_MODE;
        xmlWorker = new Dom(path);
    }

    public void toHtml() {
        if(path.equals("NOT_DEFINED")) {
            JOptionPane.showMessageDialog(null, "There is no chosen file.");
            return;
        }
        JFileChooser f = new JFileChooser();
        f.setFileSelectionMode(JFileChooser.FILES_ONLY);
        f.showSaveDialog(null);

        File selectedFile = f.getSelectedFile();

        if(selectedFile == null) {
            return;
        }

        String fileToSave = selectedFile.getAbsolutePath();

        TransformerFactory factory = TransformerFactory.newInstance();
        try {
            Transformer transformer = factory.newTransformer(new StreamSource("students.xsl"));
            transformer.transform(new StreamSource(path),
                    new StreamResult(fileToSave));
            JOptionPane.showMessageDialog(null,
                    "The information from the XML-document" +
                    " is written to " + fileToSave);
        }
        catch(Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Sorry, some error occurred.");
        }
    }

    private ArrayList<Student> intersect(ArrayList<Student> a, ArrayList<Student> b) {
        ArrayList<Student> c = new ArrayList<>();
        for(Student s : a) {
            boolean contains = false;
            for(Student t : b) {
                if(s.equals(t)) {
                    contains = true;
                    break;
                }
            }
            if(contains) {
                c.add(s);
            }
        }

        return c;
    }

    public String search(List<XPathFilter> filters) {
        if(path.equals("NOT_DEFINED")) {
            JOptionPane.showMessageDialog(null, "There is no chosen file.");
            return "";
        }
        try {
            ArrayList<Student> result = xmlWorker.parse();

            for (XPathFilter filter : filters) {
                if (filter.isEnabled()) {
                    result = intersect(result, xmlWorker.search(filter.getTag(), filter.getParam()));
                }
            }
            String resultStr = "";
            for (Student student : result) {
                resultStr += student.toString() + "\n";
            }
            return resultStr;
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Please select a parsing method");
            return "";
        }
    }

    public void chooseFile(JLabel fileNameLabel, JComboBox facultyComboBox) {
        JFileChooser f = new JFileChooser();
        f.setFileSelectionMode(JFileChooser.FILES_ONLY);
        f.showOpenDialog(null);

        File selectedFile = f.getSelectedFile();

        if(selectedFile == null){
            //No Directory selected
        }else {
            path = selectedFile.getAbsolutePath();


            fileNameLabel.setText(path);

            xmlWorker = (mode == DOM_MODE ? new Dom(path) : new Sax(path));

            List<Student> students = xmlWorker.parse();
            TreeSet<String> faculties = new TreeSet<>();
            for (Student student : students) {
                faculties.add(student.getFaculty());
            }

            facultyComboBox.removeAllItems();
            facultyComboBox.addItem("");
            for (String faculty : faculties) {
                facultyComboBox.addItem(faculty);
            }
        }
    }

}
