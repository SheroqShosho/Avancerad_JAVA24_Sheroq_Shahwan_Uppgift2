package Studenthanteringssystem;

import java.io.IOException;
import java.util.Scanner;

public class Systemet {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Skapa en Scanner
        StudentRegistering register = StudentRegistering.getInstance(); // Hämta singleton-instansen av StudentRegistering
        int val; // Variabel för att lagra användarens val

        do {
            // Visa huvudmenyn
            System.out.println("\nStudenthanteringssystem:");
            System.out.println("1. Lägg till student");
            System.out.println("2. Visa alla studenter");
            System.out.println("3. Spara poster till fil");
            System.out.println("4. Läs poster från fil");
            System.out.println("5. Sök student via ID");
            System.out.println("6. Avsluta");
            System.out.print("Välj ett alternativ: ");
            val = scanner.nextInt(); // Läs in användarens val
            scanner.nextLine(); // Rensa radbrytningen

            switch (val) {
                case 1: // Lägg till student
                    System.out.print("Ange student-ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Ange student-namn: ");
                    String namn = scanner.nextLine();
                    System.out.print("Ange student-betyg: ");
                    String betyg = scanner.nextLine();
                    if (register.adderaStudent(id, namn, betyg)) {
                        System.out.println("Student tillagd.");
                    } else {
                        System.out.println("Ett fel inträffade. ID måste vara unikt.");
                    }
                    break;

                case 2: // Visa alla studenter
                    System.out.println("\nLista över alla studenter:");
                    register.StudenternasLista();
                    break;

                case 3: // Spara till fil
                    System.out.print("Ange filnamn (t.ex students.txt): ");
                    String filNamn = scanner.nextLine();
                    try {
                        register.sparaTillFil(filNamn);
                    } catch (IOException e) {
                        System.err.println("Ett fel inträffade vid sparning till fil: " + e.getMessage());
                    }
                    break;

                case 4: //  Läsa filen
                    System.out.print("Ange filnamn (t.ex students.txt): ");
                    String filNamnLadda = scanner.nextLine();
                    try {
                        register.laddaTillFil(filNamnLadda);
                    } catch (IOException e) {
                        System.err.println("Ett fel inträffade vid läsning från fil: " + e.getMessage());
                    }
                    break;

                case 5: // Hitta studenten från ID
                    System.out.print("Ange student-ID för att söka upp: ");
                    String searchId = scanner.nextLine();
                    Studentuppgifter student = register.hittaStudentViaID(searchId);
                    if (student != null) {
                        System.out.println("Student hittad: " + student);
                    } else {
                        System.out.println("Ingen student med ID " + searchId + " hittades.");
                    }
                    break;

                case 6: // Avsluta
                    System.out.println("Programmet avslutas!");
                    break;

                default: // Felmeddelande
                    System.out.println("Ogiltigt val. Försök igen.");
                    break;

            }
        } while (val != 6); // Forsätter att visa menyn tills användaren avslutar

        scanner.close();
    }
}