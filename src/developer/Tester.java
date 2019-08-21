package developer;

import entities.doctors.BaseDoctor;
import entities.patients.Patient;
import main.ER;
import main.MainRedistributable;

import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * developer.Tester.
 * <p>
 * 18-Dec-18
 *
 * @author Gheoace Mihai
 */

public final class Tester {
    private static ER er  = ER.getInstance();

    private Tester() {

    }

    /**
     * Print patients state for current round.
     */
    public static void printExamination() {
        SortedSet<String> keys = new TreeSet<>(er.getExamMsg().keySet());
        for (String key : keys) {
            System.out.println(er.getExamMsg().get(key));
        }
        System.out.println("");
    }

    /**
     * Print assistent message.
     */
    public static void printTreatment() {
        System.out.println("~~~~ Nurses treat patients ~~~~");
        SortedSet<Patient> keys = new TreeSet<>(er.getNursesMsg().keySet());
        for (Patient key : keys) {
            System.out.println(er.getNursesMsg().get(key));
        }
        System.out.println("");
    }

    /**
     * Print doctor's verdict.
     */
    public static void doctorsCheck() {
        System.out.println(
                "~~~~ Doctors check their hospitalized patients and give verdicts ~~~~"
        );

        for (BaseDoctor doctor : er.getDoctors()) {
            // doctors list of messages
            HashMap<String, String> patientsMsgList = er.getDoctorsMsg().
                    get(doctor.getId());
            // patients name as keys
            SortedSet<String> keys = new TreeSet<>(patientsMsgList.keySet());
            // print each patient message
            for (String key : keys) {
                System.out.println(patientsMsgList.get(key));
            }
        }
        System.out.println("");
    }

    /**
     * For testing.
     * @param main
     */
    public static void printMain(MainRedistributable main) {
        System.out.println(main.getSimulationLength());
        System.out.println(main.getNurses());
        System.out.println(main.getInvestigators());
        for (BaseDoctor doctor : main.getDoctors()) {
            doctor.print();
        }
        for (Patient patient : main.getPatients()) {
            patient.print();
        }
    }

    /**
     * For further developement. Print doctors data.
     * @param doctor
     */
    public static void printDoctor(BaseDoctor doctor) {
        System.out.println(doctor.getType().toString());
        System.out.println(doctor.getIsSurgeon());
        System.out.println(doctor.getMaxForTreatment());
    }

    /**
     * Print patient data.
     * @param patient
     */
    public static void printPatient(Patient patient) {
        StringBuilder msg = new StringBuilder();
        msg.append("id " + patient.getId());
        msg.append(" name " + patient.getName());
        msg.append(" age " + patient.getAge());
        msg.append(" suffering of " + patient.getState().getIllnessName().getValue());
        msg.append(" severity: " + patient.getState().getSeverity());
        msg.append(" urgency: " + patient.getUrgency());
        System.out.println(msg.toString());
    }

    /**
     * For testing triage messages.
     */
    public static void printTriage() {
        if (er.getTriage() == null || er.getTriage().isEmpty()) {
            System.out.println("triage is empty");
            return;
        }
        for (Patient patient : er.getTriage()) {
            printPatient(patient);
        }
    }
}
