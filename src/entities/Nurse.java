/**
 * Nurse
 * <p>
 * 30-Dec-18
 *
 * @author Gheoace Mihai
 */

package entities;

import entities.doctors.BaseDoctor;
import entities.patients.Patient;
import main.ER;

public final class Nurse {
    public Nurse() {

    }

    /**
     * Applies treatment to patient.
     * @param patient patient to be treated
     * @param doctor the doctor who hospitalized patient
     * @param id nurses id
     */
    public void treat(Patient patient, BaseDoctor doctor, int id) {
        ER er = ER.getInstance();
        patient.setRounds(patient.getRounds()
                - 1);
        patient.getState().setSeverity(
                patient.getState().getSeverity() - doctor.getT());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Nurse " + id  + " treated "
                + patient.getName() + " and patient has " + patient.getRounds());
        if (patient.getRounds() != 1) {
            stringBuilder.append(" more rounds");
        } else  {
            stringBuilder.append(" more round");
        }
        er.getNursesMsg().put(patient, stringBuilder.toString());
    }
}
