import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class MainForm {

    private JTextArea textArea;
    private JPanel panel1;
    private JRadioButton domRadioButton;
    private JRadioButton saxRadioButton;
    private JCheckBox nameCheckBox;
    private JCheckBox surnameCheckBox;
    private JCheckBox facultyCheckBox;
    private JCheckBox departmentCheckBox;
    private JCheckBox averagePointCheckBox;
    private JTextField nameQueriesTextField;
    private JTextField surnameQueriesTextField;
    private JTextField departmentQueriesTextField;
    private JTextField averagePointQueriesTextField;
    private JButton searchButton;
    private JButton toHtmlButton;
    private JButton clearButton;
    private JButton chooseFile;
    private JLabel fileNameLabel;
    private JComboBox facultyComboBox;

    private MainFormController controller;

    public MainForm() {
        this.controller = new MainFormController();
        controller.setDomMode(domRadioButton, saxRadioButton);
        domRadioButton.setSelected(true);
        domRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                controller.setDomMode(domRadioButton, saxRadioButton);
            }
        });
        saxRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                controller.setSaxMode(domRadioButton, saxRadioButton);
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Object selectedItem = facultyComboBox.getSelectedItem();
                List<XPathFilter> filters = Arrays.asList(
                        new XPathFilter(nameCheckBox, "Name", nameQueriesTextField.getText()),
                        new XPathFilter(surnameCheckBox, "Surname", surnameQueriesTextField.getText()),
                        new XPathFilter(facultyCheckBox, "Faculty", (selectedItem == null ? "" : selectedItem).toString()),
                        new XPathFilter(departmentCheckBox, "Department", departmentQueriesTextField.getText()),
                        new XPathFilter(averagePointCheckBox, "AveragePoint", averagePointQueriesTextField.getText())
                        );
                textArea.setText(controller.search(filters));
            }
        });
        toHtmlButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                controller.toHtml();
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                textArea.setText("");

                nameCheckBox.setSelected(false);
                surnameCheckBox.setSelected(false);
                facultyCheckBox.setSelected(false);
                departmentCheckBox.setSelected(false);
                averagePointCheckBox.setSelected(false);

                
                nameQueriesTextField.setText("");
                surnameQueriesTextField.setText("");
                departmentQueriesTextField.setText("");
                averagePointQueriesTextField.setText("");
            }
        });
        chooseFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.chooseFile(fileNameLabel, facultyComboBox);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("XML processor");
        frame.setContentPane(new MainForm().panel1);
        frame.setSize(1000, 800) ;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
