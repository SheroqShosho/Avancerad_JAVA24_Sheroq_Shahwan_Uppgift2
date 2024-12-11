package Studenthanteringssystem;

public class Studentuppgifter {
    // Privata egenskaper
    private String studentID;
    private String studentNamn;
    private String studentBetyg;

    // Konstruktor
    public Studentuppgifter(String studentID, String studentNamn, String studentBetyg) {
        this.studentID = studentID;
        this.studentNamn = studentNamn;
        this.studentBetyg = studentBetyg;
    }

    // Getters
    public String getStudentID() {
        return studentID;
    }

    public String getStudentNamn() {
        return studentNamn;
    }

    public String getStudentBetyg() {
        return studentBetyg;
    }

    // Metod som returnerar en strängrepresentation av studenten
    @Override
    public String toString() {
        return
                "studentID: '" + studentID + '\'' +
                ", studentNamn: '" + studentNamn + '\'' +
                ", studentBetyg: '" + studentBetyg + '\'';
    }
}
