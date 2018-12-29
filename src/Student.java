public class Student {
    private String name;
    private String surname;
    private String faculty;
    private String depratment;
    private String averagePoint;

    public Student(String name, String surname, String faculty, String department, String averagePoint) {
        this.name = name;
        this.surname = surname;
        this.faculty = faculty;
        this.depratment = department;
        this.averagePoint = averagePoint;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getDepratment() {
        return depratment;
    }

    public String getAveragePoint() {
        return averagePoint;
    }

    public String toString() {
        return "Name: " + name + "\n" +
                "Surname: " + surname + "\n" +
                "Faculty: " + faculty + "\n" +
                "Department: " + depratment + "\n" +
                "Average: " + averagePoint + "\n" + "\n";
    }

    public Boolean equals(Student u) {
        return toString().equals(u.toString());
    }
}
