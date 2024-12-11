package Studenthanteringssystem;

import java.io.*;
import java.util.HashMap;

public class StudentRegistering {
    private static StudentRegistering instance; // Singelton-instans
    private HashMap<String, Studentuppgifter> studenter; // HashMap för att lagra studenter

    // Konstruktor
    public StudentRegistering() {
        studenter = new HashMap<>();
    }

    // Singelton-metod för att hämta instansen av klassen
    public static StudentRegistering getInstance() {
        if (instance == null) { // Kontrollera om instansen redan existerar
            instance = new StudentRegistering();
        }
        return instance; // Returnerar den existerande eller nya instansen
    }

    // Metod för att lägga till en ny student
    public boolean adderaStudent(String studentID, String studentNamn, String studentBetyg) {
        if (studenter.containsKey(studentID)) { // Konnrollera om student-ID redan existerar
            return false; // Returnera true om tillägget lyckades
        }
        studenter.put(studentID, new Studentuppgifter(studentID, studentNamn, studentBetyg)); // Lägg till en studenten
        return true; // Retunera true om tillägget lyckades
    }

    // Metod för att visa listan av alla studenter
    public void StudenternasLista() {
        if (studenter.isEmpty()) { // Kontrollera om listan är tom
            System.out.println("Inga studenter finns registerade.");
        } else {
            studenter.values().forEach(System.out::println); // Skriver ut alla studenter
        }
    }

    // Metod för att hitta en student baserat på deras ID
    public Studentuppgifter hittaStudentViaID(String studentID) {
        return studenter.get(studentID); // Returnera studentens uppgifter om ID matchar
    }

    // Metod för att spara alla studenter till en fil
    public void sparaTillFil(String filNamn) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filNamn))) { // Skapa BufferedWriter
            for (Studentuppgifter studentuppgifter : studenter.values()) { // Loop genom alla studenter
                writer.write(studentuppgifter.toString()); // Skriv studentens uppgifter till filen
                writer.newLine(); // Lägg till en ny rad
            }
            System.out.println("Studentposter har sparats till filen: " + filNamn);
        } catch (IOException e) {
            System.out.println("Ett fel inträffade vis skrivning till filen: " + e.getMessage());
        }
    }

    // Metod för att ladda studenter från en fil
    public void laddaTillFil(String filNamn) throws IOException {
        try ( BufferedReader reader = new BufferedReader(new FileReader(filNamn))) { // Skapa BufferedReader
            String line;
            while ((line = reader.readLine()) != null) { // Läs varje rad från filen
                String[] parts = line.split(","); // Dela raden i delar
                if (parts.length == 3) { // Kontrollera om raden har tre delar
                    String studentID = parts[0].trim();
                    String studentNamn = parts[1].trim();
                    String studentBetyg = parts[2].trim();
                    adderaStudent(studentID, studentNamn, studentBetyg); // Lägg till studenten

                }
            }
            System.out.println("Studentposter har laddats till filen: " + filNamn);
        } catch (FileNotFoundException e) {
            System.err.println("Filen hittades inte: " + filNamn);
        } catch (IOException e) {
            System.err.println("Ett fel inträäffade vid läsning från filen: " + e.getMessage());
        }
    }
}
