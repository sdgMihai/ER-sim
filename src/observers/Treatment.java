/**
 * Treatment
 * <p>
 * 19-Dec-18
 *
 * @author Gheoace Mihai
 */

package observers;

import developer.Tester;
import entities.doctors.BaseDoctor;
import entities.patients.Patient;
import main.ER;

import java.util.Observable;
import java.util.Observer;
import java.util.SortedSet;
import java.util.TreeSet;

public final class Treatment implements Observer {
    private ER er = ER.getInstance();

    /**
     * Applies treatment update for one round.
     * @param o
     * @param arg
     */
    public void update(Observable o, Object arg) {
        // nurses treat patients
        SortedSet<Patient> keys = new TreeSet<>(er.getHospitalizedPatients());
        int i = 0;
        for (Patient patient : keys) {
            // actual treatment
            er.getNurses().get(i % er.getNurses().size()).treat(
                    patient, patient.getDoctor(), i % er.getNurses().size()
            );
            i++;
        }
        Tester.printExamination();
        Tester.printTreatment();

        clearDoctorsMsg();
        // doctor check patients
        for (BaseDoctor baseDoctor : er.getDoctors()) {
            if (!baseDoctor.getPatients().isEmpty()) {
                // treat patients
                i = 0;
                while (i < baseDoctor.getPatients().size()) {
                    Patient patient = baseDoctor.getPatients().get(i);
                    // doctors decision
                    baseDoctor.treat(patient);
                    if (patient.getRounds() <= 0 || patient.getState().getSeverity() <= 0) {
                        // remove patient from doctor's internal list
                        baseDoctor.sendHomeAfterTreatment(baseDoctor.getPatients().get(i));
                        --i;
                    }
                    ++i;
                }
            }
        }
        Tester.doctorsCheck();
    }

    private void clearDoctorsMsg() {
        for (BaseDoctor baseDoctor : er.getDoctors()) {
            er.getDoctorsMsg().get(baseDoctor.getId()).clear();
        }
    }
}
